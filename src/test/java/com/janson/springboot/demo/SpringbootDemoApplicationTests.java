package com.janson.springboot.demo;

import com.janson.springboot.demo.service.MediaUploadService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest(classes = SpringbootDemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class SpringbootDemoApplicationTests {


    @Autowired
    private MediaUploadService mediaUploadService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testAsyncThread() throws ExecutionException, InterruptedException {
        List<String> msgs = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            msgs.add(i + "");
        }
        List<String> strings = mediaUploadService.batchUpload(msgs);
        System.err.println(strings);
    }

}
