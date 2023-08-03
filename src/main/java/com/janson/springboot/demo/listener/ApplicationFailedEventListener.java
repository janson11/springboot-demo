package com.janson.springboot.demo.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description: 在启动Spring发生异常时触发
 * @Author: shanjian
 * @Date: 2023/8/2 3:47 PM
 */
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
        System.out.println("===========>>>>6 applicationFailedEvent is triggered");
        System.out.println(applicationFailedEvent.getTimestamp());
        System.out.println("===========>>>>6  End");
    }
}
