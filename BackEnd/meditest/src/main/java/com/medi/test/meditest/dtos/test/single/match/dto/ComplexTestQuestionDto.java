package com.medi.test.meditest.dtos.test.single.match.dto;

import com.medi.test.meditest.dtos.test.ITestQuestion;
import javafx.util.Pair;

import java.util.List;

public class ComplexTestQuestionDto implements ITestQuestion {
    private List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches;

    private boolean answered;

    private int expectedSecsToAnswer;

    public ComplexTestQuestionDto(List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches) {
        this.matches = matches;
        this.expectedSecsToAnswer = matches.stream()
        .map(p -> p.getKey().getExpectedSecsToAnswer())
        .reduce(0, Integer::sum);
    }

    public List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> getMatches() {
        return matches;
    }

    public boolean isAnswered() {
        return answered;
    }

    public int getExpectedSecsToAnswer() {
        return expectedSecsToAnswer;
    }

    public void setExpectedSecsToAnswer(int expectedSecsToAnswer) {
        this.expectedSecsToAnswer = expectedSecsToAnswer;
    }
}
