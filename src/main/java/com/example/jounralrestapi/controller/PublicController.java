package com.example.jounralrestapi.controller;

import com.example.jounralrestapi.entity.User;
import com.example.jounralrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String HealthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public void addUser(@RequestBody User user) {
        userService.saveNewEntry(user);
    }
}
