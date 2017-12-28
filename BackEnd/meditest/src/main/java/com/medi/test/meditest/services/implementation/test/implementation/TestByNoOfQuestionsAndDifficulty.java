package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;

class TestByNoOfQuestionsAndDifficulty {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    TestByNoOfQuestionsAndDifficulty(){

    }

    TestDto generateTest(DomainDto domain, int numberOfQuestions, Difficulty difficulty){
        return null;
    }
}
