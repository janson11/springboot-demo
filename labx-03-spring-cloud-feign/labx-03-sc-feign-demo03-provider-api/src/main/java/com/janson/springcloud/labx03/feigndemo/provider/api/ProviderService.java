package com.janson.springcloud.labx03.feigndemo.provider.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/28 19:58
 **/
public interface ProviderService {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);
}
