package com.janson.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/10/10 10:51 AM
 */
@Configuration
public class AsyncServiceExecutorConfig {

    /**
     * 核心异步线程池，用户工作台查询等核心操作
     * @return
     */
    @Bean(name = "uploadExecutor")
    public ThreadPoolTaskExecutor uploadExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(2000);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("uploadExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }

    @Bean
    public DefaultAsyncExecutor defaultAsyncExecutor() {
        return new DefaultAsyncExecutor();
    }

}
