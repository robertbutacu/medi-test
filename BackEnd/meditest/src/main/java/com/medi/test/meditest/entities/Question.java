package com.medi.test.meditest.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Question")
@Table(name = "questions")
public class Question {
    private static long serialVersionUID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "body")
    @Length(max = 500)
    private String body;

    @NotNull
    @Column(name = "difficulty")
    @Length(max = 30)
    private String difficulty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    public List<Answer> answers = new ArrayList<>();

    @Column(name = "question_type")
    private QuestionType questionType;

    public Question() {  }
//this.id = serialVersionUID; serialVersionUID += 1;
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

    public void setId() { this.id = serialVersionUID; serialVersionUID += 1; }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
