package com.janson.springboot.demo.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description: 在Spring最开始启动的时候触发

 * @Author: shanjian
 * @Date: 2023/8/2 3:47 PM
 */
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("===========>>>>1 applicationStartingEvent is triggered");
        System.out.println(applicationStartingEvent.getTimestamp());
        System.out.println("===========>>>>1  End");
    }
}
