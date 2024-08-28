package com.janson.springcloud.labx03.feigndemo.consumer;

import com.janson.springcloud.labx03.feigndemo.consumer.config.DefaultFeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 15:47
 **/
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = DefaultFeignClientConfiguration.class)
public class Demo02BConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo02BConsumerApplication.class, args);
    }
}
