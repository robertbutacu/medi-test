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

    private Set<Domain> all;

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
    public Set<Domain> getDomainsByDifficulty(DomainDto domain, Difficulty difficulty) {
        if (this.all == null)
            this.all = new HashSet<>(this.getAll());

        Domain toSearch = new Domain();
        for (Domain d :
                this.all) {
            if (d.getName().equals(domain.getDomain()))
                toSearch = d;
        }

        if (toSearch.getName() == null)
            return null;

        switch (difficulty) {
            case Easy:
                return getRelatedDomains(toSearch, 1, new HashSet<>());
            case Medium:
                return getRelatedDomains(toSearch, 2, new HashSet<>());
            case Hard:
                return getRelatedDomains(toSearch, 3, new HashSet<>());
            default:
                return null;
        }
    }

    private Set<Domain> getRelatedDomains(Domain curr, int maxDepth, Set<Domain> visited) {
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
                        .addAll(getRelatedDomains(d, maxDepth - 1, visited)));

        return allDomains;
    }
}
