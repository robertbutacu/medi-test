package com.medi.test.meditest.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Domain")
@Table(name = "domains")
public class Domain {
    private static final long serialVersionUID = 4885215557937431660L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "name")
    @Length(max = 30)
    private String name;

    @OneToMany(
            mappedBy = "domain",
            cascade = CascadeType.ALL
    )
    public List<Question> questions = new ArrayList<>();

    public Domain() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}