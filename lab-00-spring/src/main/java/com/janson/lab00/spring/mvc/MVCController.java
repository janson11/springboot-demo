package com.janson.lab00.spring.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2025/1/6 19:29
 **/
@RestController
@RequestMapping("/mvc")
public class MVCController {

    @GetMapping("/dao")
    public Object go() {
        return "Hello Spring MVC:" + LocalDateTime.now().toString();
    }
}
