package com.janson.jvm;

import com.janson.jvm.objectpool.datasource.JansonDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/2/25 16:48
 **/
@SpringBootApplication
public class JVMApplication {
    public static void main(String[] args) {
        SpringApplication.run(JVMApplication.class, args);
    }


    @Bean
    @Primary
    public JansonDataSource dataSource() {
        return new JansonDataSource();
    }
}
