package com.janson.springboot.demo.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/10/10 10:53 AM
 */
public interface MediaUploadService {

    List<String> batchUpload(List<String> msgs) throws ExecutionException, InterruptedException;

    public String upload();


}
