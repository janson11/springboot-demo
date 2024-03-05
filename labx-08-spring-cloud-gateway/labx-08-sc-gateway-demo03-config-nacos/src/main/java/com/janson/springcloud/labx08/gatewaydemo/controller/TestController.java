package com.janson.springcloud.labx08.gatewaydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/5 10:40
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${nacos.test:test}")
    private String test;

    @GetMapping("/test")
    public String test() {
        return System.currentTimeMillis() + test;
    }
}
