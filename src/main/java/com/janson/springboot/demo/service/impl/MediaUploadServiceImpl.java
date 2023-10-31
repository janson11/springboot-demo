package com.janson.springboot.demo.service.impl;

import com.janson.springboot.demo.service.MediaUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/10/10 10:54 AM
 */
@Service
@Slf4j
public class MediaUploadServiceImpl implements MediaUploadService {


    @Resource(name = "uploadExecutor")
    private ThreadPoolTaskExecutor executor;


    @Override
    @Async
    public String upload() {
        log.info("upload: {}", Thread.currentThread().getName());
        return "sucess";
    }


    @Override
    @Async
    public List<String> batchUpload(List<String> msgs) throws ExecutionException, InterruptedException {
        log.info("batchUpload Async: {}", Thread.currentThread().getName());
        List<CompletableFuture<String>> futuresList = msgs.stream().map(it -> CompletableFuture.supplyAsync(() -> getData(it), executor)).collect(Collectors.toList());

        CompletableFuture<List<String>> futures = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[msgs.size()])).thenApply(it -> futuresList.stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()));

        return futures.get();
    }

    public String getData(String msg) {
        log.info("batchUpload: {}", Thread.currentThread().getName());
        return msg;
    }

}
