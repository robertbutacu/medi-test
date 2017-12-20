package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.Transformers.DomainTransformer;
import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.repositories.IDomainRepository;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
