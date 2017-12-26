package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.Transformers.DomainTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.repositories.IDomainRepository;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DomainService implements IDomainService {

    @Autowired
    private IDomainRepository domainRepository;

    @Autowired
    private IQuestionRepository questionRepository;


    @Override
    public void save(Domain entity) {
        this.domainRepository.save(entity);
    }

    @Override
    public List<Domain> getAll() {
        return domainRepository.findAll();
    }

    @Override
    public Domain getById(Long id) {
        return domainRepository.findOne(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        domainRepository.delete(id);
    }

    @Override
    public List<DomainDto> getAllDomains() {
        return this.getAll().stream()
                .filter(d -> questionRepository.findByDomain(d).size() > 10)
                .map(DomainTransformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Set<DomainDto> getDomainsByDifficulty(DomainDto domain, Difficulty difficulty) {
        Set<Domain> all = new HashSet<>(this.getAll());

        Domain toSearch = new Domain();
        for (Domain d :
                all) {
            if (d.getName().equals(domain.getDomain()))
                toSearch = d;
        }

        if (toSearch.getName() == null)
            return null;

        switch (difficulty) {
            case Easy:
                return transform(getRelatedDomains(toSearch, all, 1, new HashSet<>()));
            case Medium:
                return transform(getRelatedDomains(toSearch, all, 2, new HashSet<>()));
            case Hard:
                return transform(getRelatedDomains(toSearch, all, 3, new HashSet<>()));
            default:
                return null;
        }
    }

    private Set<DomainDto> transform(Set<Domain> domains) {
        return domains.stream()
                .map(DomainTransformer::toDto)
                .collect(Collectors.toSet());
    }

    private Set<Domain> getRelatedDomains(Domain curr, Set<Domain> all, int maxDepth, Set<Domain> visited) {
        if (maxDepth == 0)
            return new HashSet<>();

        Set<Domain> allDomains = new HashSet<>();

        visited.add(curr);

        allDomains.add(curr);
        allDomains.addAll(curr.getRelatedDomains());

        curr.getRelatedDomains()
                .stream()
                .filter(d -> !visited.contains(d))
                .forEach(d -> allDomains
                        .addAll(getRelatedDomains(d, all, maxDepth - 1, visited)));

        return allDomains;
    }
}
