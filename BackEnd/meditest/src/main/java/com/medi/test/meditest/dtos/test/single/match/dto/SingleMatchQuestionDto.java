package com.medi.test.meditest.dtos.test.single.match.dto;

import com.medi.test.meditest.dtos.QuestionDto;

public class SingleMatchQuestionDto {
    private int correctAnswerMatchId;
    private QuestionDto question;

    public SingleMatchQuestionDto(int correctAnswerMatchId, QuestionDto question){
        this.correctAnswerMatchId = correctAnswerMatchId;
        this.question = question;
    }

    public int getCorrectAnswerMatchId() {
        return correctAnswerMatchId;
    }

    public QuestionDto getQuestion() {
        return question;
    }
}
