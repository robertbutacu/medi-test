package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.entities.Question;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.repositories.IQuestionRepository;
import com.medi.test.meditest.services.contracts.IQuestionService;
import com.medi.test.meditest.transformers.QuestionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private IQuestionRepository questionsRepository;

    @Autowired
    private QuestionTransformer questionTransformer;

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
        if (questionsRepository.getOne(id) == null) {
            throw new Exception("This question doesn't exists.");
        }
        questionsRepository.delete(id);
    }

    @Override
    public List<Question> GetQuestions(int number, String difficulty) {
        List<Question> selectedQuestions = new ArrayList<>();
        List<Question> foundQuestions = questionsRepository.findByDifficulty(Difficulty.Easy);
        for (int i = 0; i < number && !foundQuestions.isEmpty(); i++) {
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(foundQuestions.size());
            Question question = foundQuestions.get(randomInt);
            selectedQuestions.add(question);
            foundQuestions.remove(question);
        }
        return selectedQuestions;
    }

    @Override
    public boolean createQuestion(QuestionDto questionDto) {

//        switch (questionDto.getType()) {
//            case FillIn:
//                //only one answer is right
//                return questionsRepository.save(questionTransformer.toEntity(questionDto)) != null;
//            break;
//            case MultipleChoice:
//                //for one question there is only one answer is right
//                return questionsRepository.save(questionTransformer.toEntity(questionDto)) != null;
//            break;
//            case TrueOrFalse:
//                //only 2 answers of type true/false
//                return questionsRepository.save(questionTransformer.toEntity(questionDto)) != null;
//            break;
//            case SingleMatch:
//                //a question can have any number of answers but only one right
//                return questionsRepository.save(questionTransformer.toEntity(questionDto)) != null;
//            break;
//
//        }
        return questionsRepository.save(questionTransformer.toEntity(questionDto)) != null;
    }
}
