package com.janson.thread.alibaba.atomic;

import com.janson.mybatis.demo.po.User;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/18 9:10 上午
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User user1 = new User("张三",23);
        User user2 = new User("李四",25);
        User user3 = new User("王五",20);

        // 初始化为user1
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);

        // 把user2赋给atomicReference
        System.out.println("user1，user2："+atomicReference.compareAndSet(user1,user2));
        System.out.println(atomicReference.get());

        // 把user3赋给atomicReference
        System.out.println("user1，user3："+atomicReference.compareAndSet(user1,user3));
        System.out.println(atomicReference.get());

    }
}
