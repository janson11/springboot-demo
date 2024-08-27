package com.janson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/26 19:19
 **/
//@Configuration
//public class AsyncConfig {
//
//    @Bean(name = "asyncTaskExecutor")
//    public Executor asyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(100);
//        return executor;
//    }
//}
