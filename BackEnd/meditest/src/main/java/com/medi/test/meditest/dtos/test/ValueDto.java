package com.medi.test.meditest.dtos.test;

import com.medi.test.meditest.dtos.AnswerDto;

import java.util.List;

public class ValueDto {

    private String body;
    private boolean answered;
    private List<AnswerDto> answers;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
