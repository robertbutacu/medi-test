package com.medi.test.meditest.controllers;

import com.medi.test.meditest.Transformers.UserTransformer;
import com.medi.test.meditest.dtos.UserDto;
import com.medi.test.meditest.entities.User;
import com.medi.test.meditest.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UsersController {
    @Autowired
    UserService service;

    @Autowired
    UserTransformer transformer;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Long> login(@RequestBody UserDto userDto){
        User user = service.getByUsername(userDto.getUsername());
        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else
            if(userDto.getPassword().equals(user.getPassword()))
                return new ResponseEntity<>(user.getId(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register (@RequestBody UserDto userDto){
        if(service.getByUsername(userDto.getUsername()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User user = transformer.toModel(userDto);
        service.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
