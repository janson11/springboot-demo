package com.janson.springcloud.labx03.feigndemo.consumer.feign;

import com.janson.springcloud.labx03.feigndemo.consumer.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/29 17:19
 **/
@FeignClient(name = "demo-provider", contextId = "uploadFeignClient", configuration = MultipartSupportConfig.class)
public interface UploadFeignClient {

    /**
     * 上传文件接口
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleFileUpload(@RequestPart("file") MultipartFile file);
}
