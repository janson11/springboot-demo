package com.janson.thread.alibaba.safe;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/14 9:42 上午
 */
public class VisibilityDemo {

    // 状态标识
    private static boolean is = true;
//    private static volatile boolean is = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (VisibilityDemo.is) {
//                    synchronized (this) { //142420387     205235741
//                        i++;
//                    }
                    i++;
//                    System.out.println("while i " + i);
                }
                System.out.println(i);
            }
        }).start();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 设置false 使上面的线程结束while循环
        VisibilityDemo.is = false;
        System.out.println("被设置false了");
    }


}
