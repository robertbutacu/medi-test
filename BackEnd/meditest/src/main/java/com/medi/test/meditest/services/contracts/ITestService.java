package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;

public interface ITestService {
    TestDto getTest(DomainDto domainDto, Difficulty difficulty, int numberOfQuestions);
}
