package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.entities.Answer;

public class AnswerTransformer {
    public static AnswerDto toDto(Answer answer){
        AnswerDto answerDto = new AnswerDto();
        answerDto.setBody(answer.body);
        answerDto.setIsCorrect(answer.isCorrect);
        return answerDto;
    }

    public static Answer toEntity(AnswerDto answerDto){
        Answer answer = new Answer();
        answer.setBody(answerDto.body);
        answer.setIsCorrect(answerDto.isCorrect);
        return answer;
    }
}
