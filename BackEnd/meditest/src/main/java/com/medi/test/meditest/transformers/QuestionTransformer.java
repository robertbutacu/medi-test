package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.entities.Answer;
import com.medi.test.meditest.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionTransformer {
    public static QuestionDto toDto(Question question){
        QuestionDto questionDto = new QuestionDto();
        List<AnswerDto> answers = new ArrayList<>();

        for (Answer answer : question.answers) {
            answers.add(AnswerTransformer.toDto(answer));
        }

        questionDto.setBody(question.body);
        questionDto.setAnswers(answers);
        questionDto.setDifficulty(question.difficulty);
        return questionDto;
    }

    public static Question toEntity(QuestionDto questionDto){
        Question question = new Question();
        List<Answer> answers = new ArrayList<>();

        for (AnswerDto answer : questionDto.answers) {
            answers.add(AnswerTransformer.toEntity(answer));
        }

        question.setBody(question.body);
        question.setAnswers(answers);
        question.setDifficulty(questionDto.getDifficulty());
        return question;
    }
}
