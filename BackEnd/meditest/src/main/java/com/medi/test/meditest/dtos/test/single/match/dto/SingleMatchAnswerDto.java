package com.medi.test.meditest.dtos.test.single.match.dto;

public class SingleMatchAnswerDto {
    private String body;

    private int matchQuestionId;

    public int getMatchQuestionId() {
        return matchQuestionId;
    }

    public void setMatchQuestionId(int matchQuestionId) {
        this.matchQuestionId = matchQuestionId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
