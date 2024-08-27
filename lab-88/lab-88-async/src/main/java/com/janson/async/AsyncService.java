package com.janson.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/8/26 15:57
 **/
@Service
public class AsyncService {

    @Autowired
    private ApplicationContext applicationContext;

    @Async/*("asyncTaskExecutor")*/
    public void testAsync() {
/*        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        String applicationName = applicationContext.getBean(AsyncService.class).getClass().getName();

        System.out.println("current thread name is " + Thread.currentThread().getName() + ", application name is " + applicationName);
    }

}
