package com.medi.test.meditest.dtos.test.score;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.entities.enums.QuestionType;

import java.util.List;

public class QuestionDto {

    private QuestionType key;
    private List<AnswerDto> answers;

    public QuestionType getKey() {
        return key;
    }
    public void setKey(QuestionType key) {
        this.key = key;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }
    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
