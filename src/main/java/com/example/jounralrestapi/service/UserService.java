package com.example.jounralrestapi.service;

import java.util.Arrays;
import java.util.List;

import com.example.jounralrestapi.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.jounralrestapi.entity.User;
import com.example.jounralrestapi.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;


    public User saveNewEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return user;
    }

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public List<?> getAllUser(){
        return userRepository.findAll();
    }

    public List<User> getUserForSA() {
        return userRepositoryImpl.getUserForSA();
    }
}
