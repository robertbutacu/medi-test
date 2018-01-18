package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.test.SolvedTestDto;
import javafx.util.Pair;

public interface ITestScoringService {
    int getScore(SolvedTestDto testToScore);
}
