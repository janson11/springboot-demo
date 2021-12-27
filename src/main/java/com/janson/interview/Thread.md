多线程训练营
关于线程同步的面试题，凡是从时间角度或者是优先级角度考虑解题思路的，基本全不对！凡是从join sleep考虑的，99.99%的不对. 线程优雅的结束，一般不用interrupt stop resume
最近新题，来自京东
给定文件，每行一词，统计词频（思考题）

基数统计？concurrent map (词 count) 大数据里面的word - count

多线程

Show Me The Difference (From Alibaba）
package com.mashibing.juc.c_35_QuestionsOfAlibaba;

public class ShowMeTheDifference {}

//4.指出以下两段程序的差别，并分析

final class Accumulator {
    private double result = 0.0D;
    public void addAll( double[] values) {
        for(double value : values) {
            result += value;
        }
    }
}

final class Accumulator2 {
    private double result = 0.0D;
    public void addAll( double[] values) {
        double sum = 0.0D;
        for(double value : values) {
            sum += value;
        }
        result += sum;
    }
}
答案：
第二种写法比第一种写法出现不一致性的概率要小，因为我们在方法完成之前，读不到中间状态的脏数据

尽量少暴露线程计算过程的中间状态

能用范围小的变量，不用范围大的变量

哲学家就餐问题
image.png

模拟哲学家问题 OOA - OOD - DDD
class : 哲学家 class : 筷子
筷子：编号
哲学家：左手的筷子 右手的筷子 编号
package com.mashibing.juc.c_33_TheDinningPhilosophersProblem;


import com.mashibing.util.SleepHelper;

public class T01_DeadLock {
    public static void main(String[] args) {
        ChopStick cs0 = new ChopStick();
        ChopStick cs1 = new ChopStick();
        ChopStick cs2 = new ChopStick();
        ChopStick cs3 = new ChopStick();
        ChopStick cs4 = new ChopStick();

        Philosohper p0 = new Philosohper("p0", 0, cs0, cs1);
        Philosohper p1 = new Philosohper("p1", 1, cs1, cs2);
        Philosohper p2 = new Philosohper("p2", 2, cs2, cs3);
        Philosohper p3 = new Philosohper("p3", 3, cs3, cs4);
        Philosohper p4 = new Philosohper("p4", 4, cs4, cs0);

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();

    }

    public static class Philosohper extends Thread {

        private ChopStick left, right;
        private int index;

        public Philosohper(String name, int index, ChopStick left, ChopStick right) {
            this.setName(name);
            this.index = index;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            synchronized (left) {
                SleepHelper.sleepSeconds(1 + index);
                synchronized (right) {
                    SleepHelper.sleepSeconds(1);
                    System.out.println(index + " 号 哲学家已经吃完");
                }
            }

        }

    }
}
死锁一般具有2把以上的锁，在锁定一把的时候等待另外一把锁

哲学家就餐问题解决方案：

两把锁合并一把锁（5把， 5把锁合成一把锁，筷子集合，锁定整个对象）
混进一个左撇子
效率更高的写法，奇数 偶数分开，混进一半的左撇子
public void run() {
            //SleepHelper.sleepSeconds(new Random().nextInt(5));
            if (index == 0) { //左撇子算法 也可以index % 2 == 0

                synchronized (left) {
                    SleepHelper.sleepSeconds(1);
                    synchronized (right) {
                        SleepHelper.sleepSeconds(1);
                        System.out.println(index + " 吃完了！");
                    }
                }
            } else {
                synchronized (right) {
                    SleepHelper.sleepSeconds(1);
                    synchronized (left) {
                        SleepHelper.sleepSeconds(1);
                        System.out.println(index + " 吃完了！");
                    }
                }
            }
        }
交替输出问题
image.png

LockSupport
t1 = new Thread(() -> {

            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2); //叫醒T2
                LockSupport.park(); //T1阻塞 当前线程阻塞
            }

        }, "t1");

        t2 = new Thread(() -> {

            for (char c : aC) {
                LockSupport.park(); //t2挂起
                System.out.print(c);
                LockSupport.unpark(t1); //叫醒t1
            }

        }, "t2");

        t1.start();
        t2.start();
Sync wait notify
new Thread(() -> {

            synchronized (o) {
                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait(); //让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify(); //必须，否则无法停止程序
            }

        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }
        }, "t2").start();
Lock ReentrantLock await signal
Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {


                for (char c : aI) {
                    System.out.print(c);
                    condition.signal();  //notify()
                    condition.await(); // wait()
                }

                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            lock.lock(); //synchronized
            try {


                for (char c : aC) {
                    System.out.print(c);
                    condition.signal(); //o.notify
                    condition.await(); //o.wait
                }

                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();
生产者消费者问题 ReentantLock Condition
class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获取锁
                lock.lock();
                try {
                    while (count >= maxNum) {
                        producerCondition.await();
                        System.out.println("生产能力达到上限，进入等待状态");
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                    //唤醒消费者
                    consumerCondition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //获取锁
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count <= 0) {
                        consumerCondition.await();
                    }

                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                    //唤醒生产者
                    producerCondition.signalAll();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
异步回调回滚问题（分布式事务）
大任务分解成小任务，出错回滚问题（分布式事务）

package com.mashibing.juc.c_32_AliQuestions;

import com.mashibing.util.SleepHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 最原始的方法，Thread run()重写
 */

public class T00_F7 {

    private static class Boss extends Thread {
        private List<Worker> workers = new ArrayList<>();

        public void addTask(Worker t) {
            workers.add(t);
        }

        @Override
        public void run() {
            workers.stream().forEach((t) -> t.start());
        }

        public void end(Worker worker) {
            if (worker.getResult() == Result.FAILED) {
                cancel(worker);
            }
        }

        private void cancel(Worker worker) {
            for (Worker w : workers) {
                if (w != worker) w.cancel();
            }
        }


    }

    public static void main(String[] args) throws Exception {

        Boss boss = new Boss();
        Worker t1 = new Worker(boss, "t1", 3, true);
        Worker t2 = new Worker(boss, "t2", 4, true);
        Worker t3 = new Worker(boss, "t3", 1, false);

        boss.addTask(t1);
        boss.addTask(t2);
        boss.addTask(t3);

        //启动线程

        boss.start();

        System.in.read();
    }

    private static enum Result {
        NOTSET, SUCCESSED, FAILED, CANCELLED
    }

    private static class Worker extends Thread {

        private Result result = Result.NOTSET;

        private Boss boss;
        private String name;
        private int timeInSeconds;
        private boolean success;

        private volatile boolean cancelling = false;

        public Worker(Boss boss, String name, int timeInSeconds, boolean success) {
            this.boss = boss;
            this.name = name;
            this.timeInSeconds = timeInSeconds;
            this.success = success;
        }

        public Result getResult() {
            return result;
        }

        @Override
        public void run() {


            int interval = 100;
            int total = 0;

            for (; ; ) {
                SleepHelper.sleepMilli(interval); //cpu密集型
                total += interval;
                if (total / 1000 >= timeInSeconds) {
                    System.out.println(name + " 任务结束！" + result); //正常结束
                    result = success ? Result.SUCCESSED : Result.FAILED;
                    break;
                }

                if (cancelling) {
                    rollback();
                    result = Result.CANCELLED;
                    cancelling = false;
                    System.out.println(name + "任务结束！" + result);
                    break;
                }
            }


            //模拟业务执行时间
            //实际中时间不固定，可能在处理计算任务，或者是IO任务


            boss.end(this);
        }

        private void rollback() {
            //如何书写回滚？
            System.out.println(name + " rollback start...");
            SleepHelper.sleepMilli(500);
            System.out.println(name + " rollback end!");

        }


        public void cancel() {
            //思考一下，如何才能cancel？
            cancelling = true;
            //思考一下，在run中如何处理？
        }
    }


}
底层同步问题（乱序 和 屏障的问题）
CPU存在乱序执行
ALU CPU内部运算单元 访问寄存器的速度，比访问内存的速度快100倍
package com.mashibing.dp.singleton;

import java.util.concurrent.ConcurrentHashMap;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 */
//DCL Double Check Lock
public class Mgr06 {
    private static volatile Mgr06 INSTANCE; //JIT

    private Mgr06() {
    }
    public static Mgr06 getInstance() {
        //业务逻辑代码省略
        if (INSTANCE == null) { //Double Check Lock
            //双重检查
            synchronized (Mgr06.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->{
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }
}

package com.mashibing.dp.singleton;

import java.util.concurrent.ConcurrentHashMap;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 */
//DCL Double Check Lock
public class Mgr06 {
    private static volatile Mgr06 INSTANCE; //JIT

    private Mgr06() {
    }
    public static Mgr06 getInstance() {
        //业务逻辑代码省略
        if (INSTANCE == null) { //Double Check Lock
            //双重检查
            synchronized (Mgr06.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->{
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }
}