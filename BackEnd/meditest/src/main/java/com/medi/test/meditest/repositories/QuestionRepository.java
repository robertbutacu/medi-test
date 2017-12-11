package com.medi.test.meditest.repositories;

import com.medi.test.meditest.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDifficulty(String difficulty);
}