package com.janson.thread.chapter36;

/**
 * @Description: 演示可见性带来的问题
 * @Author: Janson
 * @Date: 2022/1/12 23:31
 **/
public class VisibilityProblem {

    volatile int a = 10;
    volatile int b = 20;

    private void change() {
        a = 30;
        b = a;
    }

    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }


    public static void main(String[] args) {
        while (true) {
            VisibilityProblem visibilityProblem = new VisibilityProblem();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    visibilityProblem.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    visibilityProblem.print();
                }
            }).start();
        }
    }
}
