package com.example.jounralrestapi.service;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPooled;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisConnectionTest {

    @Test
    void testRedisConnection() {
        String host = "redis-16963.crce206.ap-south-1-1.ec2.redns.redis-cloud.com";
        int port = 16963; // TLS or non-TLS depending on dashboard
        String username = "default";
        String password = "AJcGgxofftrMqToDbaXeAo5WzYZTI8DW"; // raw password

        String uri = String.format("redis://%s:%s@%s:%d", username, password, host, port);

        try (JedisPooled jedis = new JedisPooled(uri)) {
            String response = jedis.ping();
            System.out.println("PING response: " + response);
            assertEquals("PONG", response);
        }
    }

}
