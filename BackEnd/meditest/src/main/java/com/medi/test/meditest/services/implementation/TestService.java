package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.Transformers.QuestionTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.QuestionDifficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService{
    @Autowired
    private IQuestionRepository questionsRepository;


    @Override
    public Optional<TestDto> getTest(DomainDto domainDto, QuestionDifficulty difficulty, int numberOfQuestions) {
        if (numberOfQuestions < 5)
            return Optional.empty();

        List<QuestionDto> possibleQuestions = questionsRepository.findByDifficulty(difficulty)
                .stream()
                .map(QuestionTransformer::toDto)
                .filter(q -> q.getDomain() == domainDto)
                .collect(Collectors.toList());

        if (possibleQuestions.size() < numberOfQuestions)
            return Optional.empty();

        TestDto test = new TestDto();

        test.setDifficulty(difficulty.toString());
        test.setDomain(domainDto);

        Random randomQuestion = new Random();

        while(numberOfQuestions > 0){
            QuestionDto nextQuestion = possibleQuestions.get(randomQuestion.nextInt(possibleQuestions.size()));

            numberOfQuestions = numberOfQuestions - 1 ;
        }

        return Optional.of(test);
    }
}
