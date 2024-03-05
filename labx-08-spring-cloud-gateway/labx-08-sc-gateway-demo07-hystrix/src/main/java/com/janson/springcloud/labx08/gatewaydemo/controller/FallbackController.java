package com.janson.springcloud.labx08.gatewaydemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/5 17:30
 */
@RestController
public class FallbackController {

    private Logger logger = LoggerFactory.getLogger(FallbackController.class);

    @GetMapping("/fallback")
    public String fallback(ServerWebExchange exchange) {
        Throwable executionException = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        logger.error("[fallback][发生异常]", executionException);
        return "服务降级..." + executionException.getMessage();
    }
}
