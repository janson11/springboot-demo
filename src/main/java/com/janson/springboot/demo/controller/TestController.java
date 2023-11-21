package com.janson.springboot.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/11/21 23:34
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/init")
    public ModelAndView init() {
        int a = 1 / 0;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @PostMapping("/test")
    public ResponseEntity<Void> test() {
        int a = 1 / 0;
        return ResponseEntity.noContent().build();
    }
}
