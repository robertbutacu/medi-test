package com.medi.test.meditest.services.contracts.test.generator;

public interface ITestGeneratorStopCriterion {
    void decrement();
    boolean isDoneGenerating();
}
