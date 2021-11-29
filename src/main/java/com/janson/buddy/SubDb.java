package com.janson.buddy;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.named;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/11/29 3:33 下午
 */
public class SubDb {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        String helloWorld = new ByteBuddy()
                .subclass(DB.class)
                .method(named("hello"))
                .intercept(MethodDelegation.to(Interceptor.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), INJECTION)
                .getLoaded()
                .newInstance().hello("World");
        System.out.println(helloWorld);
    }
}
