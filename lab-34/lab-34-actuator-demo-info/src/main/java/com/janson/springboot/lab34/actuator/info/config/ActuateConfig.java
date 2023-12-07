package com.janson.springboot.lab34.actuator.info.config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.actuate.info.SimpleInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/7 11:08 AM
 */
@Configuration
public class ActuateConfig {

    @Bean
    public InfoContributor exampleInfo() {
        return new SimpleInfoContributor("example", Collections.singletonMap("key", "value"));
    }

    @Bean
    public InfoContributor exampleInfo02() {
        return new MapInfoContributor(Collections.singletonMap("example02", "nicai"));
    }

}
