package com.example.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Random;

@RestController
public class RedisController {


    private final RedisTemplate<String, String> redisTemplate;

    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/set")
    public String setRedisValue() {
        var customKey = new Random().nextInt(100);
        redisTemplate.opsForValue().set(String.valueOf(customKey), "value "+ customKey);
        return "Value set in Redis";
    }

    @GetMapping("/get/{key}")
    public String getRedisValue(@PathVariable String key) {
        return "Value from Redis: " + redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/get-all")
    public String findAll() {
        var value = new StringBuilder();
        Objects.requireNonNull(redisTemplate.keys("*")).forEach(key -> value.append(key).append(":")
                .append(redisTemplate.opsForValue().get(key)));
        return "all values: " + value;
    }
}
