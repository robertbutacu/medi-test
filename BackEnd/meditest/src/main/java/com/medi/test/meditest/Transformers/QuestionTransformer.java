package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.Answer;
import com.medi.test.meditest.entities.Question;
import com.sun.java.browser.plugin2.DOM;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class QuestionTransformer {
    public static QuestionDto toDto(Question question){
        QuestionDto questionDto = new QuestionDto();
        List<AnswerDto> answers = new ArrayList<>();

        for (Answer answer : question.answers) {
            answers.add(AnswerTransformer.toDto(answer));
        }

        questionDto.setBody(question.getBody());
        questionDto.setAnswers(answers);
        questionDto.setDifficulty(question.getDifficulty());
        questionDto.setDomain(DomainTransformer.toDto(question.getDomain()));
        questionDto.setType(question.getQuestionType());

        return questionDto;
    }

    public static Question toEntity(QuestionDto questionDto){
        Question question = new Question();
        List<Answer> answers = new ArrayList<>();

        for (AnswerDto answer : questionDto.answers) {
            answers.add(AnswerTransformer.toEntity(answer));
        }

        question.setBody(question.getBody());
        question.setAnswers(answers);
        question.setDifficulty(questionDto.getDifficulty());
        return question;
    }

    public static Pair<SingleMatchAnswerDto, SingleMatchQuestionDto> toSingleMatchDto(Question question) {
        SingleMatchAnswerDto smad = new SingleMatchAnswerDto();
        SingleMatchQuestionDto smqd = new SingleMatchQuestionDto();

        smad.setBody(question.answers.get(0).getBody());

        smqd.setBody(question.getBody());

        return new Pair<>(smad, smqd);
    }
}
