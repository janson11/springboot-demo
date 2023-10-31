package com.janson.springboot.demo.controller;

import com.janson.springboot.demo.service.MediaUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/10/10 11:09 AM
 */
@RestController
public class MediaController {

    @Autowired
    private MediaUploadService mediaUploadService;

    @GetMapping("/media")
    public List<String> media() throws Exception {
        List<String> msgs = new ArrayList();
        for (int i = 0; i < 2000; i++) {
            msgs.add(i + "");
        }
        return mediaUploadService.batchUpload(msgs);
    }

    @GetMapping("/media1")
    public String media1() {
        return mediaUploadService.upload();
    }
}
