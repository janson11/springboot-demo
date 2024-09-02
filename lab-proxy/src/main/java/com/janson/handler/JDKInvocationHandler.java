package com.janson.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: InvocationHandler调用处理器
 * @Author: Janson
 * @Date: 2024/9/2 14:19
 **/
public class JDKInvocationHandler implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        // 获取class信息
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;

    }

    /**
     * 前置方法
     */
    public void before() {
        System.out.println("我是媒婆,现在准备开始为你寻找条件合适的相亲对象");
    }


    /**
     * 后置方法
     */
    public void after() {
        System.out.println("条件合适的话就准备嘿嘿嘿");
    }

}
