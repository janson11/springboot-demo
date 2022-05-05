package com.janson.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/27 3:03 下午
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public Mono<String> hello() {
        return Mono.just("Hello Word" + LocalDateTime.now().toString());
    }
}
