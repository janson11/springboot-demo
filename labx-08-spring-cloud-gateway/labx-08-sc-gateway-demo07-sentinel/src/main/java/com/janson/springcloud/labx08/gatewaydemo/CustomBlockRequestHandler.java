package com.janson.springcloud.labx08.gatewaydemo;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/6 15:07
 */
@Component
public class CustomBlockRequestHandler implements BlockRequestHandler {

    private static final String DEFAULT_BLOCK_MSG_PREFIX = "HAHAHA ~ Blocked by Sentinel:";
    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {

        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)  // 状态码
                .contentType(MediaType.TEXT_PLAIN)// 内容类型为 text/plain 纯文本
                .bodyValue(DEFAULT_BLOCK_MSG_PREFIX); // 错误提示
    }
}
