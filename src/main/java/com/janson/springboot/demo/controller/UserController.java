package com.janson.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description: user controller
 * @Author: Janson
 * @Date: 2023/7/8 9:19
 **/
@RestController
@RequestMapping("user")
public class UserController {

    public static void main(String[] args) {
        long l = TimeUnit.MINUTES.toMillis(3);
        System.out.println(l);
    }


}
