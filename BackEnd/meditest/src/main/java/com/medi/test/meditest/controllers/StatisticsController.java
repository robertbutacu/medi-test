package com.medi.test.meditest.controllers;

import com.medi.test.meditest.dtos.StatisticsDto;
import com.medi.test.meditest.services.contracts.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/v1/statistics")
@RequestMapping("/v1/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<StatisticsDto> getStatisticsForCurrentUser(@RequestParam("userId") long userId) {
        return statisticsService.getStatisticsForCurrentUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createStatisticsForCurrentUser(@RequestBody StatisticsDto statisticsDto,
                                                  @RequestParam("userId") long userId) {
        statisticsService.createStatisticsForCurrentUser(statisticsDto, userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteStatisticsForCurrentUser(@PathVariable("id") int id, @RequestParam("userId") long userId) {
        return statisticsService.deleteStatisticsForCurrentUser(id, userId);
    }
}
