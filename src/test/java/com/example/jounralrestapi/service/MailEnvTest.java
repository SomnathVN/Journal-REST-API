package com.example.jounralrestapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailEnvTest {

    @Value("${MAIL_USERNAME}")
    private String username;

    @Value("${MAIL_PASSWORD}")
    private String password;

    @Test
    void checkEnvVariables() {
        System.out.println("✅ Resolved MAIL_USERNAME = " + username);
        System.out.println("✅ Resolved MAIL_PASSWORD = " + password);
    }
}

