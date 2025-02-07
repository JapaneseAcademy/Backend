package com.academy.backend.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedisConnection() {
        try {
            redisTemplate.opsForValue().set("testKey", "Hello, Redis!");
            String value = redisTemplate.opsForValue().get("testKey");
            System.out.println("✅ Redis 연결 성공! 값: " + value);
        } catch (Exception e) {
            System.err.println("❌ Redis 연결 실패: " + e.getMessage());
        }
    }
}
