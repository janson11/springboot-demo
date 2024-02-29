package com.janson.springcloud.labx08.gatewaydemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/2/27 17:22
 */
@Configuration
public class GatewayConfig {

    private Logger logger = LoggerFactory.getLogger(GatewayConfig.class);

    @Bean
    @Order(1)
    public GlobalFilter firstGlobalFilter() {
        return (exchange, chain) -> {
            logger.info("[first][pre]");
            return chain.filter(exchange).then(Mono.<Void>fromRunnable(() -> logger.info("[first][post]]")));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter secondGlobalFilter() {
        return (exchange, chain) -> {
            logger.info("[second][pre]");
            return chain.filter(exchange).then(Mono.<Void>fromRunnable(() -> logger.info("[second][post]]")));
        };
    }

    @Bean
    @Order(3)
    public GlobalFilter thirdGlobalFilter() {
        return (exchange, chain) -> {
            logger.info("[third][pre]");
            return chain.filter(exchange).then(Mono.<Void>fromRunnable(() -> logger.info("[third][post]]")));
        };
    }

}
