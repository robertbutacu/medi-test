package com.medi.test.meditest.services.contracts.test.generator;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;

public interface ITestByNoOfQuestionsAndDifficulty {
    TestDto generateTest(DomainDto domain, int numberOfQuestions, Difficulty difficulty);
}
