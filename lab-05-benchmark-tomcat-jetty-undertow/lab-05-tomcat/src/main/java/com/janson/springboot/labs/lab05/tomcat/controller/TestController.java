package com.janson.springboot.labs.lab05.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: test controller
 * @Author: shanjian
 * @Date: 2024/2/26 17:12
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }

    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(100L);
        return "sleep";
    }

}
