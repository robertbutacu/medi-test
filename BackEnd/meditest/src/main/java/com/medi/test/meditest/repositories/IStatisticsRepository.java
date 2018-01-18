package com.medi.test.meditest.repositories;

import com.medi.test.meditest.entities.Statistics;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatisticsRepository extends PagingAndSortingRepository<Statistics, Integer> {

    Statistics findByIdAndUserId(int id, long userId);

    List<Statistics> findByUserIdOrderByIdDesc(long userId);
}
