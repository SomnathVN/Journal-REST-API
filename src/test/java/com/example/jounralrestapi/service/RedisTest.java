package com.example.jounralrestapi.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void sendMailTest(){
       // redisTemplate.opsForValue().set("email","gmail@email.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a = 1;
        int b = 9;
    }
}
