package com.medi.test.meditest.transformers;

import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.entities.Domain;

public class DomainTransformer {
    public static DomainDto toDto(Domain domain){
        return new DomainDto(domain.getName());
    }
}
