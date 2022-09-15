package com.janson.rocketmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/9/15 2:34 下午
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "success" + LocalDateTime.now().toString();
    }
}
