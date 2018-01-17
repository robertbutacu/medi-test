package com.medi.test.meditest.services.implementation;

import com.medi.test.meditest.entities.User;
import com.medi.test.meditest.repositories.IUserRepository;
import com.medi.test.meditest.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


    @Override
    public void save(User entity) {
        this.repository.save(entity);
    }

    @Override
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @Override
    public User getById(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        this.repository.delete(id);
    }

    public User getByUsername(String username){
        return this.repository.findByUsername(username);
    }

    public String getEncryptedPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public Boolean passwordMatches(String password, String encodedPassword){
        return bCryptPasswordEncoder.matches(password,encodedPassword);
    }

}
