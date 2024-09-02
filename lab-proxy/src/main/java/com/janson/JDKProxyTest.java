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
        Person person = (Person) new JDKInvocationHandler().getInstance(new Girl());
        person.findLove();
    }
}