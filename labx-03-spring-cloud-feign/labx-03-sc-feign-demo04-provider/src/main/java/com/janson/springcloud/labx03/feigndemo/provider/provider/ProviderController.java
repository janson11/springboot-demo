package com.janson.springcloud.labx03.feigndemo.provider.provider;

import com.janson.springcloud.labx03.feigndemo.provider.dto.DemoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/27 20:54
 **/
@RestController
public class ProviderController {

    private Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Value("${server.port}")
    private Integer serverPort;


    @GetMapping("/echo")
    public String echo(String name) throws InterruptedException {
        // 模拟业务处理时间
        Thread.sleep(100L);
        // 记录被调用的日志
        logger.info("[echo][被调用啦 name({})]", name);
        return serverPort + " -provider:" + name;
    }


    @GetMapping("/get_demo")
    public DemoDTO getDemo(DemoDTO demoDTO) {
        return demoDTO;
    }

    @PostMapping("/post_demo")
    public DemoDTO postDemo(@RequestBody DemoDTO demoDTO) {
        return demoDTO;
    }

    /**
     * 文件上传
     */
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
        logger.info("[handleFileUpload][file({})]", file.getOriginalFilename());
        return file.getOriginalFilename();
    }

}
