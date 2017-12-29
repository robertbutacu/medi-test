package com.medi.test.meditest.dtos.test.simple.test.dto;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.ITestQuestion;
import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.List;
import java.util.Optional;

public class SimpleTestQuestionDto implements ITestQuestion {
    private boolean answered;

    private String body;

    private List<AnswerDto> answers;

    private int expectedSecsToAnswer;

    private Difficulty difficulty;

    public SimpleTestQuestionDto(QuestionDto question) {
        this.body = question.getBody();
        this.answers = question.getAnswers();
        this.expectedSecsToAnswer = question.getExpectedSecsToAnswer();
        this.difficulty = question.getDifficulty();
    }

    @Override
    public Optional<Integer> computeEstimatedDuration(Difficulty testDifficulty) {
        if (this.difficulty == null || testDifficulty == null)
            return Optional.empty();

        return Optional.of(computeDuration(this.expectedSecsToAnswer, this.difficulty, testDifficulty));
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
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

    private int computeDuration(int questionDuration, Difficulty questionDifficulty, Difficulty testDifficulty) {
        double duration = Difficulty.normalizeDuration(questionDuration, questionDifficulty, testDifficulty);

        return (int) Math.round(duration / 5) * 5;
    }
}
