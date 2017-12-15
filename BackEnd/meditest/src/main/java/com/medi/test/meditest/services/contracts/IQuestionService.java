package com.medi.test.meditest.services.contracts;

import com.medi.test.meditest.entities.Question;

import java.util.List;

public interface IQuestionService extends ICrudService<Question> {
    public List<Question> GetQuestions(int number, String difficulty);
}
