package com.medi.test.meditest.services.implementation.test.implementation;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.dtos.test.simple.test.dto.SimpleTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.ComplexTestQuestionDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchAnswerDto;
import com.medi.test.meditest.dtos.test.single.match.dto.SingleMatchQuestionDto;
import com.medi.test.meditest.entities.enums.QuestionType;
import javafx.util.Pair;

import java.util.List;
import java.util.Random;

import static com.medi.test.meditest.services.implementation.test.implementation.TestServiceUtils.shuffle;
import static com.medi.test.meditest.services.implementation.test.implementation.TestServiceUtils.transform;

class QuestionGenerator {
    QuestionGenerator() {
    }

    void addQuestionsToTest(TestDto test, List<QuestionDto> questionsOfSameDifficulty,
                               List<QuestionDto> possibleQuestions,
                               List<QuestionDto> questionWithDiffDifficulty,
                               int numberOfQuestions) {
        Random r = new Random();

        while (numberOfQuestions > 0) {
            QuestionDto nextQuestion = getNextQuestion(r.nextInt(10), questionWithDiffDifficulty,
                    questionsOfSameDifficulty);

            if (nextQuestion.getType() != QuestionType.SingleMatch) {
                addQuestionToTest(nextQuestion, test, questionsOfSameDifficulty, questionWithDiffDifficulty);

            } else {
                List<QuestionDto> picked = TestServiceUtils.createSingleMatchQuestion(possibleQuestions);

                if (picked != null) {
                    addSingleMatchQuestionToTest(picked, test, questionsOfSameDifficulty,
                            questionWithDiffDifficulty);
                }
            }
            numberOfQuestions -= 1;
        }
    }

    private void addSingleMatchQuestionToTest(List<QuestionDto> picked, TestDto test,
                                                                List<QuestionDto> questionsOfSameDifficulty,
                                                                List<QuestionDto> questionWithDiffDifficulty) {
        ComplexTestQuestionDto next = normalizeToSingleMatch(picked);

        test.addQuestion(QuestionType.SingleMatch, next);
        questionsOfSameDifficulty.removeAll(picked);
        questionWithDiffDifficulty.removeAll(picked);
    }


    private void addQuestionToTest(QuestionDto nextQuestion, TestDto test,
                                   List<QuestionDto> questionsOfSameDifficulty,
                                   List<QuestionDto> questionWithDiffDifficulty) {
        test.addQuestion(nextQuestion.getType(), new SimpleTestQuestionDto(nextQuestion));
        questionsOfSameDifficulty.remove(nextQuestion);
        questionWithDiffDifficulty.remove(nextQuestion);
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
