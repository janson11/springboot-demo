package com.janson.buddy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/11/29 4:13 下午
 */
public class Interceptor1 {

    @RuntimeType
    public Object intercept(@This Object obj,
                            @AllArguments Object[] allArguments,
                            @Origin Method method,
                            @Super DB db,
                            @Morph OverrideCallable callable){
        try {
            System.out.println("before");
            Object result = callable.call(allArguments);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("finally");
        }
    }
}
