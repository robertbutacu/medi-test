package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.dtos.test.ITestQuestion;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import javafx.util.Pair;

class DurationCalculator {
    DurationCalculator() {
    }

    void computeDuration(TestDto test, Difficulty difficulty) {
        for (Pair<QuestionType, ITestQuestion> q :
                test.getQuestions()) {
            q.getValue()
                    .computeEstimatedDuration(test.getDifficulty())
                    .ifPresent(test::addToDuration);
        }
    }
}
