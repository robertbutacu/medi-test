package com.medi.test.meditest.dtos.test;

import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.Optional;

public interface ITestQuestion {
    Optional<Integer> computeEstimatedDuration(Difficulty testDifficulty);

    Difficulty questionDifficulty();
}
