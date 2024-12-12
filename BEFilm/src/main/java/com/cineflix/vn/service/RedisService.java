package com.cineflix.vn.service;

import com.cineflix.vn.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    @Lazy
    JWTUtil jwtUtil;
    @Async
    public void saveToken(String username, String token) {
        long expirationTime = jwtUtil.extractExpiration(token).getTime() - System.currentTimeMillis();
        redisTemplate.opsForValue().set(username, token, expirationTime, TimeUnit.MILLISECONDS);
    }
    @Async("taskExecutor")
    public CompletableFuture<Void> saveTokenAsync(String username, String token) {
        return CompletableFuture.runAsync(() -> saveToken(username, token));
    }
    public String getToken(String tokenKey) {
        return redisTemplate.opsForValue().get(tokenKey);
    }

    public void deleteToken(String tokenKey) {
        redisTemplate.delete(tokenKey);
    }
}
