package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.QuestionDifficulty;

import java.util.Optional;

public interface ITestService {
    Optional<TestDto> getTest(DomainDto domainDto, QuestionDifficulty difficulty, int numberOfQuestions);
}
