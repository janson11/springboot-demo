package com.janson.springboot.labs.lab11.redis;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/9/9 15:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test01() throws IOException {
        // <1.1> 读取 /resources/lua/compareAndSet.lua 脚本 。注意，需要引入下 commons-io 依赖。
        String scriptContent = IOUtils.toString(getClass().getResourceAsStream("/lua/compareAndSet.lua"), "UTF-8");
        // <1.2> 创建RedisScript对象
        RedisScript<Long> script = new DefaultRedisScript<>(scriptContent, Long.class);
        // <1.3> 执行脚本
        Long result = stringRedisTemplate.execute(script, Collections.singletonList("janson02"), "shuai", "shuai88");
        System.out.println(result);
    }
}
