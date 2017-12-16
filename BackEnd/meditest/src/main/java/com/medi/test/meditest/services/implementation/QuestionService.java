package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.entities.Question;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private IQuestionRepository questionsRepository;

    @Override
    public void save(Question entity) {
        questionsRepository.save(entity);
    }

    @Override
    public List<Question> getAll() {
        return questionsRepository.findAll();
    }

    @Override
    public Question getById(Long id) {
        return questionsRepository.findOne(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(questionsRepository.getOne(id) == null) {
            throw new Exception("This question doesn't exists.");
        }
        questionsRepository.delete(id);
    }

    @Override
    public List<Question> GetQuestions(int number, String difficulty) {
        List<Question> selectedQuestions = new ArrayList<>();
        List<Question> foundQuestions = questionsRepository.findByDifficulty(difficulty);
        for(int i = 0; i < number && !foundQuestions.isEmpty(); i++){
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(foundQuestions.size());
            Question question = foundQuestions.get(randomInt);
            selectedQuestions.add(question);
            foundQuestions.remove(question);
        }
        return selectedQuestions;
    }
}
