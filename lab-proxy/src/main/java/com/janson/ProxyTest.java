package com.janson;

import com.janson.handler.ProxyFactory;
import com.janson.service.MyInterface;
import com.janson.service.impl.TargetObject;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/12/19 15:53
 **/
public class ProxyTest {
    public static void main(String[] args) {
        TargetObject target = new TargetObject();
        // ProxyFactory 实现了 InvocationHandler接口，其中的 getInstanse()方法 利用 Proxy类
        // 生成了 target目标对象 的代理对象，并返回；且 ProxyFactory 持有对 target 的引用，可以在
        // invoke() 中完成对 target 相应方法的调用，以及目标方法前置后置的增强处理
        ProxyFactory proxyFactory = new ProxyFactory();
        // 这个 mi 就是 JDK 的 Proxy类 动态生成的代理类 $Proxy0 的实例，该实例中的方法都持有对
        // invoke()方法 的回调，所以当调用其方法时，就能够执行 invoke() 中的增强处理
        MyInterface mi = (MyInterface) proxyFactory.getInstace(target);
        // 这样可以看到 mi 的 Class 到底是什么
        System.err.println(mi.getClass());
        // 这里实际上调用的就是 $Proxy0代理类 中对 play()方法 的实现，结合下面的代码可以看到
        // play()方法 通过 super.h.invoke() 完成了对 InvocationHandler对象(proxyFactory)中
        // invoke()方法 的回调，所以我们才能够通过 invoke()方法 实现对 target对象 方法的
        // 前置后置增强处理
        mi.play();
        // 总的来说，就是在 invoke()方法 中完成 target目标方法 的调用，及前置后置增强，
        // JDK 动态生成的代理类中对 invoke()方法 进行了回调




    }
}
