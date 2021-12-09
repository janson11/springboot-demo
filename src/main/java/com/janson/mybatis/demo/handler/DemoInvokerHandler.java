package com.janson.mybatis.demo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/12/9 22:53
 **/
public class DemoInvokerHandler implements InvocationHandler {

    // 真正的业务对象，也就是RealSubject对象
    private Object target;

    public DemoInvokerHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }
}
