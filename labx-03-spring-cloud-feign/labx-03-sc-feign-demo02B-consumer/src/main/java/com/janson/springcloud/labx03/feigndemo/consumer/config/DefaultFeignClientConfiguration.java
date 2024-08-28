package com.janson.springcloud.labx03.feigndemo.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Description: 全局 Feign Client 配置
 * @Author: Janson
 * @Date: 2024/8/28 17:25
 **/
public class DefaultFeignClientConfiguration {

    @Bean
    public Logger.Level defaultFeignClientLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
