package com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria;

import com.medi.test.meditest.services.contracts.test.generator.stop.criteria.ITestGeneratorStopCriterion;

public class TestByNoOfQuestsAndDiffStopCriterion implements ITestGeneratorStopCriterion {
    private int numberOfQuestions;

    public TestByNoOfQuestsAndDiffStopCriterion(int numberOfQuestions){
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public void decrement(int currentQuestionDuration) {
        this.numberOfQuestions -= 1;
    }

    @Override
    public boolean hasToGenerate() {
        return this.numberOfQuestions > 0;
    }
}
