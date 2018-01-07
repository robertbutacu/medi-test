package com.medi.test.meditest.services.implementation.test.implementation.utils;

public class TestServiceConstants {
    private static int probabilityForDifferentQuestion = 3;

    public final static int MIN_QUESTIONS_PER_TEST = 5;

    public final static int MIN_DURATION_PER_TEST = 10;

    public final static int DEPTH_FOR_EASY_TEST = 1;

    public final static int DEPTH_FOR_MEDIUM_TEST = 2;

    public final static int DEPTH_FOR_HARD_TEST = 3;


    static int differentQuestionMinThreshold() {
        return (10 - probabilityForDifferentQuestion);
    }

    static void setDifferentQuestionMinThreshold(int newThreshold) {
        if (newThreshold >= 0 && newThreshold <= 10)
            probabilityForDifferentQuestion = newThreshold;
    }
}
