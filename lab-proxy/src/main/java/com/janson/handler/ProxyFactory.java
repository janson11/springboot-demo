package com.janson.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 一般会使用实现了 InvocationHandler接口 的类作为代理对象的生产工厂，
 * 并且通过持有 被代理对象target，来在 invoke()方法 中对被代理对象的目标方法进行调用和增强，
 * 这些我们都能通过下面这段代码看懂，但代理对象是如何生成的？invoke()方法 又是如何被调用的呢？
 * @Author: Janson
 * @Date: 2024/12/19 14:48
 **/
public class ProxyFactory implements InvocationHandler {

    private Object target = null;

    public Object getInstace(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;
        System.out.println("前置增强");
        ret = method.invoke(target, args);
        System.out.println("后置增强");
        return ret;
    }
}