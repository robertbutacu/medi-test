package com.medi.test.meditest.dtos.test.score;

import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.List;

public class TestDto {

    private String domain;
    private Difficulty difficulty;
    private int duration;
    private List<QuestionDto> questions;

    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
