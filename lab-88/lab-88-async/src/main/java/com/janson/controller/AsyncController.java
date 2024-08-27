package com.janson.controller;

import com.janson.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/26 15:59
 **/
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/test")
    public String async() {
        asyncService.testAsync();
        return (LocalDateTime.now()) + " async 调用成功";
    }
}
