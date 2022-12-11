package com.janson.jvm.classload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/12/11 15:12
 **/
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {
        String appPath = "file:\\D:\\IdeaProjects\\springboot-demo\\src\\main\\java\\com\\janson\\jvm\\classload";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmClassLoaderPrintPath.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class.forName("com.janson.jvm.classload.Hello");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
