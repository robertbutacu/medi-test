package com.medi.test.meditest.dtos.test;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class TestDto {
    private String domain;

    private String difficulty;

    private List<Pair<QuestionType, ITestQuestion>> questions;

    public TestDto(){
        this.questions = new ArrayList<>();
    }

    public TestDto(DomainDto domain, Difficulty difficulty) {
        this.domain = domain.getDomain();
        this.difficulty = difficulty.toString();
    }

    public TestDto(DomainDto domain, String difficulty, List<Pair<QuestionType, ITestQuestion>> questions){
        this.domain = domain.getDomain();
        this.difficulty = difficulty;
        this.questions = questions;
    }

    public String getDomain() {
        return domain;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<Pair<QuestionType, ITestQuestion>> getQuestions() {
        return questions;
    }

    public void addQuestion(QuestionType questionType, ITestQuestion testQuestion) {
        this.questions.add(new Pair<>(questionType, testQuestion));
    }

    public void setDomain(DomainDto domain) {

        this.domain = domain.getDomain();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
