package com.janson.springboot.demo.config;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/8/2 4:05 PM
 */
public class InitializingBeanTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("============>>> InitializingBeanTest ");
        System.out.println(System.currentTimeMillis());
        System.out.println("============>>> END ");
    }
}
