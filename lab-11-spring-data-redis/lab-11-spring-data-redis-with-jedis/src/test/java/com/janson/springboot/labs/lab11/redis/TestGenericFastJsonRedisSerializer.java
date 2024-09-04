package com.janson.springboot.labs.lab11.redis;

import com.janson.springboot.labs.lab11.redis.cacheobject.UserCacheObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: TestGenericJackson2JsonRedisSerializer
 *
 *  案例
 *
 *  {"@type":"com.janson.springboot.labs.lab11.redis.cacheobject.UserCacheObject","gender":3,"id":3,"name":"Janson3"}
 *
 * @Author: shanjian
 * @Date: 2024/3/13 17:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGenericFastJsonRedisSerializer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testStringSetKey() {
        stringRedisTemplate.opsForValue().set("janson3", "good");
    }


    @Test
    public void testStringSetKey02() {
        redisTemplate.opsForValue().set("janson3", "good1");
    }

    @Test
    public void testSetAdd() {
        stringRedisTemplate.opsForSet().add("jansonSet3", "shan", "jian");
    }

    @Test
    public void testStringSetKeyUserCache() {
        UserCacheObject object = new UserCacheObject().setId(3).setName("Janson3").setGender(3);
        String key = String.format("user:%d", object.getId());
        redisTemplate.opsForValue().set(key, object);
    }


    @Test
    public void testStringGetKeyUserCache() {
        String key = String.format("user:%d", 3);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

}
