package com.medi.test.meditest.dtos.test.simple.test.dto;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.ITestQuestion;
import com.medi.test.meditest.entities.Answer;

import java.util.List;

public class SimpleTestQuestionDto implements ITestQuestion {
    private String body;

    private List<AnswerDto> answers;

    public SimpleTestQuestionDto(QuestionDto question) {
        this.body = question.getBody();
        this.answers = question.getAnswers();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
