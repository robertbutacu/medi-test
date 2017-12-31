package com.medi.test.meditest.services.implementation.test.implementation.utils;

class TestServiceConstants {
    private static int probabilityForDifferentQuestion = 3;

    static int differentQuestionMinThreshold() {
        return (10 - probabilityForDifferentQuestion);
    }

    static void setDifferentQuestionMinThreshold(int newThreshold) {
        if (newThreshold >= 0 && newThreshold <= 10)
            probabilityForDifferentQuestion = newThreshold;
    }
}
