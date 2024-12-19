package com.janson.sourcecode;

import com.janson.service.MyInterface;
import com.janson.service.impl.TargetObject;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Description: 生成代理类的源码
 * @Author: Janson
 * @Date: 2024/12/19 16:37
 **/
public class GeneratorProxyClass {


    /**
     * 将 ProxyGenerator 生成的动态代理类的输出到文件中，利用反编译工具 luyten 等就可
     * 以看到生成的代理类的源码咯，下面给出了其反编译好的代码实现
     */
    @Test
    public void generatorSrc() {
        byte[] bytesFile = ProxyGenerator.generateProxyClass("$Proxy0", TargetObject.class.getInterfaces());
        FileOutputStream fos = null;
        try {
//            String path = System.getProperty("user.dir" + "\\$Proxy0.class");
            String path = "/Users/shanjian/IdeaProjects/study/springboot-demo/lab-proxy/src/main/java/com/janson/sourcecode/$Proxy0.class";
            File file = new File(path);
            fos = new FileOutputStream(file);
            fos.write(bytesFile);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}