package com.medi.test.meditest.repositories;

import com.medi.test.meditest.entities.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DomainRepository extends JpaRepository<Domain, Long> {
}
