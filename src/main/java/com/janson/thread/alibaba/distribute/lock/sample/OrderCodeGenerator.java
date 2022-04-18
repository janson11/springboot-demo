package com.janson.thread.alibaba.distribute.lock.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/18 10:51 上午
 */
public class OrderCodeGenerator {

//    private static volatile ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    // 自增长序列
    private int i = 0;

    // 按照年月日小时分钟秒自增长序列的规则生成订单编号
    public String getOrderCode() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        String value = sdf.format(now) + ++i;
//        if (!concurrentHashMap.isEmpty() && concurrentHashMap.get(value) == 1) {
//            System.out.println("key已经存在 key" + value);
//        } else {
//            concurrentHashMap.put(value, 1);
//        }
        return value;
    }
}
