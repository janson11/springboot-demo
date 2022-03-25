package com.janson.pdai.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/25 5:26 下午
 */
public class StackClosedExample {

    public void add100(){
        int cnt=0;
        for (int i = 0; i < 100; i++) {
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        StackClosedExample stackClosedExample = new StackClosedExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() ->stackClosedExample.add100());
        service.execute(() ->stackClosedExample.add100());
        service.shutdown();
    }
}
