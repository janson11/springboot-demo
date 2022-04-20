package com.janson.thread.alibaba.distribute.lock.sample;

import com.janson.thread.alibaba.distribute.lock.zk.ZkDistributeImproveLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/18 11:01 上午
 */
public class OrderServiceImplWithLock implements OrderService {

    private static OrderCodeGenerator ocg = new OrderCodeGenerator();

//    private static Lock lock = new ReentrantLock();

    @Override
    public void createOrder() {
        // 获取订单号
        String orderCode = null;
        // 分布式锁
//        Lock lock = new ZkDistributeLock("/dh666888");
        Lock lock = new ZkDistributeImproveLock("/dh66688877700");

        lock.lock();
        try {
            orderCode = ocg.getOrderCode();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "===============>" + orderCode);
    }
}
