package com.janson.springboot.lab26.distributedsession.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.AbstractMongoSessionConverter;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * @Description: config
 * @Author: shanjian
 * @Date: 2023/12/19 10:40 AM
 */
@Configuration
@EnableMongoHttpSession // 自动化配置 Spring Session 使用 MongoDB 作为数据源
public class SessionConfiguration {
    @Bean
    public AbstractMongoSessionConverter mongoSessionConverter() {
        return new JacksonMongoSessionConverter();
    }

}
