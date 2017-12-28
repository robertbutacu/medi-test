package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.Transformers.DomainTransformer;
import com.medi.test.meditest.Transformers.QuestionTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.dtos.test.simple.test.dto.SimpleTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.ComplexTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.ITestService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndDifficulty;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndNoOfQuestions;
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
public class TestService implements ITestService {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    @Autowired
    private ITestByNoOfQuestionsAndDifficulty testByNoOfQuestionsAndDifficulty;

    @Autowired
    private ITestByDurationAndNoOfQuestions testByDurationAndNoOfQuestions;

    @Autowired
    private ITestByDurationAndDifficulty testByDurationAndDifficulty;

    @Override
    public TestDto generateTest(DomainDto domain, Difficulty difficulty, Integer numberOfQuestions, Integer duration) {
        if (difficulty != null && numberOfQuestions != null && duration == null)
            return testByNoOfQuestionsAndDifficulty.generateTest(domain, numberOfQuestions, difficulty);

        if (difficulty != null && numberOfQuestions == null && duration != null)
            return testByDurationAndDifficulty.generateTest(domain, difficulty, duration);

        if (difficulty == null && numberOfQuestions != null && duration != null)
            return testByDurationAndNoOfQuestions.generateTest(domain, duration, numberOfQuestions);

        return null;
    }

    private TestDto getTest(DomainDto domainDto, Difficulty difficulty, int numberOfQuestions) {
        if (numberOfQuestions < 5)
            return null;

        Set<DomainDto> possibleDomains = domainService.getDomainsByDifficulty(domainDto, difficulty)
                .stream()
                .map(DomainTransformer::toDto)
                .collect(Collectors.toSet());

        List<QuestionDto> possibleQuestions = getAllQuestionsByDifficultyAndDomain(difficulty, possibleDomains);

        if (possibleQuestions.size() < numberOfQuestions)
            return null;

        TestDto test = new TestDto(domainDto, difficulty);

        Random randomQuestion = new Random();

        while (numberOfQuestions > 0) {
            QuestionDto nextQuestion = possibleQuestions.get(randomQuestion.nextInt(possibleQuestions.size()));

            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                test.addQuestion(nextQuestion.getType(), new SimpleTestQuestionDto(nextQuestion));
                possibleQuestions.remove(nextQuestion);
                numberOfQuestions -= 1;
            } else {
                List<QuestionDto> picked = TestServiceUtils.createSingleMatchQuestion(possibleQuestions);
                if (picked != null) {
                    numberOfQuestions -= 1;

                    List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transformed = transform(picked);

                    TestServiceUtils.indexSingleMatchQuestion(transformed);

                    test.addQuestion(QuestionType.SingleMatch, new ComplexTestQuestionDto(shuffle(transformed)));
                    possibleQuestions.removeAll(picked);
                }
            }
        }

        return test;
    }

    private List<QuestionDto> getAllQuestionsByDifficultyAndDomain(Difficulty difficulty, Set<DomainDto> possibleDomains) {
        return questionsRepository.findByDifficulty(difficulty)
                .stream()
                .map(QuestionTransformer::toDto)
                .filter(q -> possibleDomains.contains(q.getDomain()))
                .collect(Collectors.toList());
    }
}
