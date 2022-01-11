package com.janson.thread.chapter27;

/**
 * @Description: 典型场景2
 * 每个线程内需要保存类似于全局变量的信息（例如在拦截器中获取的用户信息），可以让不同方法直接使用，避免参数传递的麻烦却不想被多线程共享（因为不同线程获取到的用户信息不一样）。
 * <p>
 * 例如，用 ThreadLocal 保存一些业务内容（用户权限信息、从用户系统获取到的用户名、用户ID 等），这些信息在同一个线程内相同，但是不同的线程使用的业务内容是不相同的。
 * @Author: Janson
 * @Date: 2022/1/9 15:20
 **/
public class ThreadLocalDemo7 {

    public static void main(String[] args) {
        new Service1().service1();
    }

    static class Service1 {

        public void service1() {
            User user = new User("lagoujiaoyu");
            UserContextHolder.holder.set(user);
            new Service2().service2();

        }
    }

    static class Service2 {

        public void service2() {
            User user = UserContextHolder.holder.get();
            System.out.println("Service2 拿到用户名：" + user.getName());
            new Service3().service3();

        }
    }

    static class Service3 {

        public void service3() {
            User user = UserContextHolder.holder.get();
            System.out.println("Service3 拿到用户名：" + user.getName());
            UserContextHolder.holder.remove();
        }
    }

    static class UserContextHolder {
        public static ThreadLocal<User> holder = new ThreadLocal<>();
    }

    static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
