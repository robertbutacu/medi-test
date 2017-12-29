package com.medi.test.meditest.services.implementation.test.implementation.utils;

import com.medi.test.meditest.dtos.test.ITestQuestion;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import javafx.util.Pair;

public class DurationCalculator {
    public DurationCalculator() {
    }

    public void computeDuration(TestDto test) {
        for (Pair<QuestionType, ITestQuestion> q :
                test.getQuestions()) {
            q.getValue()
                    .computeEstimatedDuration(test.getDifficulty())
                    .ifPresent(test::addToDuration);
        }
    }
}
