package com.medi.test.meditest.dtos;

public class AnswerDto {

    public String body;
    public boolean isCorrect;

    public AnswerDto() {
    }

    public AnswerDto(String body, boolean isCorrect) {
        this.body = body;
        this.isCorrect = isCorrect;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }
    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }
}
