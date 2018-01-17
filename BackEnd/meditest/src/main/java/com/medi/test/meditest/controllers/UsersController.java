package com.medi.test.meditest.controllers;

import com.medi.test.meditest.Transformers.UserTransformer;
import com.medi.test.meditest.dtos.UserDto;
import com.medi.test.meditest.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UsersController {
    @Autowired
    UserService service;

    @Autowired
    UserTransformer transformer;

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody UserDto userDto){
        return service.checkCredentials(userDto, service.getByUsername(userDto.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody UserDto userDto){
        return service.registerUser(userDto);
    }



}
