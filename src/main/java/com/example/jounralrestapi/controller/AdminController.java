package com.example.jounralrestapi.controller;

import com.example.jounralrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public List<?> allUsers(){
        return userService.getAllUser();
    }
}
