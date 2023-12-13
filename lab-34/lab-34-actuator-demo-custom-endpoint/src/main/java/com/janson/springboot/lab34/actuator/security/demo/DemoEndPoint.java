package com.janson.springboot.lab34.actuator.security.demo;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/8 5:25 PM
 */
@Component
@Endpoint(id = "demo")
public class DemoEndPoint {

    @ReadOperation
    public Map<String, String> hello() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "the wrorld");
        map.put("author", "janson");
        map.put("秃头", "true");
        return map;
    }

}
