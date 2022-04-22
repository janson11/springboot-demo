package com.janson.thread.alibaba.lock.mylock;

import com.sun.org.apache.xpath.internal.operations.Gt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: 手写自定义的ReentrantLock
 * @Author: shanjian
 * @Date: 2022/4/20 4:53 下午
 */
public class MyReentrantLock1 implements Lock {
    /**
     * 锁的基本使用流程
     * 抢到锁，继续执行
     * 没有抢到锁，阻塞等待
     * 释放锁，唤醒一个等待的线程
     */


    // 方法2 使用字段更新原子类
    private static final AtomicIntegerFieldUpdater stateAtomicUpdater = AtomicIntegerFieldUpdater.newUpdater(MyReentrantLock1.class, "state");
    private static final AtomicReferenceFieldUpdater headAtomicUpdater = AtomicReferenceFieldUpdater.newUpdater(MyReentrantLock1.class, Node.class, "head");
    private static final AtomicReferenceFieldUpdater tailAtomicUpdater = AtomicReferenceFieldUpdater.newUpdater(MyReentrantLock1.class, Node.class, "tail");


    /**
     * 链表节点
     */

    private static class Node {
        Thread thread;
        volatile Node pre;
        volatile Node next;

        public Node(Thread waiter) {
            this.thread = waiter;
        }
    }

    /**
     * 链表头尾指针，组成一个链式队列
     */
    private volatile Node head, tail;


    private volatile int state;

    /**
     * 记录锁的持有线程
     */
    private volatile Thread owner;


    @Override
    public void lock() {
        if (!tryLock()) {
            // 加入排队对列
             Node node = addWaiter();

            for (; ; ) {
                Node pre = node.pre;
                if (pre ==head) {
                    if(tryLock()){
                        head.next = null;
                        head = node;
                        node.pre = null;
                        node.thread = null;
                        return;
                    }
                }

                // 抢不到，阻塞等待
                LockSupport.park(this);
                // 被唤醒后，清除中断状态（如有中断状态），否则循环再次进行将park不成功。
                Thread.interrupted();
                if (tryLock()) {
                    head.next = null;
                    head = node;
                    node.pre = null;
                    node.thread = null;
                    return;
                }
            }

        }


    }

    private Node addWaiter() {
        Node node = new Node(Thread.currentThread());
        // 加入到列尾

        for (; ; ) {
            Node t = tail;
            // 如果对列为空
            if (t == null) {
                // 初始化队列 cas 加入一个空的node作为head
                if (headAtomicUpdater.compareAndSet(this, null, new Node(null))) {
                    tail = head;
                    // 完成了队列的初始化，重复循环，将节点加到队尾
                }
            } else {
                node.pre = t;
                if (tailAtomicUpdater.compareAndSet(this, t, node)) {
                    t.next = node;
                    return node;
                }


            }

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        /**
         * 需要一个互斥量，用什么来充当互斥量
         * 可否通过一个int属性，CAS操作compareAndSwap(0,1)来充当互斥量，设置成功，抢到锁
         */

        boolean locked = stateAtomicUpdater.compareAndSet(this, 0, 1);
        if (locked) {
            owner = Thread.currentThread();
        }

        return locked;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {


        return false;
    }

    @Override
    public void unlock() {

        /**
         * 释放锁
         * 1、释放互斥量 1-》0
         * 2、唤醒一个阻塞等待线程来抢锁
         */

        if (Thread.currentThread() != owner) {
            return;
        }
        owner = null;
        state = 0;

        // 2 唤醒一个阻塞等待线程来抢锁
        // 需要能获取阻塞等待的线程，就需要子锁对象中持有哪些阻塞等待的线程
        // 怎么实现持有阻塞等待的线程？ 多个，数量不定，适合用链表，实现一个链表数据结构，记录排队等待的线程
        // 链表实现一个对联，
        Node h = head;
        if (h!=null) {
            Node n = h.next;
            if (n!=null) {
                LockSupport.unpark(n.thread);
                // 还需要做什么嘛？
                // head往后移动一个
//                head = n;
//                n.pre = null;
//                n.thread = null;
                // 唤醒的锁一定能抢到锁嘛
                // 被唤醒的线程的节点是不是一定是第一个节点
                // 由它在醒来后进行移动节点
            }
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
