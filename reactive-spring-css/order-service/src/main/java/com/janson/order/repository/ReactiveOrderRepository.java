package com.janson.order.repository;

import com.janson.order.domain.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/5 4:47 下午
 */
public interface ReactiveOrderRepository extends ReactiveMongoRepository<Order,String> {

    Mono<Order> getOrderByOrderNumber(String orderNumber);
}
