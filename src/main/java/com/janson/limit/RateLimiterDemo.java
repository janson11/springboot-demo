package com.janson.limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Description: Guava 平滑突发限流Demo
 * @Author: shanjian
 * @Date: 2022/3/9 11:16 下午
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(100);
        // 1s放100个令牌到桶里。
        for (int i = 0; i < 120; i++) {
            double sleepingTime = rateLimiter.acquire(1);
            System.out.printf("get 1 tokens:%ss  i:%s %n", sleepingTime, i);
        }
    }
}
