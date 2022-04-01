package com.janson.thread.alibaba.stop;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/1 9:57 上午
 */
public class StopDemo1 {
    public static void main(String[] args) {
        try {
            Thread t = new Thread() {
                @Override
                public synchronized void run() {
                    try {
                        long start = System.currentTimeMillis();
                        for (int i = 0; i <200000 ; i++) {
                            System.out.println("running.."+i);
                        }
                        System.out.println((System.currentTimeMillis()-start)/1000);
                    }catch (Throwable ex) {
                        System.err.println("Caught in run "+ex);
                        ex.printStackTrace();
                    }
                }
            };
            t.start();
            Thread.sleep(100);
            t.stop();
        }catch (Exception e) {
            System.out.println("Caught in main "+e);
            e.printStackTrace();
        }
    }
}
