package com.janson.order.router;

import com.janson.order.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/5 4:57 下午
 */
@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> routeOrder(OrderHandler orderHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/orders/{orderNumber}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                orderHandler::getOrderByOrderNumber);
    }

}
