package com.janson.thread.chapter28;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/9 11:59
 **/
public class ThreadLocalStatic {

    public static ExecutorService executorService = Executors.newFixedThreadPool(16);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
    static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                String date = new ThreadLocalStatic().date(finalI);
                if (concurrentHashMap.containsKey(date)) {
                    System.out.println("key :" + date);
                } else {
                    concurrentHashMap.put(date, "1");
                }
                System.out.println(date);
            });
        }
        executorService.shutdown();
    }


    static class ThreadSafeFormatter {
        public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return ThreadLocalStatic.dateFormat;
            }
        };
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }
}
