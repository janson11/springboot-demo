package com.janson.springboot.lab17.dynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description: ${DESC}
 * @Author: ${USER}
 * @Date: ${DATE} ${TIME}
 */
@SpringBootApplication
@MapperScan(basePackages = "com.janson.springboot.lab17.dynamicdatasource.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)// https://blog.csdn.net/cristianoxm/article/details/122883619
public class DynamicDatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }
}