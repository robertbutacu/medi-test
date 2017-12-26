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
    private List<Question> questions = new ArrayList<>();


    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="domains_hierarchy",
            joinColumns={@JoinColumn(name="current")},
            inverseJoinColumns={@JoinColumn(name="subdomain")})
    private Set<Domain> domains = new HashSet<>();

    @ManyToMany(mappedBy="domains")
    private Set<Domain> teammates = new HashSet<>();

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

    public Set<Domain> getDomains() {
        return domains;
    }

    public void setDomains(Set<Domain> domains) {
        this.domains = domains;
    }

    public Set<Domain> getTeammates() {
        return teammates;
    }

    public void setTeammates(Set<Domain> teammates) {
        this.teammates = teammates;
    }
}
