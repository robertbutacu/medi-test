package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.ITestService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndDifficulty;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndNoOfQuestions;
import com.medi.test.meditest.services.contracts.test.generator.ITestByNoOfQuestionsAndDifficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
