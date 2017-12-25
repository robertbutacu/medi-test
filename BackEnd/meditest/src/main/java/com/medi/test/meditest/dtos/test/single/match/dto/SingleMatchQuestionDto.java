package com.medi.test.meditest.dtos.test.single.match.dto;

import com.medi.test.meditest.dtos.QuestionDto;

public class SingleMatchQuestionDto {
    private int matchAnswerId;

    private String body;

    private int expectedSecsToAnswer ;

    public int getMatchAnswerId() {
        return matchAnswerId;
    }

    public void setMatchAnswerId(int matchAnswerId) {
        this.matchAnswerId = matchAnswerId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getExpectedSecsToAnswer() {
        return expectedSecsToAnswer;
    }

    public void setExpectedSecsToAnswer(int expectedSecsToAnswer) {
        this.expectedSecsToAnswer = expectedSecsToAnswer;
    }
}
