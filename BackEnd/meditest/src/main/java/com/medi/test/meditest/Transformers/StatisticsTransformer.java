package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.StatisticsDto;
import com.medi.test.meditest.entities.Statistics;
import com.medi.test.meditest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsTransformer implements ITransformer<Statistics, StatisticsDto> {

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public StatisticsDto toDto(Statistics statistics) {
        StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setId(statistics.getId());
        statisticsDto.setDifficulty(statistics.getDifficulty());
        statisticsDto.setScore(statistics.getScore());
        statisticsDto.setUserId(statistics.getUser().getId());
        statisticsDto.setUserDto(userTransformer.toDto(statistics.getUser()));

        return statisticsDto;
    }

    @Override
    public Statistics toModel(StatisticsDto statisticsDto) {
        Statistics statistics = new Statistics();

        statistics.setId(statisticsDto.getId());
        statistics.setDifficulty(statisticsDto.getDifficulty());
        statistics.setScore(statisticsDto.getScore());

        User user = new User();
        user.setId(statisticsDto.getUserId());
        statistics.setUser(user);

        return statistics;
    }
}
