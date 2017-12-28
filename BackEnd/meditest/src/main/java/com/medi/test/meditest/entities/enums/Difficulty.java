package com.medi.test.meditest.entities.enums;

import com.medi.test.meditest.dtos.QuestionDto;

public enum Difficulty {
    Easy, Medium, Hard;

    public static double normalizeDuration(QuestionDto question, Difficulty difficulty) {
        switch (difficulty) {
            case Easy:
                if (question.getDifficulty() == Medium)
                    return question.getExpectedSecsToAnswer() * 1.5;

                if (question.getDifficulty() == Hard)
                    return question.getExpectedSecsToAnswer() * 2.0;

                return question.getExpectedSecsToAnswer();

            case Medium:
                if (question.getDifficulty() == Easy)
                    return question.getExpectedSecsToAnswer() * 0.75;

                if (question.getDifficulty() == Hard)
                    return question.getExpectedSecsToAnswer() * 1.5;

                return question.getExpectedSecsToAnswer();

            case Hard:
                if (question.getDifficulty() == Easy)
                    return question.getExpectedSecsToAnswer() * 0.5;

                if (question.getDifficulty() == Medium)
                    return question.getExpectedSecsToAnswer() * 0.75;

                return question.getExpectedSecsToAnswer();
        }

        return question.getExpectedSecsToAnswer();
    }
}
