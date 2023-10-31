package com.janson.springboot.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/10/10 10:49 AM
 */
@EnableAsync
@Slf4j
public class DefaultAsyncExecutor extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        log.info("MyInitializing {}", this.getClass().getSimpleName());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(2000);
        executor.setThreadNamePrefix("defaultAsyncE-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        String uuid = UUID.randomUUID().toString();
        return (ex, method, params) -> {
            // 异步方法的参数太多了，会淹没异常栈
            log.error("异步方法报错{}方法名={}，\r\n参数={}", uuid, method, params);
            log.error("异步方法报错{}方法名={}", uuid, method, ex);
        };
    }
}