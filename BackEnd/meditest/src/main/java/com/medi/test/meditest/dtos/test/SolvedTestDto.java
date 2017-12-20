package com.medi.test.meditest.dtos.test;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.List;

public class SolvedTestDto {
    private String difficulty;

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
}
