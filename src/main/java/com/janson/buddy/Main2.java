package com.janson.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.any;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/11/29 4:27 下午
 */
public class Main2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<? extends DB> constructor = new ByteBuddy()
                .subclass(DB.class)
                .constructor(any())
                .intercept(SuperMethodCall.INSTANCE.andThen(
                        MethodDelegation.withDefaultConfiguration()
                                .to(new Interceptor2())
                )).make().load(Main2.class.getClassLoader(),INJECTION)
                .getLoaded()
                .getConstructor(String.class);
        constructor.newInstance("MySQL");
    }
}
