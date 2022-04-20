package com.janson.thread.alibaba.distribute.lock.zk;

import com.janson.thread.alibaba.distribute.lock.sample.MyZkSerializer;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 分布式锁  临时顺序节点
 * @Author: shanjian
 * @Date: 2022/4/20 9:51 上午
 */
public class ZkDistributeImproveLock implements Lock {

    /**
     * 利用临时顺序节点来实现分布式锁
     * 获取锁：取排队号（创建自己的临时顺序节点，然后判断自己是否是最小号，如果是，则获取锁。不是，则注册前一节点的watcher，阻塞等待
     * 释放锁：删除自己的创建的临时顺序节点
     */

    private String lockPath;

    private ZkClient zkClient;

    private String currentPath;

    private String beforePath;

    private Thread owner;


    public ZkDistributeImproveLock(String lockPath) {
        this.lockPath = lockPath;
        zkClient = new ZkClient("localhost:2181");
        zkClient.setZkSerializer(new MyZkSerializer());
        if (!zkClient.exists(lockPath)) {
            try {
                zkClient.createPersistent(lockPath);
            } catch (ZkNodeExistsException e) {

            }
        }

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

        zkClient.subscribeDataChanges(this.beforePath, listener);


        if (this.zkClient.exists(this.beforePath)) {
            // 阻塞自己
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //取消注册watcher
        zkClient.unsubscribeDataChanges(this.beforePath, listener);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        // 抢锁，创建临时顺序节点
        if (this.currentPath == null) {
            currentPath = this.zkClient.createEphemeralSequential(lockPath + "/", "aaa");
        }

        // 获取所有的子顺序节点
        List<String> children = this.zkClient.getChildren(lockPath);
        // 排序list
        Collections.sort(children);

        //判断当前节点是否是最小的
        if (currentPath.equals(lockPath + "/" + children.get(0))) {
            return true;
        } else {
            //收到前一个
            // 得到子节的索引号
            int curIndex = children.indexOf(currentPath.substring(lockPath.length() + 1));
            beforePath = lockPath + "/" + children.get(curIndex - 1);
        }
//        owner = Thread.currentThread();
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
//        if (Thread.currentThread() != owner) {
//            return;
//        }
//
//        owner = null;
        // 必须是持有锁的线程才可以执行unlock逻辑
        zkClient.delete(this.currentPath);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
