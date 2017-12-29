package com.medi.test.meditest.services.implementation.test.implementation.generators;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.IQuestionService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByNoOfQuestionsAndDifficulty;
import com.medi.test.meditest.services.contracts.test.generator.stop.criteria.ITestGeneratorStopCriterion;
import com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria.TestByNoOfQuestsAndDiffStopCriterion;
import com.medi.test.meditest.services.implementation.test.implementation.utils.DurationCalculator;
import com.medi.test.meditest.services.implementation.test.implementation.utils.QuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestByNoOfQuestionsAndDifficulty implements ITestByNoOfQuestionsAndDifficulty {
    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private IDomainService domainService;

    @Autowired
    private IQuestionService questionService;

    /*
    For number of questions and test difficulty:
    Pick questions of test difficulty with a probability of 30-40% of questions to be from a lower/higher rank.
    Also, the duration will be given by:
        If the test is easy : for medium-hard questions, multiply by 1.5 - 2 respectively
           the test is medium : divide by 0.75 for easy, and 1.5 for hard.
           the test is hard : divide by 0.75 for medium, and 0.5 for medium.
           -> the easiest, start with this.
     */
    @Override
    public TestDto generateTest(DomainDto domain, int numberOfQuestions, Difficulty difficulty) {
        Set<Domain> domains = domainService.getDomainsByDifficulty(domain, difficulty);

        List<QuestionDto> possibleQuestions = questionService.getQuestionsByDomains(domains);

        List<QuestionDto> questionsOfSameDifficulty = getQuestionsOfSameDifficulty(possibleQuestions, difficulty);
        List<QuestionDto> questionWithDiffDifficulty = getQuestionsWithDiffDifficulty(possibleQuestions, difficulty);

        if (questionsOfSameDifficulty.size() < numberOfQuestions)
            return null;

        TestDto test = new TestDto(domain, difficulty);

        new QuestionGenerator().addQuestionsToTest(test,
                possibleQuestions,
                questionsOfSameDifficulty,
                questionWithDiffDifficulty,
                new TestByNoOfQuestsAndDiffStopCriterion(numberOfQuestions));

        new DurationCalculator().computeDuration(test, difficulty);

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
