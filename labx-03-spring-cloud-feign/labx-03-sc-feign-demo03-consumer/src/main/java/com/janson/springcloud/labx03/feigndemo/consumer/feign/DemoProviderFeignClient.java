package com.janson.springcloud.labx03.feigndemo.consumer.feign;

import com.janson.springcloud.labx03.feigndemo.provider.api.ProviderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 15:52
 **/
@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient extends ProviderService {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

}
