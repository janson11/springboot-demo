package com.janson.interview;

import lombok.SneakyThrows;

/**
 * @Description: 哲学家就餐问题
 * 模拟哲学家问题 OOA - OOD - DDD
 * class : 哲学家 class : 筷子
 * 筷子：编号
 * 哲学家：左手的筷子 右手的筷子 编号
 * <p>
 * 死锁一般具有2把以上的锁，在锁定一把的时候等待另外一把锁
 * <p>
 * 哲学家就餐问题解决方案：
 * <p>
 * 两把锁合并一把锁（5把， 5把锁合成一把锁，筷子集合，锁定整个对象）
 * 混进一个左撇子
 * 效率更高的写法，奇数 偶数分开，混进一半的左撇子
 * @Author: Janson
 * @Date: 2021/12/23 22:09
 **/
public class DeadLock01 {

    public static class ChopStick {

    }


    public static void main(String[] args) {
        ChopStick cs0 = new ChopStick();
        ChopStick cs1 = new ChopStick();
        ChopStick cs2 = new ChopStick();
        ChopStick cs3 = new ChopStick();
        ChopStick cs4 = new ChopStick();
        Philosohper p0 = new Philosohper("p0", 0, cs0, cs1);
        Philosohper p1 = new Philosohper("p1", 1, cs1, cs2);
        Philosohper p2 = new Philosohper("p2", 2, cs2, cs3);
        Philosohper p3 = new Philosohper("p3", 3, cs3, cs4);
        Philosohper p4 = new Philosohper("p4", 4, cs4, cs0);
        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();


    }

    public static class Philosohper extends Thread {
        private ChopStick left;
        private ChopStick right;
        private int index;

        public Philosohper(String name, int index, ChopStick left, ChopStick right) {
            this.setName(name);
            this.index = index;
            this.left = left;
            this.right = right;
        }


        @SneakyThrows
        @Override
        public void run() {
            //左撇子算法 也可以index % 2 == 0
            if (index == 0) {
                synchronized (left) {
                    Thread.sleep(1);
                    synchronized (right) {
                        Thread.sleep(1);
                        System.out.println(index + " 号 哲学家已吃完");
                    }
                }
            } else {
                synchronized (right) {
                    Thread.sleep(1);
                    synchronized (left) {
                        Thread.sleep(1);
                        System.out.println(index + " 号 哲学家已吃完");
                    }
                }
            }
        }
    }
}