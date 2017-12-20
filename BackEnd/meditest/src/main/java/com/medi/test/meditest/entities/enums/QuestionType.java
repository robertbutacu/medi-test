package com.medi.test.meditest.entities.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum QuestionType {
    MultipleChoice, FillIn, TrueOrFalse, SingleMatch;

    private static final List<QuestionType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static QuestionType randomType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
