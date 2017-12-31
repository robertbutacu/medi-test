package com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria;

import com.medi.test.meditest.services.contracts.test.generator.stop.criteria.ITestGeneratorStopCriterion;

public class TestByDurationAndDiffStopCriterion implements ITestGeneratorStopCriterion {
    private double duration;

    public TestByDurationAndDiffStopCriterion(double duration) {
        this.duration = duration;
    }

    @Override
    public void decrement(int currentQuestionDuration) {
        this.duration -= currentQuestionDuration;
    }

    @Override
    public boolean hasToGenerate() {
        return this.duration > 0;
    }
}
