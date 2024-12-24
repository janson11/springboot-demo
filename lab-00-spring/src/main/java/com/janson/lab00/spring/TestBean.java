package com.janson.lab00.spring;

import com.janson.lab00.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/24 9:08
 **/
public class TestBean {


    @Test
    public void testAdd() {
        // 1 、加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");

        //2、获取配置创建的对象
        UserService userService = context.getBean("userService", UserService.class);
        //3、调用对象的方法
        userService.add();
    }
}
