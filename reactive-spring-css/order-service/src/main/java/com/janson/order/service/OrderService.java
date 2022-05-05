package com.janson.order.service;

import com.janson.order.domain.Order;
import com.janson.order.repository.ReactiveOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/5 4:49 下午
 */
@Service
public class OrderService {

    @Autowired
    private ReactiveOrderRepository reactiveOrderRepository;

    public Mono<Order> getOrderByOrderNumber(String orderNumber){
        return reactiveOrderRepository.getOrderByOrderNumber(orderNumber);
    }

}
