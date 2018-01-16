package com.medi.test.meditest.Transformers;

import com.medi.test.meditest.dtos.UserDto;
import com.medi.test.meditest.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer implements ITransformer<User,UserDto>{

    @Override
    public User toModel(UserDto dto){
        User model = new User();
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());
        return model;
    }

    @Override
    public UserDto toDto(User model){
        UserDto dto = new UserDto();
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        return dto;
    }
}
