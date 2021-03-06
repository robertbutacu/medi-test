package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.dtos.AnswerDto;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.dtos.test.SolvedTestDto;
import com.medi.test.meditest.dtos.test.score.TestDto;
import com.medi.test.meditest.entities.Statistics;
import com.medi.test.meditest.entities.User;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IStatisticsRepository;
import com.medi.test.meditest.services.contracts.ITestScoringService;
import io.swagger.models.auth.In;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestScoringService implements ITestScoringService {

    @Autowired
    private IStatisticsRepository statisticsRepository;

    @Override
    public int getScore(long userId, TestDto testDto) {

        boolean ok;
        int score = 0;
        for (com.medi.test.meditest.dtos.test.score.QuestionDto questionDto : testDto.getQuestions()) {
            switch (questionDto.getKey()) {
                case SingleMatch:
                    ok=true;
                    for (AnswerDto answerDto : questionDto.getAnswers()) {
                        if(answerDto.getSelectedAnswer() != answerDto.getMatchAnswerId()){
                            ok = false;
                        }
                    }
                    if(ok){
                        score += 1;
                    }
                        break;
                case FillIn:
                    for (AnswerDto answerDto : questionDto.getAnswers()) {
                        if (answerDto.getBody().equals(answerDto.getFillin()))
                            score += 1;
                    }
                    break;
                case TrueOrFalse:
                    for (AnswerDto answerDto : questionDto.getAnswers()) {
                        if ((answerDto.isCorrect())&&(answerDto.isCorrect() == answerDto.isSelected()))
                            score += 1;
                    }
                    break;
                case MultipleChoice:
                    ok = true;
                    for (AnswerDto answerDto : questionDto.getAnswers()) {
                        if ((answerDto.isCorrect())&&(answerDto.isCorrect() != answerDto.isSelected()))
                            ok = false;
                    }
                    if (ok) {
                        score += 1;
                    }
                    break;
            }
        }
        Statistics statistics = new Statistics();
        statistics.setCreated(new Date());
        User user = new User();
        user.setId(userId);
        statistics.setUser(user);
        statistics.setDifficulty(testDto.getDifficulty());
        statistics.setDomain(testDto.getDomain());
        statistics.setScore(score*100/testDto.getQuestions().size());
        return statisticsRepository.save(statistics).getId();
    }
}
