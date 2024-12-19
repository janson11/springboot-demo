package com.janson;

import com.janson.handler.JDKInvocationHandler;
import com.janson.service.Person;
import com.janson.service.impl.Girl;

/**
 * @Description: ${DESCRIPTION}
 * <p>
 * JDK动态代理小结
 * <p>
 * 实际上JDK动态代理是采用字节重组，重新生成对象来代替原始对象以达到动态代理的目的。JDK Proxy生成对象的步骤如下以媒婆为示例。
 * <p>
 * 拿到被代理对象（Girl）的引用（Person），并且获取到它所有的接口（本例中的findLove方法），反射获取。（这也是为什么JDK动态代理需要代理类和被代理类都要事先同一接口的原因）
 * JDK Proxy类重新生成一个新的类，这个新的类需要实现被代理类(Girl)的所有实现的所有接口（findLove)
 * 动态生成java代码，把新加的业务逻辑方法由一定的逻辑代码去调用（在代码中体现）
 * 编译新生成的java代码.class
 * 再重新加载到JVM中运行
 * <p>
 * 作者：勤奋的派大星
 * 链接：https://www.jianshu.com/p/8aee43cbc373
 * 来源：简书
 * 以上这个过程就叫字节码重组。JDK中有一个规范，在ClassPath下只要是$开头的class文件一般都是自动生成的。
 * @Author: Janson
 * @Date: 2024/9/2 14:15
 **/
public class JDKProxyTest {
    public static void main(String[] args) {
        //生成$Proxy0的class文件，也就是代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // JDKInvocationHandler 实现了 InvocationHandler接口，其中的 getInstanse()方法 利用 Proxy类
        // 生成了 target目标对象 的代理对象，并返回；且 JDKInvocationHandler 持有对 target 的引用，可以在
        // invoke() 中完成对 target 相应方法的调用，以及目标方法前置后置的增强处理

        // 这个 person 就是 JDK 的 Proxy类 动态生成的代理类 $Proxy0 的实例，该实例中的方法都持有对
        // invoke()方法 的回调，所以当调用其方法时，就能够执行 invoke() 中的增强处理
        Person person = (Person) new JDKInvocationHandler().getInstance(new Girl());
        // 这样可以看到 person 的 Class 到底是什么
        System.out.println(person.getClass());
        // 这里实际上调用的就是 $Proxy0代理类 中对 play()方法 的实现，结合下面的代码可以看到
        // findLove()方法 通过 super.h.invoke() 完成了对 InvocationHandler对象(JDKInvocationHandler)中
        // invoke()方法 的回调，所以我们才能够通过 invoke()方法 实现对 target对象 方法的
        // 前置后置增强处理
        person.findLove();
        // 总的来说，就是在 invoke()方法 中完成 target目标方法 的调用，及前置后置增强，
        // JDK 动态生成的代理类中对 invoke()方法 进行了回调
    }
}