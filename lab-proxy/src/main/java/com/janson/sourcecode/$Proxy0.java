package com.janson.sourcecode;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/19 20:07
 **/

import com.janson.service.MyInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Proxy 生成的代理类，可以看到，其继承了 Proxy，并且实现了 被代理类的接口MyInterface
 */
public final class $Proxy0 extends Proxy implements MyInterface {
    private static Method m1;
    private static Method m0;
    private static Method m3;
    private static Method m2;

    static {
        try {
            $Proxy0.m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            $Proxy0.m0 = Class.forName("java.lang.Object").getMethod("hashCode", (Class<?>[]) new Class[0]);
            // 实例化 MyInterface 的 play()方法
            $Proxy0.m3 = Class.forName("com.shuitu.test.MyInterface").getMethod("play", (Class<?>[]) new Class[0]);
            $Proxy0.m2 = Class.forName("java.lang.Object").getMethod("toString", (Class<?>[]) new Class[0]);
        } catch (NoSuchMethodException ex) {
            throw new NoSuchMethodError(ex.getMessage());
        } catch (ClassNotFoundException ex2) {
            throw new NoClassDefFoundError(ex2.getMessage());
        }
    }

    public $Proxy0(final InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

    public final void play() {
        try {
            // 这个 h 其实就是我们调用 Proxy.newProxyInstance()方法 时传进去的 ProxyFactory对象(它实现了
            // InvocationHandler接口)，该对象的 invoke()方法 中实现了对目标对象的目标方法的增强。
            // 看到这里，利用动态代理实现方法增强的实现原理就全部理清咯
            super.h.invoke(this, $Proxy0.m3, null);
        } catch (Error | RuntimeException error) {
            throw new RuntimeException();
        } catch (Throwable t) {
            throw new UndeclaredThrowableException(t);
        }
    }

    public final boolean equals(final Object o) {
        try {
            return (boolean) super.h.invoke(this, $Proxy0.m1, new Object[]{o});
        } catch (Error | RuntimeException error) {
            throw new RuntimeException();
        } catch (Throwable t) {
            throw new UndeclaredThrowableException(t);
        }
    }

    public final int hashCode() {
        try {
            return (int) super.h.invoke(this, $Proxy0.m0, null);
        } catch (Error | RuntimeException error) {
            throw new RuntimeException();
        } catch (Throwable t) {
            throw new UndeclaredThrowableException(t);
        }
    }

    public final String toString() {
        try {
            return (String) super.h.invoke(this, $Proxy0.m2, null);
        } catch (Error | RuntimeException error) {
            throw new RuntimeException();
        } catch (Throwable t) {
            throw new UndeclaredThrowableException(t);
        }
    }
}