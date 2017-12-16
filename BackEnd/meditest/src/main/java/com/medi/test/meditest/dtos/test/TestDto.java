package com.medi.test.meditest.dtos.test;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.enums.QuestionType;
import javafx.util.Pair;

import java.util.List;

public class TestDto {
    private DomainDto domain;

    private String difficulty;

    private List<Pair<QuestionType, ITestQuestion>> questions;

    public TestDto(DomainDto domain, String difficulty, List<Pair<QuestionType, ITestQuestion>> questions){
        this.domain = domain;
        this.difficulty = difficulty;
        this.questions = questions;
    }

    public DomainDto getDomain() {
        return domain;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<Pair<QuestionType, ITestQuestion>> getQuestions() {
        return questions;
    }
}
