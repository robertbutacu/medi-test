package com.medi.test.meditest.controllers;


import com.medi.test.meditest.dtos.DomainDto;
import com.medi.test.meditest.dtos.test.TestDto;
import com.medi.test.meditest.entities.enums.Difficulty;
import com.medi.test.meditest.services.implementation.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/v1/test")
public class TestsController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity getTest(@RequestParam("domain") DomainDto domain,
                                  @RequestParam(value = "difficulty", required = false) Difficulty difficulty,
                                  @RequestParam(value = "numberOfQuestions", required = false) Integer numberOfQuestions,
                                  @RequestParam(value = "duration", required = false) Integer duration) {

        TestDto generatedTest = testService.generateTest(domain, difficulty, numberOfQuestions, duration);

        if (generatedTest == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(generatedTest, HttpStatus.OK);
    }
    
}
