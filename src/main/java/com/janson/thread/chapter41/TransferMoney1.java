package com.janson.thread.chapter41;

/**
 * @Description: 模拟转账发生死锁
 * 可以看到 transferMoney 的变化就在于，在两个 synchronized 之间，
 * 也就是获取到第一把锁后、获取到第二把锁前，我们加了睡眠 500 毫秒的语句。此时再运行程序，会有很大的概率发生死锁，从而导致控制台中不打印任何语句，而且程序也不会停止。
 * @Author: Janson
 * @Date: 2022/1/17 11:55
 **/
public class TransferMoney1 implements Runnable {

    int flag;
    static Account a = new Account(500);
    static Account b = new Account(500);

    static class Account {
        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        // 先获取两把锁，然后开始转账
        synchronized (to) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (from) {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                    return;
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账，" + amount + " 元");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TransferMoney1 r1 = new TransferMoney1();
        TransferMoney1 r2 = new TransferMoney1();
        r1.flag = 1;
        r2.flag = 0;
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);

    }
}
