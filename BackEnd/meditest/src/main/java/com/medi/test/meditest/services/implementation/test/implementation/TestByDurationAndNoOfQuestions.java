package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndNoOfQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TestByDurationAndNoOfQuestions implements ITestByDurationAndNoOfQuestions{
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    @Override
    public TestDto generateTest(DomainDto domain, double duration, int numberOfQuestions){
        return null;
    }
}
