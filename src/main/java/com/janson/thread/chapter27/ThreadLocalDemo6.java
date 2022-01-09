package com.janson.thread.chapter27;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 这样的结果是正常的，没有出现重复的时间。但是由于我们使用了 synchronized 关键字，就会陷入一种排队的状态，多个线程不能同时工作，这样一来，整体的效率就被大大降低了。有没有更好的解决方案呢？
 * <p>
 * 我们希望达到的效果是，既不浪费过多的内存，同时又想保证线程安全。经过思考得出，可以让每个线程都拥有一个自己的 simpleDateFormat 对象来达到这个目的，这样就能两全其美了。
 * @Author: Janson
 * @Date: 2022/1/9 11:59
 **/
public class ThreadLocalDemo6 {

    public static ExecutorService executorService = Executors.newFixedThreadPool(16);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                String date = new ThreadLocalDemo6().date(finalI);
                System.out.println(date);
            });
        }
        executorService.shutdown();
    }


    class ThreadSafeFormatter {
        public ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("mm:ss");
            }
        };

    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        ThreadSafeFormatter threadSafeFormatter = new ThreadSafeFormatter();
        SimpleDateFormat simpleDateFormat = threadSafeFormatter.dateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }
}
