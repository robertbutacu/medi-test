package com.medi.test.meditest.services.contracts.test.generator;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;

public interface ITestByDurationAndNoOfQuestions {
    TestDto generateTest(DomainDto domain, double duration, int numberOfQuestions);
}
