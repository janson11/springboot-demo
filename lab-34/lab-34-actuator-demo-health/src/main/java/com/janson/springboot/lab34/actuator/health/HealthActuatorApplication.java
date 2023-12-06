package com.janson.springboot.lab34.actuator.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/6 5:20 PM
 */
@SpringBootApplication
public class HealthActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthActuatorApplication.class,args);
    }
}
