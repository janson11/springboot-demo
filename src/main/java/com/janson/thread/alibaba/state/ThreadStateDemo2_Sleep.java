package com.janson.thread.alibaba.state;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/3/31 4:20 下午
 */
public class ThreadStateDemo2_Sleep {

    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                while (running) {
                    System.out.println("t1 running");
                }
                System.out.println("t1 running is false,t1 将sleep了");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("new t1 t1的状态：" + t1.getState());
        t1.start();
        System.out.println("t1.start()后的状态：" + t1.getState());
        // 将控制标识设置false，让子线程退出循环
        running = false;

        Thread.sleep(2000L);
        System.out.println("t1.sleep()时的状态：" + t1.getState());

    }

}
