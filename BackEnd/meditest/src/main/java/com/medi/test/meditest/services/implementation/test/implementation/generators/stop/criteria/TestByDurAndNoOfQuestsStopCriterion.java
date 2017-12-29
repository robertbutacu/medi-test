package com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria;

import com.medi.test.meditest.services.contracts.test.generator.ITestGeneratorStopCriterion;

public class TestByDurAndNoOfQuestsStopCriterion implements ITestGeneratorStopCriterion {
    @Override
    public void decrement() {

    }

    @Override
    public boolean isDoneGenerating() {
        return true;
    }
}
