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
public class Interceptor2 {

    @RuntimeType
    public void intercept(@This Object obj,
                            @AllArguments Object[] allArguments){
        System.out.println("after constructor!");
    }
}
