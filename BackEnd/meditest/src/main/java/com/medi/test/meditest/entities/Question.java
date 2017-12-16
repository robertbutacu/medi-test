package com.medi.test.meditest.entities;

import com.medi.test.meditest.entities.enums.QuestionDifficulty;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "body")
    @Length(max = 500)
    private String body;

    @NotNull
    @Column(name = "difficulty")
    private QuestionDifficulty difficulty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    public List<Answer> answers = new ArrayList<>();

    @Column(name = "question_type")
    private QuestionType questionType;

    public Question() {  }

    public QuestionType getQuestionType(){ return this.questionType; }

    public void setQuestionType(QuestionType questionType) { this.questionType = questionType; }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId() { }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public QuestionDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(QuestionDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
