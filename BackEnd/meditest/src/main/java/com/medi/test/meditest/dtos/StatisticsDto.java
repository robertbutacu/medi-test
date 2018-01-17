package com.medi.test.meditest.dtos;

import com.medi.test.meditest.entities.User;

public class StatisticsDto {

    private int id;
    private long userId;
    private UserDto userDto;
    private int score;
    private String difficulty;

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

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
