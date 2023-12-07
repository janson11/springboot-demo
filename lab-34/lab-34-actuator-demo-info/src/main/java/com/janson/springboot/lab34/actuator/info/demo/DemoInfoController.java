package com.janson.springboot.lab34.actuator.info.demo;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/7 11:11 AM
 */
@Component
public class DemoInfoController implements InfoContributor {


    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("demo", Collections.singletonMap("key", "value"));
    }
}
