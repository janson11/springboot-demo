package com.janson.springboot.lab17.dynamicdatasource.service;

import com.janson.springboot.lab17.dynamicdatasource.DynamicDatasourceApplication;
import com.janson.springboot.lab17.dynamicdatasource.daoobject.OrderDO;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/29 11:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApplication.class)
public class OrderServiceTest {


    @Autowired
    private OrderService orderService;

    @Test
    public void testAdd() {
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(20);
        orderService.add(orderDO);
    }


    @Test
    public void testFindById() {
        for (int i = 0; i < 10; i++) {
            OrderDO orderDo = orderService.findById(1);
            System.out.println(orderDo);
        }
    }
}