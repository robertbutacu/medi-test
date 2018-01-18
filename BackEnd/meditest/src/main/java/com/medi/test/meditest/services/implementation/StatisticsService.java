package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.Transformers.StatisticsTransformer;
import com.medi.test.meditest.dtos.StatisticsDto;
import com.medi.test.meditest.entities.Statistics;
import com.medi.test.meditest.repositories.IStatisticsRepository;
import com.medi.test.meditest.services.contracts.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService implements IStatisticsService {

    @Autowired
    private IStatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsTransformer statisticsTransformer;

    private List<StatisticsDto> convertListToDTO(List<Statistics> list) {
        return list.stream().map(statisticsTransformer::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StatisticsDto> getStatisticsForCurrentUser(long userId) {
        return convertListToDTO(statisticsRepository.findByUserIdOrderByIdDesc(userId));
    }

    @Override
    public void createStatisticsForCurrentUser(StatisticsDto statisticsDto, long userId) {
        statisticsDto.setUserId(userId);
        statisticsDto.setCreated(new Date());
        statisticsRepository.save(statisticsTransformer.toModel(statisticsDto));
    }

    @Override
    public boolean deleteStatisticsForCurrentUser(int id, long userId) {
        if (statisticsRepository.findByIdAndUserId(id, userId) != null) {
            statisticsRepository.delete(id);
            return true;
        }
        return false;
    }
}
