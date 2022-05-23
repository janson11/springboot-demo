package com.janson.design.proxy.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/23 2:21 下午
 */
public class NonOwnerInvocationHandler implements InvocationHandler {
    PersonBean personBean;

    public NonOwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("setGeekRating")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("set")) {
                return method.invoke(personBean, args);
            }

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
