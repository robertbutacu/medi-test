package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.Transformers.DomainTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndDifficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class TestByDurationAndDifficulty implements ITestByDurationAndDifficulty{
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    TestByDurationAndDifficulty(){

    }

    @Override
    public TestDto generateTest(DomainDto domain, Difficulty difficulty, double duration) {
        Set<DomainDto> domains = domainService.getDomainsByDifficulty(domain, difficulty)
                .stream().map(DomainTransformer::toDto)
                .collect(Collectors.toSet());

        return null;
    }
}
