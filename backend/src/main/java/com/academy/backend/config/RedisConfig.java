package com.academy.backend.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class RedisConfig {

    private Process redisProcess;

    @PostConstruct
    public void startRedis() throws IOException {
        log.info("Starting embedded Redis server...");
        redisProcess = Runtime.getRuntime().exec("redis-server");
    }

    @PreDestroy
    public void stopRedis() {
        if (redisProcess != null) {
            redisProcess.destroy();
            log.info("Redis server stopped.");
        }
    }
}

