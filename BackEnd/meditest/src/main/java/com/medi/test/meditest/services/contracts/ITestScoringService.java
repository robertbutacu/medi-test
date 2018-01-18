package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.test.SolvedTestDto;
import com.medi.test.meditest.dtos.test.score.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import javafx.util.Pair;

public interface ITestScoringService {
    int getScore(long userId, TestDto testDto);
}
