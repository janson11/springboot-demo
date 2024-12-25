package com.janson.lab00.spring.testdemo;

import com.janson.lab00.spring.collectiontype.Stu;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/25 23:43
 **/
public class TestSpring5Demo1 {

    @Test
    public void testCollectoion() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Stu stu = context.getBean("stu", Stu.class);
        stu.test();
    }
}
