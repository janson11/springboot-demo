package com.janson.lab00.spring;

import com.janson.lab00.spring.model.Book;
import com.janson.lab00.spring.model.Orders;
import com.janson.lab00.spring.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/22 16:58
 **/
public class TestSpring5 {


    @Test
    public void testAdd() {
        // 1 、加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2、获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }


    @Test
    public void testBook1() {
        // 1 、加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2、获取配置创建的对象
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        book.testDemo();
    }

    @Test
    public void testOrders() {
        // 1 、加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2、获取配置创建的对象
        Orders order = context.getBean("orders", Orders.class);
        System.out.println(order);
        order.orderTest();
    }

}
