package com.janson.thread.alibaba.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/18 9:27 上午
 */
public class AtomicFieldUpdaterDemo {

    public static void main(String[] args) {
        int threads = 20;
        CountDownLatch cdl = new CountDownLatch(threads);
        DemoBean obj = new DemoBean();
        AtomicIntegerFieldUpdater<DemoBean> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(DemoBean.class, "inte");
        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicIntegerFieldUpdater.incrementAndGet(obj);
                }
                cdl.countDown();
            }).start();
        }


        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(obj.getInte());

    }


}

class ParentBean {
    volatile int inte;
}

class DemoBean extends ParentBean {
    /**
     * [1] 必须是volatile 修饰的
     * 【2】 不能是static的
     * 【3】在执行更新的代码中一定要能直接访问到改属性，加private下面的代码访问不到就不可以了
     * 【4】不能是父类的字段
     */
    volatile int inte;

    public DemoBean() {
        this.inte = 0;
    }

    public int getInte() {
        return inte;
    }

}
