package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.UserDto;
import com.medi.test.meditest.entities.User;
import com.medi.test.meditest.services.implementation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer implements ITransformer<User,UserDto>{

    @Autowired
    UserService service;

    @Override
    public User toModel(UserDto dto){
        User model = new User();
        model.setPassword(this.getEncryptedPassword(dto.getPassword()));
        model.setUsername(dto.getUsername());
        return model;
    }

    private String getEncryptedPassword(String password){
        String encryptedPassword = service.getEncryptedPassword(password);
        return password.equals(encryptedPassword) ? password : encryptedPassword;
    }


    @Override
    public UserDto toDto(User model){
        UserDto dto = new UserDto();
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        return dto;
    }
}
