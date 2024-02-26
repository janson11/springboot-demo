package com.janson.springboot.lab12.myabtis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/1/22 17:35
 */
@SpringBootApplication
@MapperScan(basePackages = "com.janson.springboot.lab12.myabtis.mapper")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
