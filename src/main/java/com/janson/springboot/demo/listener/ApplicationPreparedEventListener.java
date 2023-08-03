package com.janson.springboot.demo.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description: 在Bean定义加载之后、刷新上下文之前触发
 * @Author: shanjian
 * @Date: 2023/8/2 3:47 PM
 */
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        System.out.println("===========>>>>3 applicationPreparedEvent is triggered");
        System.out.println(applicationPreparedEvent.getTimestamp());
        System.out.println("===========>>>>3  End");
    }
}
