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
import com.sun.java.browser.plugin2.DOM;
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
        Domain domain = new Domain();
        domain.setName("domain");

        generateFillInQuestions(domain);
        generateMultipleChoiceQuestions(domain);
        generateSingleMatchQuestions(domain);
        generateTrueOrFalseQuestions(domain);
    }

    private void generateSingleMatchQuestions(Domain domain){

    }

    private void generateMultipleChoiceQuestions(Domain domain){
        Question question1 = new Question();
        question1.setBody("What is Java");
        question1.setDifficulty(Difficulty.Hard);
        question1.setQuestionType(QuestionType.MultipleChoice);

        Answer ans = new Answer();
        ans.setBody("A Programming Language");
        ans.setIsCorrect(true);
        ans.setQuestion(question1);

        Answer ans1 = new Answer();
        ans1.setBody("An island");
        ans1.setIsCorrect(false);
        ans1.setQuestion(question1);

        Answer ans2 = new Answer();
        ans2.setBody("an animal");
        ans2.setIsCorrect(false);
        ans2.setQuestion(question1);

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(ans);
        answers1.add(ans1);
        answers1.add(ans2);
        question1.setAnswers(answers1);

        Question question2 = new Question();
        question2.setBody("What is Scala");
        question2.setDifficulty(Difficulty.Hard);
        question2.setQuestionType(QuestionType.MultipleChoice);

        Answer ans3 = new Answer();
        ans3.setBody("A Functional Programming Language");
        ans3.setIsCorrect(true);
        ans3.setQuestion(question2);

        Answer ans4 = new Answer();
        ans4.setBody("A tasty beverage");
        ans4.setIsCorrect(false);
        ans4.setQuestion(question2);

        Answer ans5 = new Answer();
        ans5.setBody("A framework");
        ans5.setIsCorrect(false);
        ans5.setQuestion(question2);

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(ans3);
        answers2.add(ans4);
        answers2.add(ans5);
        question2.setAnswers(answers2);


        Question question3 = new Question();
        question3.setBody("What is Cobol");
        question3.setDifficulty(Difficulty.Hard);
        question3.setQuestionType(QuestionType.MultipleChoice);

        Answer ans6 = new Answer();
        ans6.setBody("An ancient Programming Language");
        ans6.setIsCorrect(true);
        ans6.setQuestion(question3);

        Answer ans7 = new Answer();
        ans7.setBody("A rat");
        ans7.setIsCorrect(false);
        ans7.setQuestion(question3);

        Answer ans8 = new Answer();
        ans8.setBody("a mistake");
        ans8.setIsCorrect(false);
        ans8.setQuestion(question3);

        List<Answer> answers3 = new ArrayList<>();
        answers3.add(ans6);
        answers3.add(ans7);
        answers3.add(ans8);
        question3.setAnswers(answers3);


        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        domain.setQuestions(questions);
        questions.forEach(q -> q.setDomain(domain));

        domainService.save(domain);
    }

    private void generateFillInQuestions(Domain domain) {
        Question question1 = new Question();
        question1.setBody("Java is");
        question1.setDifficulty(Difficulty.Hard);
        question1.setQuestionType(QuestionType.FillIn);

        Answer ans = new Answer();
        ans.setBody("A Programming Language");
        ans.setIsCorrect(true);
        ans.setQuestion(question1);

        Answer ans1 = new Answer();
        ans1.setBody("An island");
        ans1.setIsCorrect(false);
        ans1.setQuestion(question1);

        Answer ans2 = new Answer();
        ans2.setBody("an animal");
        ans2.setIsCorrect(false);
        ans2.setQuestion(question1);

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(ans);
        answers1.add(ans1);
        answers1.add(ans2);
        question1.setAnswers(answers1);

        Question question2 = new Question();
        question2.setBody("Scala is...");
        question2.setDifficulty(Difficulty.Hard);
        question2.setQuestionType(QuestionType.FillIn);

        Answer ans3 = new Answer();
        ans3.setBody("A Functional Programming Language");
        ans3.setIsCorrect(true);
        ans3.setQuestion(question2);

        Answer ans4 = new Answer();
        ans4.setBody("A tasty beverage");
        ans4.setIsCorrect(false);
        ans4.setQuestion(question2);

        Answer ans5 = new Answer();
        ans5.setBody("A framework");
        ans5.setIsCorrect(false);
        ans5.setQuestion(question2);

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(ans3);
        answers2.add(ans4);
        answers2.add(ans5);
        question2.setAnswers(answers2);


        Question question3 = new Question();
        question3.setBody("Cobol is...");
        question3.setDifficulty(Difficulty.Hard);
        question3.setQuestionType(QuestionType.FillIn);

        Answer ans6 = new Answer();
        ans6.setBody("An ancient Programming Language");
        ans6.setIsCorrect(true);
        ans6.setQuestion(question3);

        Answer ans7 = new Answer();
        ans7.setBody("A rat");
        ans7.setIsCorrect(false);
        ans7.setQuestion(question3);

        Answer ans8 = new Answer();
        ans8.setBody("a mistake");
        ans8.setIsCorrect(false);
        ans8.setQuestion(question3);

        List<Answer> answers3 = new ArrayList<>();
        answers3.add(ans6);
        answers3.add(ans7);
        answers3.add(ans8);
        question3.setAnswers(answers3);


        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        domain.setQuestions(questions);
        questions.forEach(q -> q.setDomain(domain));

        domainService.save(domain);
    }

    private void generateTrueOrFalseQuestions(Domain domain){
        Question question1 = new Question();
        question1.setBody("Java 10 is out");
        question1.setDifficulty(Difficulty.Hard);
        question1.setQuestionType(QuestionType.TrueOrFalse);

        Answer ans = new Answer();
        ans.setBody("false");
        ans.setIsCorrect(true);
        ans.setQuestion(question1);

        Answer ans1 = new Answer();
        ans1.setBody("true");
        ans1.setIsCorrect(false);
        ans1.setQuestion(question1);

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(ans);
        answers1.add(ans1);
        question1.setAnswers(answers1);

        Question question2 = new Question();
        question2.setBody("Scala 2.13 is out");
        question2.setDifficulty(Difficulty.Hard);
        question2.setQuestionType(QuestionType.TrueOrFalse);

        Answer ans3 = new Answer();
        ans3.setBody("false");
        ans3.setIsCorrect(true);
        ans3.setQuestion(question2);

        Answer ans4 = new Answer();
        ans4.setBody("true");
        ans4.setIsCorrect(false);
        ans4.setQuestion(question2);

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(ans3);
        answers2.add(ans4);
        question2.setAnswers(answers2);


        Question question3 = new Question();
        question3.setBody("Cobol is a fresh language");
        question3.setDifficulty(Difficulty.Hard);
        question3.setQuestionType(QuestionType.TrueOrFalse);

        Answer ans6 = new Answer();
        ans6.setBody("false");
        ans6.setIsCorrect(true);
        ans6.setQuestion(question3);

        Answer ans7 = new Answer();
        ans7.setBody("true");
        ans7.setIsCorrect(false);
        ans7.setQuestion(question3);

        List<Answer> answers3 = new ArrayList<>();
        answers3.add(ans6);
        answers3.add(ans7);
        question3.setAnswers(answers3);


        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        domain.setQuestions(questions);
        questions.forEach(q -> q.setDomain(domain));

        domainService.save(domain);
    }
}