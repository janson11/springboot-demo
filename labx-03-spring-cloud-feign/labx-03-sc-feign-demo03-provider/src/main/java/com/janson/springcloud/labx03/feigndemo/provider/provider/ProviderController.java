package com.janson.springcloud.labx03.feigndemo.provider.provider;

import com.janson.springcloud.labx03.feigndemo.provider.api.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 20:54
 **/
@RestController
public class ProviderController implements ProviderService {

    private Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Value("${server.port}")
    private Integer serverPort;


    @GetMapping("/echo")
    public String echo(String name)  {
        // 模拟业务处理时间
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 记录被调用的日志
        logger.info("[echo][被调用啦 name({})]", name);
        return serverPort + " -provider:" + name;
    }

}
