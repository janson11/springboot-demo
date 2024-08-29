package com.janson.springcloud.labx03.feigndemo.consumer.controller;

import com.janson.springcloud.labx03.feigndemo.consumer.feign.UploadFeignClient;
import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/29 17:22
 **/
@RestController
public class UploadFileController {

    @Autowired
    private UploadFeignClient uploadFeignClient;

    @PostMapping("/uploadFile")
    public String upload(@RequestPart("file") MultipartFile file) {
        return uploadFeignClient.handleFileUpload(file);
    }
}
