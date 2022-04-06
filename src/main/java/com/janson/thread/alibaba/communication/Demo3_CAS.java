package com.janson.thread.alibaba.communication;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/6 10:52 上午
 */
public class Demo3_CAS {
    static volatile  int t=1;
    static volatile  int i=0;

    public static void main(String[] args) {
        new Thread( ()->{
           while (i<10) {
               while (t!=1) {
               }
               System.out.println("t1: "+(++i));
               t=2;
           }
        }).start();

        new Thread( () ->{
           while (i<10) {
               while (t!=2) {
               }
               System.out.println(" t2: "+(++i));
               t=1;
           }
        }).start();
    }

}
