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
 *  {"@class":"com.janson.springboot.labs.lab11.redis.cacheobject.UserCacheObject","id":1,"name":"Janson","gender":1}
 *
 * @Author: shanjian
 * @Date: 2024/3/13 17:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGenericJackson2JsonRedisSerializer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testStringSetKey() {
        stringRedisTemplate.opsForValue().set("janson", "good");
    }


    @Test
    public void testStringSetKey02() {
        redisTemplate.opsForValue().set("janson1", "good1");
    }

    @Test
    public void testSetAdd() {
        stringRedisTemplate.opsForSet().add("jansonSet", "shan", "jian");
    }

    @Test
    public void testStringSetKeyUserCache() {
        UserCacheObject object = new UserCacheObject().setId(1).setName("Janson").setGender(1);
        String key = String.format("user:%d", object.getId());
        redisTemplate.opsForValue().set(key, object);
    }


    @Test
    public void testStringGetKeyUserCache() {
        String key = String.format("user:%d", 1);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

}
