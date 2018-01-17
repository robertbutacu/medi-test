package com.medi.test.meditest.entities;

import com.medi.test.meditest.entities.enums.Difficulty;

import javax.persistence.*;

@Entity
@Table(name = "STATISTICS")
public class Statistics {

    private int id;
    private User user;
    private int score;
    private Difficulty difficulty;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "SCORE")
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    @Column(name = "DIFFICULTY")
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
