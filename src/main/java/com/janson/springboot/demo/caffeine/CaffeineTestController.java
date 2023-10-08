package com.janson.springboot.demo.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @Description:
 *
 *     Caffeine.newBuilder()
 *                 .maximumSize(10)
 *                 .expireAfterWrite(Duration.ofMillis(5))
 *                 .refreshAfterWrite(Duration.ofMillis(1))
 *                 .build();
 *
 * @Author: shanjian
 * @Date: 2023/9/4 5:18 PM
 */
@RestController
public class CaffeineTestController {



    @GetMapping("/caffeine")
    @Cacheable(value = "users",key = "#userId")
    public String testCaffeine(Long userId) {



        return "caffeine";
    }

}
