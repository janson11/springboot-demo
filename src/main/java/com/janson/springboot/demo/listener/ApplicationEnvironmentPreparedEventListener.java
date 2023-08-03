package com.janson.springboot.demo.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description: 在Spring已经准备好上下文但是上下文尚未创建的时候触发
 * @Author: shanjian
 * @Date: 2023/8/2 3:47 PM
 */
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        System.out.println("===========>>>>2 applicationEnvironmentPreparedEvent is triggered");
        System.out.println(applicationEnvironmentPreparedEvent.getTimestamp());
        System.out.println("===========>>>>2  End");
    }
}
