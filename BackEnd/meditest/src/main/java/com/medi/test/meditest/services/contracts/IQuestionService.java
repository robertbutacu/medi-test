package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.entities.Question;

import java.util.List;

public interface IQuestionService extends ICrudService<Question> {
    List<Question> GetQuestions(int number, String difficulty);
}
