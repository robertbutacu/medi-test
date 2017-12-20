package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.dtos.test.SolvedTestDto;
import com.medi.test.meditest.services.contracts.ITestScoringService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class TestScoringService implements ITestScoringService{
    @Override
    public Pair<Double, Double> getScore(SolvedTestDto testToScore) {
        return new Pair(100,100);
    }
}