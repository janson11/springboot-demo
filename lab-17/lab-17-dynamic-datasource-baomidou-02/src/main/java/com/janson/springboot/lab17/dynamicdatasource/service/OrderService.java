package com.janson.springboot.lab17.dynamicdatasource.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.janson.springboot.lab17.dynamicdatasource.constant.DBConstants;
import com.janson.springboot.lab17.dynamicdatasource.daoobject.OrderDO;
import com.janson.springboot.lab17.dynamicdatasource.mapper.OrderMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/29 10:24
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @DS(DBConstants.DATASOURCE_MASTER)
    public void add(OrderDO orderDO) {
        OrderDO exists = orderMapper.selectById(orderDO.getId());
        System.out.println(exists);

        // 插入订单
        orderMapper.insert(orderDO);
    }


    public OrderDO findById(Integer id) {
        return orderMapper.selectById(id);
    }

}
