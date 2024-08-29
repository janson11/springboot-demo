package com.janson.springcloud.labx03.feigndemo.consumer.feign;

import com.janson.springcloud.labx03.feigndemo.consumer.dto.DemoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 15:52
 **/
@FeignClient(name = "demo-provider",contextId = "demo-provider-feign-client")
public interface DemoProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

    // GET 方式一  最推荐
    @GetMapping("/get_demo")
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    // GET 方式二  相对推荐
    @GetMapping("/get_demo")
    DemoDTO getDemo(@RequestParam("username") String username, @RequestParam("password") String password);

    // GET 方式三  不推荐
    @GetMapping("/get_demo")
    DemoDTO getDemo(@RequestParam Map<String, Object> params);

    //POST 方式
    @PostMapping("/post_demo")
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);

}
