package com.janson.springboot.demo.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description:在刷新上下文之后、调用application命令之前触发
 * @Author: shanjian
 * @Date: 2023/8/2 3:47 PM
 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("===========>>>>4 applicationStartedEvent is triggered");
        System.out.println(applicationStartedEvent.getTimestamp());
        System.out.println("===========>>>>4  End");
    }
}
