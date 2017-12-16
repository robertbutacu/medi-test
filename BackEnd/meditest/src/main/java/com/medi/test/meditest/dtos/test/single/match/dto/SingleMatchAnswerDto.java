package com.medi.test.meditest.dtos.test.single.match.dto;

import com.medi.test.meditest.dtos.AnswerDto;

public class SingleMatchAnswerDto {
    private int correctQuestionAnswerId;
    private AnswerDto answer;

    public SingleMatchAnswerDto(int correctQuestionAnswerDto, AnswerDto answer){
        this.correctQuestionAnswerId = correctQuestionAnswerDto;
        this.answer = answer;
    }

    public int getCorrectQuestionAnswerDto() {
        return correctQuestionAnswerId;
    }

    public AnswerDto getAnswer() {
        return answer;
    }
}
