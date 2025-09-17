package com.example.jounralrestapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMailTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendMailTest() {
        emailService.sendMail(
                "nalawadesomnath2003@gmail.com",
                "Kaise ho?",
                "Thik ho ya Nahi puchne ke liye mail kiya hai.");
    }
}
