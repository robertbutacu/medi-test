package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.Transformers.DomainTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class TestByDurationAndDifficulty {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    TestByDurationAndDifficulty(){

    }

    TestDto generateTest(DomainDto domain, Difficulty difficulty, double duration) {
        Set<DomainDto> domains = domainService.getDomainsByDifficulty(domain, difficulty);

        return null;
    }
}
