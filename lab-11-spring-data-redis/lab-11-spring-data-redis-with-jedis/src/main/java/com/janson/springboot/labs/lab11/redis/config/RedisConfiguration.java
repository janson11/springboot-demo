package com.janson.springboot.labs.lab11.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.janson.springboot.labs.lab11.redis.listener.TestChannelMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/12 17:20
 */
@Configuration
public class RedisConfiguration {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置开启事务支持
        template.setEnableTransactionSupport(true);

        // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
        template.setConnectionFactory(factory);

        //使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());
        // 1、使用 JSON 序列化方式（库是 Jackson  GenericJackson2JsonRedisSerializer），序列化 VALUE 。
        // template.setValueSerializer(RedisSerializer.json());

        // 2、使用 Jackson2JsonRedisSerializer 序列化方式，序列化 VALUE [推荐用法]。
        //template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

        // 3、 使用 GenericFastJsonRedisSerializer 序列化方式，序列化 VALUE 。
        template.setValueSerializer(new GenericFastJsonRedisSerializer());

        return template;
    }


    @Bean //PUB/SUB 使用的Bean，需要打开
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory factory) {
        //创建 RedisMessageListenerContainer 对象
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
        container.setConnectionFactory(factory);

        //添加监听器
        container.addMessageListener(new TestChannelMessageListener(), new ChannelTopic("TEST"));

        return container;
    }

}
