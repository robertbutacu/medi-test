package com.medi.test.meditest.dtos;

import com.medi.test.meditest.dtos.test.ValueDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;

import java.util.List;

public class QuestionDto {

    private String body;

    private Difficulty difficulty;

    private DomainDto domain;

    private ValueDto value;

    private QuestionType key;

    private int expectedSecsToAnswer;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public DomainDto getDomain() {
        return domain;
    }

    public void setDomain(DomainDto domain) {
        this.domain = domain;
    }

    public ValueDto getValue() {
        return value;
    }

    public void setValue(ValueDto value) {
        this.value = value;
    }

    public QuestionType getKey() {
        return key;
    }

    public void setKey(QuestionType key) {
        this.key = key;
    }

    public int getExpectedSecsToAnswer() {
        return expectedSecsToAnswer;
    }

    public void setExpectedSecsToAnswer(int expectedSecsToAnswer) {
        this.expectedSecsToAnswer = expectedSecsToAnswer;
    }
}
