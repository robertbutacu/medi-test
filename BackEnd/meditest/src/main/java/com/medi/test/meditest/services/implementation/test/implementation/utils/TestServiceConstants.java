package com.medi.test.meditest.services.implementation.test.implementation.utils;

public class TestServiceConstants {
    private static int probabilityForDifferentQuestion = 3;

    private static int minQuestionsPerTest = 5;

    private static int minDurationPerTest = 10;

    static int differentQuestionMinThreshold() {
        return (10 - probabilityForDifferentQuestion);
    }

    static void setDifferentQuestionMinThreshold(int newThreshold) {
        if (newThreshold >= 0 && newThreshold <= 10)
            probabilityForDifferentQuestion = newThreshold;
    }

    public static int minimumQuestionsPerTest() {
        return minQuestionsPerTest;
    }

    public static void setMinimumQuestionsPerTest(int newMin) {
        minQuestionsPerTest = newMin;
    }

    public static int minimumDurationPerTest() {
        return minDurationPerTest;
    }

    public static void setMinDurationPerTest(int newDuration) {
        minDurationPerTest = newDuration;
    }
}
