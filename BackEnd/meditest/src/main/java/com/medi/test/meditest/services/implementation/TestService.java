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
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService{
    @Autowired
    private IQuestionRepository questionsRepository;


    @Override
    public Optional<TestDto> getTest(DomainDto domainDto, QuestionDifficulty difficulty, int numberOfQuestions) {
        if (numberOfQuestions < 5)
            return Optional.empty();

        List<QuestionDto> questions = questionsRepository.findByDifficulty(difficulty)
                .stream()
                .map(QuestionTransformer::toDto)
                .collect(Collectors.toList());

        TestDto test = new TestDto();

        test.setDifficulty(difficulty.toString());
        test.setDomain(domainDto);


        while(numberOfQuestions > 0){
            numberOfQuestions = numberOfQuestions - 1 ;
        }

        return Optional.of(test);
    }
}
