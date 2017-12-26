package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.enums.Difficulty;

import java.util.List;
import java.util.Set;

public interface IDomainService extends ICrudService<Domain> {
    List<DomainDto> getAllDomains();
    Set<DomainDto> getDomainsByDifficulty(DomainDto domain, Difficulty difficulty);
}
