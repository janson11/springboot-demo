package com.janson.springcloud.labx03.feigndemo.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @Description: 服务 demo-provider的FeignClient 配置类
 * @Author: Janson
 * @Date: 2024/8/28 17:27
 **/
public class DemoProviderFeignClientConfiguration {

    @Bean
    @Primary // 主Bean
    public Logger.Level feignClientLoggerLevel() {
        return Logger.Level.FULL;
    }
}
