package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.Domain;

import java.util.List;

public interface IDomainService extends ICrudService<Domain> {
    List<DomainDto> getAllDomains();
}
