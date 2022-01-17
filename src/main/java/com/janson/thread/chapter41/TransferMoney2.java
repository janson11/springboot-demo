package com.janson.thread.chapter41;

/**
 * @Description: 模拟转账发生死锁
 * 经过思考，我们可以发现，其实转账时，并不在乎两把锁的相对获取顺序。转账的时候，
 * 我们无论先获取到转出账户锁对象，还是先获取到转入账户锁对象，只要最终能拿到两把锁，就能进行安全的操作。所以我们来调整一下获取锁的顺序，使得先获取的账户和该账户是“转入”或“转出”无关，而是使用 HashCode 的值来决定顺序，从而保证线程安全。
 * @Author: Janson
 * @Date: 2022/1/17 11:55
 **/
public class TransferMoney2 implements Runnable {

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
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            synchronized (from) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    if (from.balance - amount < 0) {
                        System.out.println("余额不足，转账失败。");
                        return;
                    }
                    from.balance -= amount;
                    to.balance += amount;
                    System.out.println("成功转账，" + amount + " 元");
                }
            }
        } else if (fromHash >toHash){
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

    }

    public static void main(String[] args) throws InterruptedException {
        TransferMoney2 r1 = new TransferMoney2();
        TransferMoney2 r2 = new TransferMoney2();
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
