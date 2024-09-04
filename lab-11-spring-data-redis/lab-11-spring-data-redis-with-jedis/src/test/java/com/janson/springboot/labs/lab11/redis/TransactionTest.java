package com.janson.springboot.labs.lab11.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/9/4 20:22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
//    @Transactional
    public void testTransaction() {
        // 这里是偷懒，没在 RedisConfiguration 配置类中，设置 stringRedisTemplate 开启事务
        stringRedisTemplate.setEnableTransactionSupport(true);

        //执行想要的操作
        stringRedisTemplate.opsForValue().set("sj:1", "shuai");
        stringRedisTemplate.opsForValue().set("sj:2", "shuai2");
    }


    @Test
    public void testTransaction02() {
        // 这里是偷懒，没在 RedisConfiguration 配置类中，设置 stringRedisTemplate 开启事务
        stringRedisTemplate.setEnableTransactionSupport(true);

        //执行想要的操作
        String s1 = stringRedisTemplate.opsForValue().get("sj:1");
        String s2 = stringRedisTemplate.opsForValue().get("sj:2");
        System.out.println(s1);
        System.out.println(s2);
    }

}
