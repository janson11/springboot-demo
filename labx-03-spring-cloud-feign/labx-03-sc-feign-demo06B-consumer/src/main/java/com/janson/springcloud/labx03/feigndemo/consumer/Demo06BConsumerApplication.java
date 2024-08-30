package com.janson.springcloud.labx03.feigndemo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 15:47
 **/
@SpringBootApplication
@EnableFeignClients
public class Demo06BConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo06BConsumerApplication.class, args);
    }
}
