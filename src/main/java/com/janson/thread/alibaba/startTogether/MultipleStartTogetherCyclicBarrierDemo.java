package com.janson.thread.alibaba.startTogether;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/7 3:16 下午
 */
public class MultipleStartTogetherCyclicBarrierDemo {

    // 阶段计数
    static int P = 0;

    public static void main(String[] args) {
        // 参与数
        int parties = 3;
        final CyclicBarrier cb = new CyclicBarrier(parties, new Runnable() {
            @Override
            public void run() {
                switch (P) {
                    case 0:
                        System.out.println("*************第一阶段，大家都到公司了，出发去公园！");
                        break;
                    case 1:
                        System.out.println("*************第二阶段，大家都到公园大门，出发去餐厅！");
                        break;
                    case 2:
                        System.out.println("*************第三阶段，大家都到餐厅了，开始用餐！");
                        break;
                }
                // 阶段数增1
                P++;
            }
        });

        // 用于生成随机等待时长的随机数对象
        final Random rd = new Random();
        for (int i = 0; i < parties; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String staff = "员工【" + Thread.currentThread().getName() + "】";
                    try {
                        /**
                         * 第一阶段，从家出发，到公司
                         */
                        System.out.println(staff +"从家出发了。。。");
                        Thread.sleep(rd.nextInt(5000));
                        System.out.println(staff +"到达公司！");
                        //协同 第一次等待大家到齐
                        cb.await();

                        /**
                         * 第二阶段任务 去公园玩，再到公园大门口集合
                         */
                        System.out.println(staff +"出发去公园玩。。。");

                        // 在公园玩了很久
                        Thread.sleep(rd.nextInt(5000));
                        System.out.println(staff +"到达公园大门集合！");


                        //协同 第二次等待大家到齐
                        cb.await();

                        /**
                         * 第三阶段任务 去餐厅
                         */
                        System.out.println(staff +"出发去餐厅。。。");
                        Thread.sleep(rd.nextInt(5000));

                        //协同 第三次等待大家到齐
                        cb.await();


                        /**
                         * 第四阶段任务 就餐
                         */
                        System.out.println(staff +"开始用餐。。。");
                        Thread.sleep(rd.nextInt(5000));
                        // 吃完饭回家了
                        System.out.println(staff +"回家了。。。");

                    }catch (Exception e) {
                       e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
