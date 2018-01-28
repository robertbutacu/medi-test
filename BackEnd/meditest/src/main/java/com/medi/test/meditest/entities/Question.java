package com.medi.test.meditest.entities;

import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Question")
@Table(name = "questions")
public class Question {
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    private Domain domain;


    @NotNull
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @NotNull
    @Column(name = "body")
    @Length(max = 500)
    private String body;

    @Column(name = "question_type")
    private QuestionType questionType;

    @NotNull
    @Column(name = "expectedSecsToAnswer")
    private int expectedSecsToAnswer = 20;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    public List<Answer> answers = new ArrayList<>();


    public Question() {
    }

    public QuestionType getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public int getExpectedSecsToAnswer() {
        return expectedSecsToAnswer;
    }

    public void setExpectedSecsToAnswer(int expectedSecsToAnswer) {
        this.expectedSecsToAnswer = expectedSecsToAnswer;
    }
}
