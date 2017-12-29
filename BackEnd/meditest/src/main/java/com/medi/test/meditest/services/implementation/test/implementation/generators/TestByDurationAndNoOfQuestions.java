package com.medi.test.meditest.services.implementation.test.implementation.generators;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.IQuestionService;
import com.medi.test.meditest.services.contracts.test.generator.ITestByDurationAndNoOfQuestions;
import com.medi.test.meditest.services.implementation.test.implementation.generators.stop.criteria.TestByDurAndNoOfQuestsStopCriterion;
import com.medi.test.meditest.services.implementation.test.implementation.utils.QuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestByDurationAndNoOfQuestions implements ITestByDurationAndNoOfQuestions {
    @Autowired
    private IDomainService domainService;

    @Autowired
    private IQuestionService questionService;

    @Override
    public TestDto generateTest(DomainDto domain, double duration, int numberOfQuestions) {
        double averageDurationPerQuestion = duration / numberOfQuestions;

        Set<Domain> domains = domainService.getDomainsByDifficulty(domain, Difficulty.Hard);

        List<QuestionDto> possibleQuestions = questionService.getQuestionsByDomains(domains);

        List<QuestionDto> favorableQuestions = getQuestionsCloseToAverageDuration(possibleQuestions,
                averageDurationPerQuestion);

        List<QuestionDto> notFavorableQuestions = getQuestionsFarFromAverageDuration(possibleQuestions,
                averageDurationPerQuestion);

        if (favorableQuestions.size() < numberOfQuestions)
            return null;

        TestDto test = new TestDto();
        test.setDomain(domain);
        test.setDuration((int) duration);

        new QuestionGenerator().addQuestionsToTest(test,
                possibleQuestions,
                favorableQuestions,
                notFavorableQuestions,
                new TestByDurAndNoOfQuestsStopCriterion(numberOfQuestions, duration));

        test.setDifficulty(Difficulty.inferTestDifficulty(test.getQuestions()));
        return test;
    }

    private List<QuestionDto> getQuestionsCloseToAverageDuration(List<QuestionDto> possibleQuestions,
                                                                 double averageDurationPerQuestion) {
        possibleQuestions.sort(Comparator
                .comparingDouble(o -> Math.abs(o.getExpectedSecsToAnswer() - averageDurationPerQuestion)));

        double averageDeviation = possibleQuestions.stream()
                .map(q -> Math.abs(q.getExpectedSecsToAnswer() - averageDurationPerQuestion))
                .reduce(Double::sum).orElse(0.0) / possibleQuestions.size();

        return possibleQuestions.stream()
                .filter(q -> Math.abs(q.getExpectedSecsToAnswer() - averageDurationPerQuestion) <= averageDeviation)
                .collect(Collectors.toList());
    }

    private List<QuestionDto> getQuestionsFarFromAverageDuration(List<QuestionDto> possibleQuestions,
                                                                 double averageDurationPerQuestion) {
        possibleQuestions.sort(Comparator
                .comparingDouble(o -> Math.abs(o.getExpectedSecsToAnswer() - averageDurationPerQuestion)));

        double averageDeviation = possibleQuestions.stream()
                .map(q -> Math.abs(q.getExpectedSecsToAnswer() - averageDurationPerQuestion))
                .reduce(Double::sum).orElse(0.0) / possibleQuestions.size();

        return possibleQuestions.stream()
                .filter(q -> Math.abs(q.getExpectedSecsToAnswer() - averageDurationPerQuestion) < averageDeviation)
                .collect(Collectors.toList());
    }
}
