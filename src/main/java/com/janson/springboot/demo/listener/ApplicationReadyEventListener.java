package com.janson.springboot.demo.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description: 在调用applicaiton命令之后触发
 * @Author: shanjian
 * @Date: 2023/8/2 3:47 PM
 */
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("===========>>>>5 applicationReadyEvent is triggered");
        System.out.println(applicationReadyEvent.getTimestamp());
        System.out.println("===========>>>>5  End");
    }
}
