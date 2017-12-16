package com.medi.test.meditest.repositories;

import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.Question;
import com.medi.test.meditest.entities.enums.QuestionDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IQuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDifficulty(QuestionDifficulty difficulty);
}