package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.Question;

import java.util.List;
import java.util.Set;

public interface IQuestionService extends ICrudService<Question> {
    List<Question> GetQuestions(int number, String difficulty);

    List<QuestionDto> getQuestionsByDomains(Set<Domain> domains);
}
