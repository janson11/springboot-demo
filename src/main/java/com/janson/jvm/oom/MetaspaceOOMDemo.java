package com.janson.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: 元空间内存溢出示例
 *
 *接着我们可以设置一下这个程序的JVM参数，限制他的Metaspace区域比较小一点，如下所示，我们把这个程序的JVM中的
 * Metaspace区域设置为仅仅10m：
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * 接着我们可以在上述代码中做点手脚，大家看到上面的代码是有一个while循环的，所以他会不停的创建Car类的子类
 *
 * Caused by: java.lang.OutOfMemoryError: Metaspace
 *
 * @Author: shanjian
 * @Date: 2022/8/1 10:09 上午
 */
public class MetaspaceOOMDemo {


    public static void main(String[] args) {
        long counter = 0;
        while (true) {
            System.out.println("目前创建了"+(++counter)+"个Car类的子类了");
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")) {
                        System.out.println("启动汽车之前，先进行自动安全检查......");
                        return methodProxy.invokeSuper(o,objects);
                    } else {
                        return methodProxy.invokeSuper(o,objects);
                    }
                }
            });
            Car car = (Car) enhancer.create();
            car.run();
        }
    }

    static class Car {
        public void run() {
            System.out.println("汽车启动，开始行驶......");
        }
    }
}
