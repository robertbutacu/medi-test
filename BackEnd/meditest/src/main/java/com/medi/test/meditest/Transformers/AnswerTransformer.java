package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.entities.Answer;

class AnswerTransformer {
    static AnswerDto toDto(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setBody(answer.getBody());
        answerDto.setIsCorrect(answer.getIsCorrect());
        return answerDto;
    }

    static Answer toEntity(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setBody(answerDto.body);
        answer.setIsCorrect(answerDto.isCorrect);
        return answer;
    }
}
