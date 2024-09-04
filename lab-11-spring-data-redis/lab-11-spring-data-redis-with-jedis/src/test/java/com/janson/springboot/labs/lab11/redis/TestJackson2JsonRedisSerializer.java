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
 * @Description:  TestJackson2JsonRedisSerializer
 *
 * 案例
 * {"id":2,"name":"Janson02","gender":2}
 *
 * @Author: shanjian
 * @Date: 2024/3/13 17:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJackson2JsonRedisSerializer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testStringSetKey() {
        stringRedisTemplate.opsForValue().set("janson02", "good");
    }


    @Test
    public void testStringSetKey02() {
        redisTemplate.opsForValue().set("janson02", "good1");
    }

    @Test
    public void testSetAdd() {
        stringRedisTemplate.opsForSet().add("jansonSet02", "shan", "jian");
    }

    @Test
    public void testStringSetKeyUserCache() {
        UserCacheObject object = new UserCacheObject().setId(2).setName("Janson02").setGender(2);
        String key = String.format("user:%d", object.getId());
        redisTemplate.opsForValue().set(key, object);
    }



    @Test
    public void testStringGetKeyUserCache() {
        String key = String.format("user:%d", 2);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

}
