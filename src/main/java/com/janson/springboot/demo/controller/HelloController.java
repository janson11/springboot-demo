package com.janson.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/8/16 7:38 PM
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello" + LocalDateTime.now();
    }
}
