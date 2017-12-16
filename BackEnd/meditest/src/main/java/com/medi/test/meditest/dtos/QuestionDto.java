package com.medi.test.meditest.dtos;

import com.medi.test.meditest.entities.enums.QuestionDifficulty;
import com.medi.test.meditest.entities.enums.QuestionType;

import java.util.List;

public class QuestionDto {

    private String body;

    private QuestionDifficulty difficulty;

    private DomainDto domain;

    public List<AnswerDto> answers;

    private QuestionType type;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public QuestionDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(QuestionDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public DomainDto getDomain() {
        return domain;
    }

    public void setDomain(DomainDto domain) {
        this.domain = domain;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
