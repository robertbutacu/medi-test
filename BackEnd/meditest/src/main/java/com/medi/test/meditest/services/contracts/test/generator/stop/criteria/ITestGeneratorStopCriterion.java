package com.medi.test.meditest.services.contracts.test.generator.stop.criteria;

public interface ITestGeneratorStopCriterion{
    void decrement(int currentQuestionDuration);
    boolean hasToGenerate();
}
