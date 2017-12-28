package com.medi.test.meditest.services.implementation.test.implementation;

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
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Override
    public TestDto generateTest(DomainDto domain, Difficulty difficulty, Integer numberOfQuestions, Integer duration) {
        if (difficulty != null && numberOfQuestions != null && duration == null)
            return generateByDifficultyAndNumberOfQuestions(domain, difficulty, numberOfQuestions);

        if (difficulty != null && numberOfQuestions == null && duration != null)
            return generateByDifficultyAndDuration(domain, difficulty, duration);

        if (difficulty == null && numberOfQuestions != null && duration != null)
            return generateByNumberOfQuestionsAndDuration(domain, numberOfQuestions, duration);

        return null;
    }


    private TestDto generateByDifficultyAndNumberOfQuestions(DomainDto domain, Difficulty difficulty,
                                                             int numberOfQuestions) {
        TestDto test;

        switch (difficulty) {
            case Easy:
                test = getTest(domain, difficulty, numberOfQuestions);
                if (test != null)
                    test.setDuration(3 * numberOfQuestions);
                return test;

            case Medium:
                test = getTest(domain, difficulty, numberOfQuestions);
                if (test != null)
                    test.setDuration(2 * numberOfQuestions);
                return test;

            case Hard:
                test = getTest(domain, difficulty, numberOfQuestions);
                if (test != null)
                    test.setDuration(numberOfQuestions);
                return test;

            default:
                return null;
        }
    }


    private TestDto generateByDifficultyAndDuration(DomainDto domain, Difficulty difficulty,
                                                    int duration) {
        TestDto test;
        int numberOfQuestions;

        switch (difficulty) {
            case Easy:
                numberOfQuestions = duration / 3;

                test = getTest(domain, difficulty, numberOfQuestions);
                if (test != null)
                    test.setDuration(3 * numberOfQuestions);
                return test;

            case Medium:
                numberOfQuestions = duration / 2;

                test = getTest(domain, difficulty, numberOfQuestions);
                if (test != null)
                    test.setDuration(2 * numberOfQuestions);
                return test;

            case Hard:
                numberOfQuestions = duration;

                test = getTest(domain, difficulty, numberOfQuestions);
                if (test != null)
                    test.setDuration(numberOfQuestions);
                return test;

            default:
                return null;
        }
    }


    private TestDto generateByNumberOfQuestionsAndDuration(DomainDto domain, int numberOfQuestions, int duration) {
        Double ratio = (double) duration / (double) numberOfQuestions;

        TestDto test;

        if (ratio < 2) {
            test = getTest(domain, Difficulty.Hard, numberOfQuestions);

            if (test != null)
                test.setDuration(duration);
            return test;
        }

        if (ratio < 3) {
            test = getTest(domain, Difficulty.Medium, numberOfQuestions);

            if (test != null)
                test.setDuration(duration);
            return test;
        }

        test = getTest(domain, Difficulty.Easy, numberOfQuestions);

        if (test != null)
            test.setDuration(duration);

        return test;
    }


    private TestDto getTest(DomainDto domainDto, Difficulty difficulty, int numberOfQuestions) {
        if (numberOfQuestions < 5)
            return null;

        Set<DomainDto> possibleDomains = domainService.getDomainsByDifficulty(domainDto, difficulty);

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
