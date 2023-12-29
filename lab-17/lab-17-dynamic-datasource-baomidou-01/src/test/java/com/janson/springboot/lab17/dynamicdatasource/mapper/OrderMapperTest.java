package com.janson.springboot.lab17.dynamicdatasource.mapper;

import com.janson.springboot.lab17.dynamicdatasource.DynamicDatasourceApplication;
import com.janson.springboot.lab17.dynamicdatasource.daoobject.OrderDO;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/29 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApplication.class)
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSelectById() {
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
    }


}