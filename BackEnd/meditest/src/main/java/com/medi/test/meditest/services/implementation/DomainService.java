package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.repositories.IDomainRepository;
import com.medi.test.meditest.services.contracts.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService implements IDomainService{

    @Autowired
    private IDomainRepository domainRepository;


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
}
