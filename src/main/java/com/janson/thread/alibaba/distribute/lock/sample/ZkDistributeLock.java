package com.janson.thread.alibaba.distribute.lock.sample;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 分布式锁
 * @Author: shanjian
 * @Date: 2022/4/20 9:51 上午
 */
public class ZkDistributeLock implements Lock {

    private String lockPath;

    private ZkClient zkClient;

    private Thread owner;

    public ZkDistributeLock(String lockPath) {
        this.lockPath = lockPath;
        zkClient = new ZkClient("localhost:2181");
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    @Override
    public void lock() {
        while (!tryLock()) {
            // 阻塞等
            waitForLock();
        }

    }

    private void waitForLock() {

        CountDownLatch cdl = new CountDownLatch(1);
        // 注册watch
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("-------收到节点数据的变化：" + dataPath + "----------");
                // 唤醒阻塞的线程
                cdl.countDown();
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("--------收到节点被删除了-------------");

            }
        };

        zkClient.subscribeDataChanges(lockPath, listener);


        if (this.zkClient.exists(lockPath)) {
            // 阻塞自己
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //取消注册
        zkClient.unsubscribeDataChanges(lockPath, listener);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        // 抢锁，创建临时节点
        try {
            zkClient.createEphemeral(lockPath);
        } catch (ZkNodeExistsException e) {
            return false;
        }
        owner = Thread.currentThread();
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 必须是持有锁的线程才可以执行unlock逻辑

        if (Thread.currentThread() != owner) {
            return;
        }

        owner = null;
        zkClient.delete(lockPath);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
