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
class TestByNoOfQuestionsAndDifficulty implements ITestByNoOfQuestionsAndDifficulty{
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

        List<QuestionDto> questionsOfSameDifficulty = possibleQuestions.stream()
                .filter(q -> q.getDifficulty() == difficulty)
                .collect(Collectors.toList());

        List<QuestionDto> questionWithDifferentDifficulty = possibleQuestions.stream()
                .filter(q -> q.getDifficulty() != difficulty)
                .collect(Collectors.toList());

        int differentDifficulty;

        int testDuration = 0;

        if (questionsOfSameDifficulty.size() < numberOfQuestions)
            return null;

        TestDto test = new TestDto(domain, difficulty);

        Random r = new Random();

        while (numberOfQuestions > 0) {
            differentDifficulty = r.nextInt(10);
            QuestionDto nextQuestion;

            if (differentDifficulty >= 7 && questionWithDifferentDifficulty.size() > 0)
                nextQuestion = pickQuestion(questionWithDifferentDifficulty);
            else
                nextQuestion = pickQuestion(questionsOfSameDifficulty);

            testDuration += computeDuration(nextQuestion, difficulty);

            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                test.addQuestion(nextQuestion.getType(), new SimpleTestQuestionDto(nextQuestion));
                possibleQuestions.remove(nextQuestion);
            } else {
                List<QuestionDto> picked = TestServiceUtils.createSingleMatchQuestion(possibleQuestions);
                if (picked != null) {
                    List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transformed = transform(picked);

                    TestServiceUtils.indexSingleMatchQuestion(transformed);

                    test.addQuestion(QuestionType.SingleMatch, new ComplexTestQuestionDto(shuffle(transformed)));
                    possibleQuestions.removeAll(picked);
                }
            }

            numberOfQuestions -= 1;
        }

        test.setDuration(testDuration);

        return test;
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
