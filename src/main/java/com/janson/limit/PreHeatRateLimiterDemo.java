package com.janson.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Guava 平滑预热限流Demo
 * @Author: shanjian
 * @Date: 2022/3/9 11:39 下午
 */
public class PreHeatRateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);

        for (int i = 0; i < 20; i++) {
            double acquire = rateLimiter.acquire(1);
            System.out.printf("get 1 tokens :%sds%n", acquire);

        }
    }
}
