package com.medi.test.meditest.controllers;

import com.medi.test.meditest.Transformers.QuestionTransformer;
import com.medi.test.meditest.dtos.QuestionDto;
import com.medi.test.meditest.entities.Answer;
import com.medi.test.meditest.entities.Domain;
import com.medi.test.meditest.entities.Question;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.entities.enums.QuestionType;
import com.medi.test.meditest.services.contracts.IDomainService;
import com.medi.test.meditest.services.contracts.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/v1/questions")
public class QuestionsController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IDomainService domainService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        initializeDomain();// - it's buggy
        List<Question> questions = questionService.getAll();
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question :
                questions) {
            questionDtos.add(QuestionTransformer.toDto(question));
        }
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseEntity getAllQuestionsByNumberAndDifficulty(
            @RequestParam(value="numberOfQuestions", required=false) int numberOfQestions,
            @RequestParam(value="difficulty", required=false) String difficulty) {
        initializeDomain();
        List<Question> questions = questionService.GetQuestions(numberOfQestions, difficulty);

        if(questions.size() != numberOfQestions) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question :
                questions) {
            questionDtos.add(QuestionTransformer.toDto(question));
        }
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    private void initializeDomain(){
        Question question = new Question();
        question.setBody("question");
        question.setDifficulty(Difficulty.Hard);
        question.setQuestionType(QuestionType.MultipleChoice);

        Answer ans = new Answer();
        ans.setBody("answer");
        ans.setIsCorrect(true);
        ans.setQuestion(question);

        Answer ans1 = new Answer();
        ans1.setBody("answer");
        ans1.setIsCorrect(true);
        ans1.setQuestion(question);

        Answer ans2 = new Answer();
        ans2.setBody("answer");
        ans2.setIsCorrect(false);
        ans2.setQuestion(question);


        List<Answer> answers = new ArrayList<>();
        answers.add(ans);
        answers.add(ans1);
        answers.add(ans2);
        question.setAnswers(answers);

        List<Question> questions = new ArrayList<>();
        questions.add(question);
        Domain domain = new Domain();
        domain.setName("domain");

        domain.setQuestions(questions);

        domainService.save(domain);
    }
}