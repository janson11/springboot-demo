package com.janson.thread.chapter42;

/**
 * @Description: 改变一个哲学家拿筷子的顺序解决死锁问题
 * @Author: Janson
 * @Date: 2022/1/17 15:09
 **/
public class DiningPhilosophers1 {

    public static class Philosopher implements Runnable {

        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("思考人生、宇宙、万物、灵魂");
                    synchronized (leftChopstick) {
                        doAction("拿起左边的筷子");
                        synchronized (rightChopstick) {
                            doAction("拿起右边的筷子");
                            doAction("吃饭");
                            doAction("放下右边的筷子");
                        }
                        doAction("放下左边的筷子");
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "      " + action);
            Thread.sleep((long) (Math.random() * 10));
        }

        public static void main(String[] args) {
            Philosopher[] philosophers = new Philosopher[5];
            Object[] chopsticks = new Object[philosophers.length];
            for (int i = 0; i < chopsticks.length; i++) {
                chopsticks[i] = new Object();
            }
            for (int i = 0; i < philosophers.length; i++) {
                Object leftChopstick = chopsticks[i];
                Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
                // 第五个哲学家先拿起右边的筷子，再拿起左边的筷子。
                if (i == philosophers.length - 1) {
                    philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
                } else {
                    philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
                }
                new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
            }
        }
    }

}
