package com.medi.test.meditest.entities.enums;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.ITestQuestion;
import javafx.util.Pair;

import java.util.List;

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

    public static double normalizeDuration(int duration, Difficulty questionDifficulty, Difficulty testDifficulty) {
        switch (testDifficulty) {
            case Easy:
                if (questionDifficulty == Medium)
                    return duration * 1.5;

                if (questionDifficulty == Hard)
                    return duration * 2.0;

                return duration;

            case Medium:
                if (questionDifficulty == Easy)
                    return duration * 0.75;

                if (questionDifficulty == Hard)
                    return duration * 1.5;

                return duration;

            case Hard:
                if (questionDifficulty == Easy)
                    return duration * 0.5;

                if (questionDifficulty == Medium)
                    return duration * 0.75;

                return duration;
        }

        return duration;
    }


    public static Difficulty inferTestDifficulty(List<Pair<QuestionType, ITestQuestion>> testQuestions) {
        int nrHardQuestions = 0;
        int nrMediumQuestions = 0;
        int nrEasyQuestions = 0;

        for (Pair<QuestionType, ITestQuestion> q :
                testQuestions)
            switch (q.getValue().questionDifficulty()){
                case Easy:
                    nrEasyQuestions += 1;
                    break;
                case Medium:
                    nrMediumQuestions += 1;
                    break;
                case Hard:
                    nrHardQuestions += 1;
                    break;
                default:
                    break;
            }

        if ( nrHardQuestions >= nrMediumQuestions && nrHardQuestions >= nrEasyQuestions)
            return Difficulty.Hard;

        if ( nrMediumQuestions >= nrHardQuestions && nrMediumQuestions >= nrEasyQuestions )
            return Difficulty.Medium;

        return Difficulty.Easy;
    }
}
