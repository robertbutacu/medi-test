package com.medi.test.meditest.dtos;

import com.medi.test.meditest.entities.User;
import com.medi.test.meditest.entities.enums.Difficulty;

public class StatisticsDto {

    private int id;
    private long userId;
    private UserDto userDto;
    private int score;
    private Difficulty difficulty;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserDto getUserDto() {
        return userDto;
    }
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
