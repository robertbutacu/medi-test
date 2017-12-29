package com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria;

import com.medi.test.meditest.services.contracts.test.generator.ITestGeneratorStopCriterion;

public class TestByNoOfQuestsAndDiffStopCriterion implements ITestGeneratorStopCriterion {
    private int numberOfQuestions;

    public TestByNoOfQuestsAndDiffStopCriterion(int numberOfQuestions){
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public void decrement() {
        this.numberOfQuestions -= 1;
    }

    @Override
    public boolean isDoneGenerating() {
        return this.numberOfQuestions == 0;
    }
}
