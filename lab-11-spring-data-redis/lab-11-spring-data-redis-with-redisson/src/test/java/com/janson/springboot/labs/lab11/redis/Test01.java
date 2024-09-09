package com.janson.springboot.labs.lab11.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/9/9 16:18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testStringSetKey() {
        stringRedisTemplate.opsForValue().set("janson001", "shuai001");
    }

    @Test
    public void testStringSetKey02() {
        redisTemplate.opsForValue().set("janson002", "shuai002");
    }


    @Test
    public void testSetAdd() {
        stringRedisTemplate.opsForSet().add("janson_descriptions","shuai","janson");
    }
}
