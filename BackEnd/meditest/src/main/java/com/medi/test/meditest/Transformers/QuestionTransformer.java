package com.medi.test.meditest.transformers;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.simple.test.dto.SimpleTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.Answer;
import com.medi.test.meditest.entities.Question;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        question.setQuestionType(questionDto.getType());
        return question;
    }

    public static Pair<SingleMatchQuestionDto, SingleMatchAnswerDto> toSingleMatchDto(QuestionDto question) {
        SingleMatchAnswerDto smad = new SingleMatchAnswerDto();
        SingleMatchQuestionDto smqd = new SingleMatchQuestionDto();

        smad.setBody(question.answers.get(0).getBody());

        smqd.setBody(question.getBody());

        return new Pair<>(smqd, smad);
    }

    public static SimpleTestQuestionDto toSimpleQuestionDto(QuestionDto questionDto){
        return new SimpleTestQuestionDto(questionDto);
    }
}
