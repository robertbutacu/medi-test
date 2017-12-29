package com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria;

import com.medi.test.meditest.services.contracts.test.generator.stop.criteria.ITestGeneratorStopCriterion;

public class TestByDurAndNoOfQuestsStopCriterion implements ITestGeneratorStopCriterion {
    private int numberOfQuestions;
    private double duration;

    public TestByDurAndNoOfQuestsStopCriterion(int numberOfQuestions, double duration){
        this.numberOfQuestions = numberOfQuestions;
        this.duration = duration;
    }

    @Override
    public void decrement(int currentQuestionDuration) {
        this.numberOfQuestions -= 1;
        this.duration -= (double) currentQuestionDuration;
    }

    @Override
    public boolean hasToGenerate() {
        return numberOfQuestions > 0 ;
    }
}
