package com.janson.buddy;

import net.bytebuddy.ByteBuddy;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

import net.bytebuddy.implementation.FixedValue;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/11/29 3:01 下午
 */
public class SubFoo {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Foo dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class))
                .intercept(FixedValue.value("One!"))
                .method(named("foo"))
                .intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1)))
                .intercept(FixedValue.value("Three!"))
                .make()
                .load(SubFoo.class.getClassLoader(), INJECTION)
                .getLoaded()
                .newInstance();
        System.out.println(dynamicFoo.bar());
        System.out.println(dynamicFoo.foo());
        System.out.println(dynamicFoo.foo(null));
    }
}
