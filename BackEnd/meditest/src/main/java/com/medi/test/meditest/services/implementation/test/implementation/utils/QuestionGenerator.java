package com.medi.test.meditest.services.implementation.test.implementation.utils;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.dtos.test.simple.test.dto.SimpleTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.ComplexTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.services.contracts.test.generator.stop.criteria.ITestGeneratorStopCriterion;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.medi.test.meditest.services.implementation.test.implementation.utils.TestServiceUtils.shuffle;
import static com.medi.test.meditest.services.implementation.test.implementation.utils.TestServiceUtils.transform;


public class QuestionGenerator {
    public QuestionGenerator() {
    }

    public void addQuestionsToTest(TestDto test, List<QuestionDto> questionsOfSameDifficulty,
                                   List<QuestionDto> possibleQuestions,
                                   List<QuestionDto> questionWithDiffDifficulty,
                                   ITestGeneratorStopCriterion stopCriterion) {
        Random r = new Random();
        Optional<Integer> currentQuestionDuration = Optional.empty();

        while (stopCriterion.hasToGenerate()) {
            QuestionDto nextQuestion = getNextQuestion(r.nextInt(10), questionWithDiffDifficulty,
                    questionsOfSameDifficulty);

            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                currentQuestionDuration = addQuestionToTest(nextQuestion, test,
                        questionsOfSameDifficulty, questionWithDiffDifficulty);
            } else {
                //TODO when time, change this so it picks from questionsOfSame/WithDiff(Difficulty)
                List<QuestionDto> picked = TestServiceUtils.createSingleMatchQuestion(possibleQuestions);

                if (picked != null) {
                    currentQuestionDuration = addSingleMatchQuestionToTest(picked, test, questionsOfSameDifficulty,
                            questionWithDiffDifficulty);
                }
            }
            stopCriterion.decrement(currentQuestionDuration.orElse(0));
        }
    }

    private Optional<Integer> addSingleMatchQuestionToTest(List<QuestionDto> picked, TestDto test,
                                              List<QuestionDto> questionsOfSameDifficulty,
                                              List<QuestionDto> questionWithDiffDifficulty) {
        ComplexTestQuestionDto next = normalizeToSingleMatch(picked);

        test.addQuestion(QuestionType.SingleMatch, next);
        questionsOfSameDifficulty.removeAll(picked);
        questionWithDiffDifficulty.removeAll(picked);

        return next.computeEstimatedDuration(test.getDifficulty());
    }


    private Optional<Integer> addQuestionToTest(QuestionDto nextQuestion, TestDto test,
                                                List<QuestionDto> questionsOfSameDifficulty,
                                                List<QuestionDto> questionWithDiffDifficulty) {
        SimpleTestQuestionDto added = new SimpleTestQuestionDto(nextQuestion);
        test.addQuestion(nextQuestion.getType(), added);
        questionsOfSameDifficulty.remove(nextQuestion);
        questionWithDiffDifficulty.remove(nextQuestion);

        return added.computeEstimatedDuration(test.getDifficulty());
    }

    private ComplexTestQuestionDto normalizeToSingleMatch(List<QuestionDto> picked) {
        List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> transformed = transform(picked);

        TestServiceUtils.indexSingleMatchQuestion(transformed);

        return new ComplexTestQuestionDto(shuffle(transformed));
    }

    private QuestionDto getNextQuestion(int differentDifficulty,
                                        List<QuestionDto> questionWithDiffDifficulty,
                                        List<QuestionDto> questionsOfSameDifficulty) {
        QuestionDto nextQuestion;

        if (differentDifficulty >= 7 && questionWithDiffDifficulty.size() > 0)
            nextQuestion = pickQuestion(questionWithDiffDifficulty);
        else
            nextQuestion = pickQuestion(questionsOfSameDifficulty);

        return nextQuestion;
    }

    private QuestionDto pickQuestion(List<QuestionDto> questions) {
        Random r = new Random();

        return questions.get(r.nextInt(questions.size()));
    }
}
