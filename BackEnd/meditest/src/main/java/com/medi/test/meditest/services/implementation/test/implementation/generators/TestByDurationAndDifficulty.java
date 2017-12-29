package com.medi.test.meditest.services.implementation.test.implementation.generators;

import com.medi.test.meditest.Transformers.DomainTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.IQuestionService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndDifficulty;
import com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria.TestByDurationAndDiffStopCriterion;
import com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria.TestByNoOfQuestsAndDiffStopCriterion;
import com.medi.test.meditest.services.implementation.test.implementation.utils.DurationCalculator;
import com.medi.test.meditest.services.implementation.test.implementation.utils.QuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestByDurationAndDifficulty implements ITestByDurationAndDifficulty {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    @Autowired
    private IQuestionService questionService;

    /*
    For duration and test difficulty:
    Look for questions that fit the test difficulty criteria and get all the matching questions.
            Also, provide a probability of around 30-40% where a question of different difficulty will be picked
    Be careful to normalize that question to the test difficulty.
    If the test is easy : for medium-hard questions, multiply by 1.5 - 2 respectively
    the test is medium : divide by 0.75 for easy, and 1.5 for hard.
    the test is hard : divide by 0.75 for medium, and 0.5 for medium.
    Fetch questions while duration > 0. - the second easiest
    */

    @Override
    public TestDto generateTest(DomainDto domain, Difficulty difficulty, double duration) {
        Set<Domain> domains = domainService.getDomainsByDifficulty(domain, difficulty);

        List<QuestionDto> possibleQuestions = questionService.getQuestionsByDomains(domains);

        List<QuestionDto> questionsOfSameDifficulty = getQuestionsOfSameDifficulty(possibleQuestions, difficulty);
        List<QuestionDto> questionWithDiffDifficulty = getQuestionsWithDiffDifficulty(possibleQuestions, difficulty);

        if (questionsOfSameDifficulty.size() == 0)
            return null;

        TestDto test = new TestDto(domain, difficulty);

        new QuestionGenerator().addQuestionsToTest(test,
                possibleQuestions,
                questionsOfSameDifficulty,
                questionWithDiffDifficulty,
                new TestByDurationAndDiffStopCriterion(duration));

        new DurationCalculator().computeDuration(test);

        return test;
    }

    private List<QuestionDto> getQuestionsOfSameDifficulty(List<QuestionDto> possibleQuestions,
                                                           Difficulty difficulty) {
        return possibleQuestions.stream()
                .filter(q -> q.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    private List<QuestionDto> getQuestionsWithDiffDifficulty(List<QuestionDto> possibleQuestions,
                                                             Difficulty difficulty) {
        return possibleQuestions.stream()
                .filter(q -> q.getDifficulty() != difficulty)
                .collect(Collectors.toList());
    }
}
