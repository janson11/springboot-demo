package com.janson.springboot.lab17.dynamicdatasource.service;

import com.janson.springboot.lab17.dynamicdatasource.DynamicDatasourceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void method01() {
        orderService.method01();
    }

    @Test
    public void method02() {
        orderService.method02();
    }

    @Test
    public void method03() {
        orderService.method03();
    }

    @Test
    public void method04() {
        orderService.method04();
    }

    @Test
    public void method05() {
        orderService.method05();
    }
}