package com.medi.test.meditest.dtos;

import com.medi.test.meditest.entities.User;
import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.Date;

public class StatisticsDto {

    private int id;
    private long userId;
    private int score;
    private Difficulty difficulty;
    private String domain;
    private Date created;
    private String createdFormatted;

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

    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedFormatted() {
        return createdFormatted;
    }
    public void setCreatedFormatted(String createdFormatted) {
        this.createdFormatted = createdFormatted;
    }
}
