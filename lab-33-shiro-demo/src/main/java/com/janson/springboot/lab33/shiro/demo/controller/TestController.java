package com.janson.springboot.lab33.shiro.demo.controller;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/11/30 7:30 PM
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequiresGuest
    @GetMapping("/echo")
    public String demo() {
        return "示例返回";
    }

    @GetMapping("/home")
    public String home() {
        return "我是首页";
    }

    @RequiresRoles("ADMIN")
    @GetMapping("/admin")
    public String admin() {
        return "我是管理员";
    }

    @RequiresRoles("NORMAL")
    @GetMapping("/normal")
    public String normal() {
        return "我是普通用户";
    }


}
