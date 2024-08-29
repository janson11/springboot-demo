package com.janson.springcloud.labx03.feigndemo.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 15:52
 **/
//@FeignClient(name = "demo-provider")
@FeignClient(name = "demo-provider", url = "https://www.iocoder.cn")
public interface DemoProviderFeignClient {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

    @GetMapping("/")
    String echo(@RequestParam("name") String name);
}
