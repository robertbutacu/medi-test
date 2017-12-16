package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.Transformers.QuestionTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.dtos.test.simple.test.dto.SimpleTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.ComplexTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.ITestService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Override
    public TestDto getTest(DomainDto domainDto, Difficulty difficulty, int numberOfQuestions) {
        if (numberOfQuestions < 5)
            return null;

        List<QuestionDto> possibleQuestions = getAllQuestionsByDifficultyAndDomain(difficulty, domainDto);

        if (possibleQuestions.size() < numberOfQuestions)
            return null;

        TestDto test = new TestDto();

        test.setDifficulty(difficulty.toString());
        test.setDomain(domainDto);

        Random randomQuestion = new Random();

        while (numberOfQuestions > 0) {
            QuestionDto nextQuestion = possibleQuestions.get(randomQuestion.nextInt(possibleQuestions.size()));

            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                test.addQuestion(nextQuestion.getType(), new SimpleTestQuestionDto(nextQuestion));
                possibleQuestions.remove(nextQuestion);
                numberOfQuestions -= 1;
            } else {
                Pair<Integer, List<QuestionDto>> picked = createSingleMatchQuestion(possibleQuestions);
                if (picked != null) {
                    numberOfQuestions -= picked.getKey();

                    List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transformed = picked.getValue()
                            .stream()
                            .map(QuestionTransformer::toSingleMatchDto)
                            .collect(Collectors.toList());

                    indexSingleMatchQuestion(transformed);

                    test.addQuestion(QuestionType.SingleMatch, new ComplexTestQuestionDto(transformed));
                    possibleQuestions.removeAll(picked.getValue());
                }
            }
        }

        return test;
    }

    private void indexSingleMatchQuestion(List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> questions) {
        for (int i = 0; i < questions.size(); i++) {
            Pair<SingleMatchQuestionDto, SingleMatchAnswerDto> curr = questions.get(i);

            curr.getValue().setMatchQuestionId(i);
            curr.getKey().setMatchAnswerId(i);
        }
    }

    private List<QuestionDto> getAllQuestionsByDifficultyAndDomain(Difficulty difficulty, DomainDto domainDto) {
        return questionsRepository.findByDifficulty(difficulty)
                .stream()
                .map(QuestionTransformer::toDto)
                .filter(q -> q.getDomain() == domainDto)
                .collect(Collectors.toList());
    }

    private Pair<Integer, List<QuestionDto>> createSingleMatchQuestion(List<QuestionDto> questions) {
        List<QuestionDto> matches = questions.stream()
                .filter(q -> q.getType() == QuestionType.SingleMatch)
                .collect(Collectors.toList());

        if (matches.size() < 3)
            return null;

        List<Integer> questionsPickedIndexes = new ArrayList<>();
        List<QuestionDto> questionsPicked = new ArrayList<>();
        Random r = new Random();

        while (questionsPickedIndexes.size() < 3) {
            int next = r.nextInt(matches.size());

            questionsPicked.add(matches.get(next));
            questionsPickedIndexes.add(next);
        }

        return new Pair<>(questionsPickedIndexes.size(), questionsPicked);
    }
}
