package com.medi.test.meditest.dtos.test.simple.test.dto;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.ITestQuestion;
import com.medi.test.meditest.entities.Answer;

public class SimpleTestQuestionDto implements ITestQuestion {
    private QuestionDto question;

    public SimpleTestQuestionDto(QuestionDto question){
        this.question = question;
    }

    public QuestionDto getQuestion() {
        return question;
    }
}
