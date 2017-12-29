package com.medi.test.meditest.dtos.test.single.match.dto;

import com.medi.test.meditest.dtos.test.ITestQuestion;
import com.medi.test.meditest.entities.enums.Difficulty;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public class ComplexTestQuestionDto implements ITestQuestion {
    private List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches;

    private boolean answered;

    private int expectedSecsToAnswer;

    private Difficulty difficulty;

    public ComplexTestQuestionDto(List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches) {
        this.matches = matches;
        this.expectedSecsToAnswer = matches.stream()
                .map(p -> p.getKey().getExpectedSecsToAnswer())
                .reduce(0, Integer::sum);

        if (matches.size() > 0)
            this.difficulty = matches.get(0).getKey().getDifficulty();
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

    @Override
    public Optional<Integer> computeEstimatedDuration(Difficulty testDifficulty) {
        if (testDifficulty == null)
            return Optional.empty();

        return Optional.of(computeDuration(this.expectedSecsToAnswer, this.difficulty, testDifficulty));
    }

    private int computeDuration(int questionDuration, Difficulty questionDifficulty, Difficulty testDifficulty) {
        double duration = Difficulty.normalizeDuration(questionDuration, questionDifficulty, testDifficulty);

        return (int) Math.round(duration / 5) * 5;
    }
}
