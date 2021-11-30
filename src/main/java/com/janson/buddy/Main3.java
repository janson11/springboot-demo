package com.janson.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/11/30 9:51
 **/
public class Main3 {
    public static void main(String[] args) throws Exception {
        Class<? extends Foo> loaded = new ByteBuddy()
                .subclass(Foo.class)
                .defineMethod("moon", String.class, Modifier.PUBLIC)
                .withParameter(String.class, "s")
                .intercept(FixedValue.value("Zero!"))
                .defineField("name", String.class, Modifier.PUBLIC)
                .implement(DemoInterface.class)
                .intercept(FieldAccessor.ofField("name"))
                .make().load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();
        Foo dynamicFoo = loaded.newInstance();
        Method m = loaded.getDeclaredMethod("moon", String.class);
        System.out.println(m.invoke(dynamicFoo, new Object[]{""}));
        Field field = loaded.getField("name");
        field.set(dynamicFoo, "Zero-Name");
        System.out.println(field.get(dynamicFoo));
        Method setNameMethod = loaded.getDeclaredMethod("set", String.class);
        setNameMethod.invoke(dynamicFoo, new Object[]{"Zero-Name2"});
        Method getNameMethod = loaded.getDeclaredMethod("get");
        System.out.println(getNameMethod.invoke(dynamicFoo, new Object[]{}));
    }
}
