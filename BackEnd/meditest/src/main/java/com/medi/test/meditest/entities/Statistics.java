package com.medi.test.meditest.entities;

import com.medi.test.meditest.entities.enums.Difficulty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STATISTICS")
public class Statistics {

    private int id;
    private User user;
    private int score;
    private Difficulty difficulty;
    private String domain;
    private Date created;

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

    @Column(name = "DOMAIN")
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Column(name = "CREATED", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
}
