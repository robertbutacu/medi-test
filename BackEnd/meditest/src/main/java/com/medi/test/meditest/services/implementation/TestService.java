package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.transformers.QuestionTransformer;
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
import java.util.Objects;
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

        TestDto test = new TestDto(domainDto, difficulty);

        Random randomQuestion = new Random();

        while (numberOfQuestions > 0) {
            QuestionDto nextQuestion = possibleQuestions.get(randomQuestion.nextInt(possibleQuestions.size()));

            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                test.addQuestion(nextQuestion.getType(), new SimpleTestQuestionDto(nextQuestion));
                possibleQuestions.remove(nextQuestion);
                numberOfQuestions -= 1;
            } else {
                List<QuestionDto> picked = createSingleMatchQuestion(possibleQuestions);
                if (picked != null) {
                    numberOfQuestions -= 1;

                    List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transformed = transform(picked);

                    indexSingleMatchQuestion(transformed);

                    test.addQuestion(QuestionType.SingleMatch, new ComplexTestQuestionDto(shuffle(transformed)));
                    possibleQuestions.removeAll(picked);
                }
            }
        }

        return test;
    }

    private List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> shuffle(
            List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> singleMatchQuestion) {
        List<Integer> pickedAnswersIndexes = new ArrayList<>();
        List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> shuffle = new ArrayList<>();
        Pair<SingleMatchQuestionDto, SingleMatchAnswerDto> curr;
        SingleMatchAnswerDto randomAnswer;

        Random r = new Random();

        for (Pair<SingleMatchQuestionDto, SingleMatchAnswerDto> aSingleMatchQuestion : singleMatchQuestion) {
            curr = aSingleMatchQuestion;

            int random = r.nextInt(singleMatchQuestion.size());
            while (pickedAnswersIndexes.contains(random)) {
                random = r.nextInt(singleMatchQuestion.size());
            }

            pickedAnswersIndexes.add(random);

            randomAnswer = singleMatchQuestion.get(random).getValue();

            shuffle.add(new Pair<>(curr.getKey(), randomAnswer));
        }

        return shuffle;
    }

    private List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transform(List<QuestionDto> picked) {
        return picked
                .stream()
                .map(QuestionTransformer::toSingleMatchDto)
                .collect(Collectors.toList());
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
                .filter(q -> Objects.equals(q.getDomain().getDomain(), domainDto.getDomain()))
                .collect(Collectors.toList());
    }

    private List<QuestionDto> createSingleMatchQuestion(List<QuestionDto> questions) {
        List<QuestionDto> matches = questions.stream()
                .filter(q -> q.getType() == QuestionType.SingleMatch)
                .collect(Collectors.toList());

        if (matches.size() < 3)
            return null;

        List<QuestionDto> questionsPicked = new ArrayList<>();
        Random r = new Random();

        while (questionsPicked.size() < 3) {
            int next = r.nextInt(matches.size());

            questionsPicked.add(matches.get(next));
            matches.remove(next);
        }

        return questionsPicked;
    }
}
