package com.janson.springcloud.labx08.gatewaydemo;

import com.alibaba.cloud.sentinel.gateway.ConfigConstants;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/2/27 16:16
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        System.setProperty(SentinelConfig.APP_TYPE, ConfigConstants.APP_TYPE_SCG_GATEWAY);// 【重点】设置应用类型为 Spring Cloud Gateway
        SpringApplication.run(GatewayApplication.class, args);
    }
}
