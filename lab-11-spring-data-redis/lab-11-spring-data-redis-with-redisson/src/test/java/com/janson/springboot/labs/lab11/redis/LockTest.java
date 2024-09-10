package com.janson.springboot.labs.lab11.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8
 * @Author: Janson
 * @Date: 2024/9/9 19:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {

    private static final String LOCK_KEY = "anylock";

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 8.1. 可重入锁（Reentrant Lock）
     * 基于Redis的Redisson分布式可重入锁RLock Java对象实现了java.util.concurrent.locks.Lock接口。同时还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
     *
     * @throws InterruptedException
     */
    @Test
    public void test01() throws InterruptedException {
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                RLock lock = redissonClient.getLock(LOCK_KEY);
                lock.lock(10, TimeUnit.SECONDS);
            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(String.format("准备开始获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        RLock lock = redissonClient.getLock(LOCK_KEY);
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            System.out.println(String.format("实际获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        } else {
            System.out.println("获取锁失败");
        }
    }


    /**
     * 8.2. 公平锁（Fair Lock
     * 基于Redis的Redisson分布式可重入公平锁也是实现了java.util.concurrent.locks.Lock接口的一种RLock对象。
     * 同时还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。它保证了当多个Redisson客户端线程同时请求加锁时，
     * 优先分配给先发出请求的线程。所有请求线程会在一个队列中排队，当某个线程出现宕机时，Redisson会等待5秒后继续下一个线程，
     * 也就是说如果前面有5个线程都处于等待状态，那么后面的线程会等待至少25秒。
     * <p>
     * 大家都知道，如果负责储存这个分布式锁的Redis节点宕机以后，而且这个锁正好处于锁住的状态时，这个锁会出现锁死的状态。
     * 为了避免这种情况的发生，Redisson内部提供了一个监控锁的看门狗，它的作用是在Redisson实例被关闭前，不断的延长锁的有效期。
     * 默认情况下，看门狗的检查锁的超时时间是30秒钟，也可以通过修改Config.lockWatchdogTimeout来另行指定。
     * <p>
     * 另外Redisson还通过加锁的方法提供了leaseTime的参数来指定加锁的时间。超过这个时间后锁便自动解开了。
     *
     * @throws InterruptedException
     */
    @Test
    public void test02() throws InterruptedException, ExecutionException {
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                RLock lock = redissonClient.getFairLock(LOCK_KEY);
                lock.lock(10, TimeUnit.SECONDS);
            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(String.format("准备开始获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        RLock lock = redissonClient.getFairLock(LOCK_KEY);
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            System.out.println(String.format("实际获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        } else {
            System.out.println("获取锁失败");
        }
    }


    /**
     * 公平锁（Fair Lock)异步加锁
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test02Async() throws InterruptedException, ExecutionException {
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                RLock lock = redissonClient.getFairLock(LOCK_KEY);
                // 异步加锁
                lock.lockAsync(10, TimeUnit.SECONDS);

            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(String.format("准备开始获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        RLock lock = redissonClient.getFairLock(LOCK_KEY);
        RFuture<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
        if (res.get()) {
            System.out.println(String.format("实际获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        } else {
            System.out.println("获取锁失败");
        }
    }

    /**
     * 联锁（MultiLock）
     * 基于Redis的Redisson分布式联锁RedissonMultiLock对象可以将多个RLock对象关联为一个联锁，每个RLock对象实例可以来自于不同的Redisson实例。
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test03MultiLock() throws InterruptedException, ExecutionException {
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient.getLock("lock2");
        RLock lock3 = redissonClient.getLock("lock3");
        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                // 异步加锁
                lock.lock(10, TimeUnit.SECONDS);

            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(String.format("准备开始获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            System.out.println(String.format("实际获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        } else {
            System.out.println("获取锁失败");
        }
    }

    /**
     * 红锁（RedLock）
     * 基于Redis的Redisson分布式红锁RedissonRedLock对象可以将多个RLock对象关联为一个红锁，每个RLock对象实例可以来自于不同的Redisson实例。
     * 红锁是一种特殊的联锁，它保证了锁的可用性，即使其中一个Redisson实例宕机，其他实例依然可以获得锁。
     * 基于Redis的Redisson红锁RedissonRedLock对象实现了Redlock介绍的加锁算法。该对象也可以用来将多个RLock对象关联为一个红锁，每个RLock对象实例可以来自于不同的Redisson实例。
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test04RedLock() throws InterruptedException, ExecutionException {
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient.getLock("lock2");
        RLock lock3 = redissonClient.getLock("lock3");
        RedissonMultiLock lock = new RedissonRedLock(lock1, lock2, lock3);
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                // 异步加锁
                lock.lock(10, TimeUnit.SECONDS);

            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(String.format("准备开始获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            System.out.println(String.format("实际获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        } else {
            System.out.println("获取锁失败");
        }
    }

    private Long count = 0L;

    /**
     * 读写锁（ReadWriteLock）
     * 读写锁是一种用于控制对共享资源的访问的锁。它允许多个线程同时对共享资源进行读操作，但只允许一个线程对共享资源进行写操作。
     * 读写锁通过分离读锁和写锁来实现。读锁可以由多个线程同时持有，而写锁则是独占的。
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test05ReadWriteLock() throws InterruptedException, ExecutionException {

        RReadWriteLock rwLock = redissonClient.getReadWriteLock("anyRWLock");
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                rwLock.writeLock().lock(10, TimeUnit.SECONDS);
                count++;
                System.out.println("count设置成功时间:" + new Date());
            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 主线程读锁
        rwLock.readLock().lock(10, TimeUnit.SECONDS);
        System.out.println("主线程:" + Thread.currentThread().getName() + " 获取读锁成功,count=" + count + "当前时间:" + new Date());

        new Thread(new Runnable() {
            @Override
            public void run() {
                rwLock.readLock().lock(10, TimeUnit.SECONDS);
                System.out.println("子线程:" + Thread.currentThread().getName() + " 获取读锁成功,count=" + count + "当前时间:" + new Date());
            }
        }).start();
    }


    /**
     * 信号量（Semaphore）
     * 信号量是一种用于控制对共享资源的访问数量的锁。它允许多个线程同时对共享资源进行访问，但同时只允许固定数量的线程对共享资源进行访问。
     * 信号量通过计数器来实现。计数器表示当前可用的共享资源数，每当一个线程需要访问共享资源时，它必须先获取信号量，然后才能访问。
     * 当计数器为零时，线程将被阻塞，直到其他线程释放了信号量。
     * 基于Redis的Redisson的分布式信号量（Semaphore）Java对象RSemaphore采用了与java.util.concurrent.Semaphore相似的接口和用法。
     * 同时还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test06Semaphore() throws InterruptedException {
        RSemaphore semaphore = redissonClient.getSemaphore("semaphore");
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                // 异步加锁
                try {
//                    semaphore.tryAcquire(2, 10, TimeUnit.SECONDS);
//                    semaphore.acquire();
                    boolean b = semaphore.tryAcquire(10, TimeUnit.SECONDS);
                    if (b) {
                        System.out.println(Thread.currentThread().getName() + String.format("线程信号量获取成功时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                    } else {
                        System.out.println(Thread.currentThread().getName() + String.format("线程信号量获取失败时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(1000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(Thread.currentThread().getName() + String.format("线程准备开始获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        boolean res = semaphore.tryAcquire(10, TimeUnit.SECONDS);
        if (res) {
            System.out.println(Thread.currentThread().getName() + String.format("线程实际获得锁时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        } else {
            System.out.println("获取锁失败");
            semaphore.release();
        }
    }


    /**
     * 可过期性信号量（PermitExpirableSemaphore）
     * 基于Redis的Redisson可过期性信号量（PermitExpirableSemaphore）是在RSemaphore对象的基础上，为每个信号增加了一个过期时间。每个信号可以通过独立的ID来辨识，释放时只能通过提交这个ID才能释放。它提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
     * <p>
     * RPermitExpirableSemaphore semaphore = redisson.getPermitExpirableSemaphore("mySemaphore");
     * String permitId = semaphore.acquire();
     * // 获取一个信号，有效期只有2秒钟。
     * String permitId = semaphore.acquire(2, TimeUnit.SECONDS);
     * // ...
     * semaphore.release(permitId);
     *
     * @throws InterruptedException
     */
    @Test
    public void test07PermitExpirableSemaphore() throws InterruptedException {
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore("mysemaphore");
        semaphore.addPermits(1);
        // 启动一个线程A,去占用锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 加锁以后,10秒后自动释放锁
                // 无需调用unlock方法,锁会自动释放
                // 异步加锁
                //                    semaphore.tryAcquire(2, 10, TimeUnit.SECONDS);
//                    semaphore.acquire();
                String permitId = null;
                try {
                    Thread.sleep(1000L);
                    permitId = semaphore.tryAcquire(20, -1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " permitId:" + permitId + String.format("线程信号量开始获取时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                if (permitId != null) {
                    System.out.println(Thread.currentThread().getName() + String.format("线程信号量获取成功时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
//                    semaphore.release(permitId);
                } else {
                    System.out.println(Thread.currentThread().getName() + String.format("线程信号量获取失败时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                }

            }
        }).start();
        // 简单sleep 1 秒,确保线程A成功持有锁
        Thread.sleep(10000L);

        // 尝试加锁, 最多等待100秒,上锁以后10秒自动解锁
        System.out.println(Thread.currentThread().getName() + String.format("线程信号量开始获取时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        String permitId = semaphore.tryAcquire(20, 10, TimeUnit.SECONDS);
        if (permitId != null) {
            System.out.println(Thread.currentThread().getName() + " permitId:" + permitId + String.format("线程信号量获取成功时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
//            semaphore.release(permitId);
        } else {
            System.out.println("获取锁失败");
        }
    }


    /**
     * 闭锁（CountDownLatch）
     * 闭锁（CountDownLatch）是一种同步工具，它允许一个或多个线程等待，直到其他线程都完成某项工作。
     * 闭锁的作用是让一组线程等待直到最后一个线程完成某件事情。
     * 闭锁的计数器可以被重置，所以它可以被用来实现一组线程的同步。
     * 基于Redis的Redisson的分布式闭锁（CountDownLatch）Java对象RCountDownLatch采用了与java.util.concurrent.CountDownLatch相似的接口和用法。
     * 同时还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
     */
    @Test
    public void test08CountDownLatch() throws InterruptedException {
        RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
        latch.trySetCount(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown();
                System.out.println("线程" + Thread.currentThread().getName() + String.format("完成任务时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown();
                System.out.println("线程" + Thread.currentThread().getName() + String.format("完成任务时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown();
                System.out.println("线程" + Thread.currentThread().getName() + String.format("完成任务时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            }
        }).start();

        latch.await();
        System.out.println("线程" + Thread.currentThread().getName() + String.format("完成任务时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        System.out.println("所有线程都完成了任务" + String.format("时间: %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    }


}
