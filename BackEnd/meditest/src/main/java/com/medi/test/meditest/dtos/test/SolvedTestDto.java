package com.medi.test.meditest.dtos.test;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.List;

public class SolvedTestDto {

    private String difficulty;
    private String domain;
    private int duration;
    private List<QuestionDto> questions;

    public SolvedTestDto(Difficulty difficulty, List<QuestionDto> questions) {
        this.difficulty = difficulty.toString();
        this.questions = questions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
