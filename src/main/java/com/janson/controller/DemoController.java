package com.janson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/12/27 11:05 上午
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String hello() {
        LocalDateTime now = LocalDateTime.now();

        return now.toString() + "hello";
    }
}
