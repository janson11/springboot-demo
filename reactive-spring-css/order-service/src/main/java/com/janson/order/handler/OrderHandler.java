package com.janson.order.handler;

import com.janson.order.domain.Order;
import com.janson.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/5 4:43 下午
 */
@Configuration
public class OrderHandler {
    @Autowired
    private OrderService orderService;

    public Mono<ServerResponse> getOrderByOrderNumber(ServerRequest request) {
        String orderNumber = request.pathVariable("orderNumber");
        return ServerResponse.ok().body(this.orderService.getOrderByOrderNumber(orderNumber), Order.class);
    }


}
