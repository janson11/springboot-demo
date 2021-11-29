package com.janson.buddy;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.named;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2021/11/29 4:18 下午
 */
public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        String hello = new ByteBuddy()
                .subclass(DB.class)
                .method(named("hello"))
                .intercept(MethodDelegation.withDefaultConfiguration()
                .withBinders(Morph.Binder.install(OverrideCallable.class))
                .to(new Interceptor1()))
                .make()
                .load(Main.class.getClassLoader(),INJECTION)
                .getLoaded()
                .newInstance()
                .hello("World");
        System.out.println(hello);
    }
}
