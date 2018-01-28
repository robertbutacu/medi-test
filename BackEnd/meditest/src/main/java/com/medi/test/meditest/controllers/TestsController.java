package com.medi.test.meditest.controllers;


import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.SolvedTestDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.services.implementation.TestScoringService;
import com.medi.test.meditest.services.implementation.test.implementation.TestService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/test")
public class TestsController {
    @Autowired
    private TestService testService;

    @Autowired
    private TestScoringService testScoringService;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity getTest(@RequestParam("domain") DomainDto domain,
                                  @RequestParam(value = "difficulty", required = false) Difficulty difficulty,
                                  @RequestParam(value = "numberOfQuestions", required = false) Integer numberOfQuestions,
                                  @RequestParam(value = "duration", required = false) Integer duration) {

        TestDto generatedTest = testService.generateTest(domain, difficulty, numberOfQuestions, duration);


        if (generatedTest == null){
            System.out.println("Test generation bad request.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            System.out.println("Returning back the generated test.");
            return new ResponseEntity<>(generatedTest, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public int getTestScore(@RequestBody com.medi.test.meditest.dtos.test.score.TestDto testDto,
                            @RequestParam("userId") long userId) {
        return testScoringService.getScore(userId, testDto);
    }
}
