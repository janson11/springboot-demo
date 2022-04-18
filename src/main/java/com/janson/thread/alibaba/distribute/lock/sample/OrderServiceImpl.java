package com.janson.thread.alibaba.distribute.lock.sample;

/**
 * @Description: 创建订单接口
 * @Author: shanjian
 * @Date: 2022/4/18 10:54 上午
 */
public class OrderServiceImpl implements OrderService {

    private OrderCodeGenerator ocg = new OrderCodeGenerator();

    @Override
    public void createOrder() {
        // 获取订单号
        String orderCode = ocg.getOrderCode();
        System.out.println(Thread.currentThread().getName() + "===============>" + orderCode);

    }
}
