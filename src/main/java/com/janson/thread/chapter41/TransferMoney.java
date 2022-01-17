package com.janson.thread.chapter41;

/**
 * @Description: 模拟转账发生死锁
 * @Author: Janson
 * @Date: 2022/1/17 11:55
 **/
public class TransferMoney implements Runnable {

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
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
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
