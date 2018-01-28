package com.medi.test.meditest.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Domain")
@Table(name = "domains")
public class Domain {
    @Id
    private long id;

    @NotNull
    @Column(name = "name")
    @Length(max = 50)
    private String name;

    @OneToMany(
            mappedBy = "domain",
            cascade = CascadeType.ALL
    )
    private List<Question> questions = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "domains_hierarchy",
            joinColumns = {@JoinColumn(name = "current")},
            inverseJoinColumns = {@JoinColumn(name = "subdomain")})
    private Set<Domain> relatedDomains = new HashSet<>();

    @ManyToMany(mappedBy = "relatedDomains")
    private Set<Domain> subdomains = new HashSet<>();

    public Domain() {
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

    public Set<Domain> getRelatedDomains() {
        return relatedDomains;
    }

    public void setRelatedDomains(Set<Domain> relatedDomains) {
        this.relatedDomains = relatedDomains;
    }

    public Set<Domain> getSubdomains() {
        return subdomains;
    }

    public void setSubdomains(Set<Domain> subdomains) {
        this.subdomains = subdomains;
    }
}
