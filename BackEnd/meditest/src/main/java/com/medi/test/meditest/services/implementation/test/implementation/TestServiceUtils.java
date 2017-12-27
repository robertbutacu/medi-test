package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.Transformers.QuestionTransformer;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.enums.QuestionType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class TestServiceUtils {
    static List<QuestionDto> createSingleMatchQuestion(List<QuestionDto> questions) {
        List<QuestionDto> matches = questions.stream()
                .filter(q -> q.getType() == QuestionType.SingleMatch)
                .collect(Collectors.toList());

        if (matches.size() < 3)
            return null;

        return pickQuestions(new ArrayList<>(), matches, 3);
    }

    static void indexSingleMatchQuestion(List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> questions) {
        for (int i = 0; i < questions.size(); i++) {
            Pair<SingleMatchQuestionDto, SingleMatchAnswerDto> curr = questions.get(i);

            curr.getValue().setMatchQuestionId(i);
            curr.getKey().setMatchAnswerId(i);
        }
    }

    static List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transform(List<QuestionDto> picked) {
        return picked
                .stream()
                .map(QuestionTransformer::toSingleMatchDto)
                .collect(Collectors.toList());
    }

    private static List<QuestionDto> pickQuestions(List<QuestionDto> pickedQuestions,
                                                   List<QuestionDto> questions,
                                                   int picked){
        if(picked == 0)
            return pickedQuestions;

        int nextQuestion = getRandomNumber(questions.size());

        pickedQuestions.add(questions.get(nextQuestion));
        questions.remove(nextQuestion);

        return pickQuestions(pickedQuestions, questions, picked - 1);
    }

    private static int getRandomNumber(int boundary){
        Random r = new Random();

        return r.nextInt(boundary);
    }
}
