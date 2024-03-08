package com.janson.springcloud.labx08.gatewaydemo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/8 16:58
 */
@Component
public class DubboFilter implements GlobalFilter, Ordered {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        String schemePrefix = exchange.getAttribute(GATEWAY_SCHEME_PREFIX_ATTR);
        if (url == null || (!"dubbo".equals(url.getScheme()) && !"dubbo".equals(schemePrefix))) {
            return chain.filter(exchange);
        }
        String result = restTemplate.getForObject(url, String.class);
        return null;
    }

    @Override
    public int getOrder() {
        return 10100;
    }
}
