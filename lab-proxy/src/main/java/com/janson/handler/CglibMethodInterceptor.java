package com.janson.handler;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/9/2 14:45
 **/
public class CglibMethodInterceptor implements MethodInterceptor {

    /**
     * 获取代理对象，这里的参数是Class类型
     * 为啥是class类型，因为这里接收的参数是父类的class，我们需要继承这个父类
     * 重写方法生成新的类
     *
     * @param clazz
     * @return
     */
    public Object getInstance(Class clazz) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        // 设置目标类
        enhancer.setSuperclass(clazz);
        // 设置拦截器,也就是这个类的intercepor方法
        enhancer.setCallback(this);
        // 生成代理类并返回一个实例
        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
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
