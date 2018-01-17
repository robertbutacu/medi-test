package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.StatisticsDto;

import java.util.List;

public interface IStatisticsService {

    List<StatisticsDto> getStatisticsForCurrentUser(long userId);
    void createStatisticsForCurrentUser(StatisticsDto statisticsDto, long userId);
    boolean deleteStatisticsForCurrentUser(int id, long userId);
}
