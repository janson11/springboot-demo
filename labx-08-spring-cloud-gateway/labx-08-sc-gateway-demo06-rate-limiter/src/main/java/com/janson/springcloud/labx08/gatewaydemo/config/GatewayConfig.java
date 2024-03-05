package com.janson.springcloud.labx08.gatewaydemo.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/5 16:46
 */
@Configuration
public class GatewayConfig {

    @Bean
    public KeyResolver ipKeyResolver() {
        return new KeyResolver() {

            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                // 获取请求的IP
                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
            }
        };
    }

}
