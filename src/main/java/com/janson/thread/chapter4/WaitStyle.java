package com.janson.thread.chapter4;

/**
 * @Description: wait形式实现生产者消费者模式
 * @Author: shanjian
 * @Date: 2021/12/27 6:43 下午
 */
public class WaitStyle {
    public static void main(String[] args) {
        MyBlockQueue myBlockQueue = new MyBlockQueue(10);
        Producer producer = new Producer(myBlockQueue);
        Consumer consumer = new Consumer(myBlockQueue);
        new Thread(producer).start();
        new Thread(consumer).start();


    }


    static class Producer implements Runnable {
        private MyBlockQueue storage;

        public Producer(MyBlockQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    storage.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable {
        private MyBlockQueue storage;

        public Consumer(MyBlockQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    storage.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
