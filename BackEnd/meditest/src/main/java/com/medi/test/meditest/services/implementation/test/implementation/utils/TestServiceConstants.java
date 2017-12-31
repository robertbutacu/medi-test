package com.medi.test.meditest.services.implementation.test.implementation.utils;

public class TestServiceConstants {
    private static int probabilityForDifferentQuestion = 3;

    public static int MIN_QUESTIONS_PER_TEST = 5;

    public static int MIN_DURATION_PER_TEST = 10;

    static int differentQuestionMinThreshold() {
        return (10 - probabilityForDifferentQuestion);
    }

    static void setDifferentQuestionMinThreshold(int newThreshold) {
        if (newThreshold >= 0 && newThreshold <= 10)
            probabilityForDifferentQuestion = newThreshold;
    }
}
