package com.janson.thread.chapter10;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/28 2:00 下午
 */
@Slf4j
public class Task implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(12 * 1000);
        log.info("time:{} thread name:{}", LocalDateTime.now(), Thread.currentThread().getName());
    }
}
