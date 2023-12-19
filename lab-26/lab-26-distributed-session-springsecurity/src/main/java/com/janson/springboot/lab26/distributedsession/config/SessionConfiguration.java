package com.janson.springboot.lab26.distributedsession.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description: session config
 * @Author: shanjian
 * @Date: 2023/12/19 2:06 PM
 */
@Configuration
@EnableRedisHttpSession // 自动化配置 Spring Session 使用 Redis 作为数据源
public class SessionConfiguration {
}
