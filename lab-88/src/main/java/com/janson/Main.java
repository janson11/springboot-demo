package com.janson;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://mp.weixin.qq.com/s/qtd4tfqg-AQdMXMUv31X1Q
 *
 * @Description: ${DESCRIPTION}
 * @Author: Janson
 * @Date: 2024/8/26 15:06
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            threadDontGcDemo();
            Thread.sleep(2000);
        }
    }

    private static void threadDontGcDemo() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        threadPoolExecutor.execute(() -> System.out.println("Hello world!" + LocalDateTime.now().toString()));
        threadPoolExecutor.shutdown();
    }
}