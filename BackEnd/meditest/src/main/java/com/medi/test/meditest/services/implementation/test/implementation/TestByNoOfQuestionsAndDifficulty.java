package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.dtos.test.simple.test.dto.SimpleTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.ComplexTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.IQuestionService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByNoOfQuestionsAndDifficulty;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static com.medi.test.meditest.services.implementation.test.implementation.TestServiceUtils.shuffle;
import static com.medi.test.meditest.services.implementation.test.implementation.TestServiceUtils.transform;

@Service
class TestByNoOfQuestionsAndDifficulty implements ITestByNoOfQuestionsAndDifficulty {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    @Autowired
    private IQuestionService questionService;

    /*
    For number of questions and test difficulty:
    Pick questions of test difficulty with a probability of 30-40% of questions to be from a lower/higher rank.
    Also, the duration will be given by:
        If the test is easy : for medium-hard questions, multiply by 1.5 - 2 respectively
           the test is medium : divide by 0.75 for easy, and 1.5 for hard.
           the test is hard : divide by 0.75 for medium, and 0.5 for medium.
           -> the easiest, start with this.
     */
    @Override
    public TestDto generateTest(DomainDto domain, int numberOfQuestions, Difficulty difficulty) {
        Set<Domain> domains = domainService.getDomainsByDifficulty(domain, difficulty);

        List<QuestionDto> possibleQuestions = questionService.getQuestionsByDomains(domains);

        List<QuestionDto> questionsOfSameDifficulty = getQuestionsOfSameDifficulty(possibleQuestions, difficulty);
        List<QuestionDto> questionWithDiffDifficulty = getQuestionsWithDiffDifficulty(possibleQuestions, difficulty);

        if (questionsOfSameDifficulty.size() < numberOfQuestions)
            return null;

        TestDto test = new TestDto(domain, difficulty);
        Random r = new Random();

        int differentDifficulty;
        int testDuration = 0;

        while (numberOfQuestions > 0) {
            differentDifficulty = r.nextInt(10);
            QuestionDto nextQuestion;

            if (differentDifficulty >= 7 && questionWithDiffDifficulty.size() > 0)
                nextQuestion = pickQuestion(questionWithDiffDifficulty);
            else
                nextQuestion = pickQuestion(questionsOfSameDifficulty);


            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                test.addQuestion(nextQuestion.getType(), new SimpleTestQuestionDto(nextQuestion));
                questionsOfSameDifficulty.remove(nextQuestion);
                questionWithDiffDifficulty.remove(nextQuestion);

                testDuration += computeDuration(nextQuestion, difficulty);
            } else {
                List<QuestionDto> picked = TestServiceUtils.createSingleMatchQuestion(possibleQuestions);
                if (picked != null) {
                    ComplexTestQuestionDto next = normalizeToSingleMatch(picked);

                    testDuration += computeDuration(next.getExpectedSecsToAnswer(),
                            nextQuestion.getDifficulty(), difficulty);

                    test.addQuestion(QuestionType.SingleMatch, next);
                    questionsOfSameDifficulty.removeAll(picked);
                    questionWithDiffDifficulty.removeAll(picked);
                }
            }

            numberOfQuestions -= 1;
        }

        test.setDuration(testDuration);

        return test;
    }

    private List<QuestionDto> getQuestionsOfSameDifficulty(List<QuestionDto> possibleQuestions,
                                                           Difficulty difficulty) {
        return possibleQuestions.stream()
                .filter(q -> q.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    private List<QuestionDto> getQuestionsWithDiffDifficulty(List<QuestionDto> possibleQuestions,
                                                             Difficulty difficulty) {
        return possibleQuestions.stream()
                .filter(q -> q.getDifficulty() != difficulty)
                .collect(Collectors.toList());
    }

    private ComplexTestQuestionDto normalizeToSingleMatch(List<QuestionDto> picked) {
        List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transformed = transform(picked);

        TestServiceUtils.indexSingleMatchQuestion(transformed);

        return new ComplexTestQuestionDto(shuffle(transformed));
    }

    private int computeDuration(int questionDuration, Difficulty questionDifficulty, Difficulty testDifficulty) {
        double duration = Difficulty.normalizeDuration(questionDuration, questionDifficulty, testDifficulty);

        return (int) Math.round(duration / 5) * 5;
    }

    private int computeDuration(QuestionDto nextQuestion, Difficulty difficulty) {
        double duration = Difficulty.normalizeDuration(nextQuestion, difficulty);

        return (int) Math.round(duration / 5) * 5;
    }

    private QuestionDto pickQuestion(List<QuestionDto> questions) {
        Random r = new Random();

        return questions.get(r.nextInt(questions.size()));
    }
}
