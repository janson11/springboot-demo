package com.janson.springcloud.labx03.feigndemo.consumer.controller;

import com.janson.springcloud.labx03.feigndemo.consumer.dto.DemoDTO;
import com.janson.springcloud.labx03.feigndemo.consumer.feign.DemoProviderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 15:57
 **/
@RestController
public class ConsumerController {

    @Autowired
    private DemoProviderFeignClient demoProviderFeignClient;

    @GetMapping("/hello2")
    public String hello2(String name) {
        // 通过Feign调用Provider的接口
        String response = demoProviderFeignClient.echo(name);
        // 返回结果
        return "consumer:" + response;
    }


    @GetMapping("/test_get_demo")
    public DemoDTO testGetDemo(@RequestParam("type") int type, DemoDTO demoDTO) {
        // 方式一
        if (type == 1) {
            return demoProviderFeignClient.getDemo(demoDTO);
        } else if (type == 2) {
            return demoProviderFeignClient.getDemo(demoDTO.getUsername(), demoDTO.getPassword());
        } else {
            // 方式三
            Map<String, Object> params = new HashMap<>();
            params.put("username", demoDTO.getUsername());
            params.put("password", demoDTO.getPassword());
            return demoProviderFeignClient.getDemo(params);
        }
    }

    @PostMapping("/test_post_demo")
    public DemoDTO testPostDemo(@RequestBody DemoDTO demoDTO) {
        return demoProviderFeignClient.postDemo(demoDTO);
    }
}