package com.janson.design.proxy.javaproxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/23 2:30 下午
 */
public class MatchMakingTestDrive {
    HashMap<String,PersonBean> datingDB = new HashMap<>();

    public static void main(String[] args) {
        MatchMakingTestDrive test = new MatchMakingTestDrive();
        test.drive();
    }

    public MatchMakingTestDrive(){
        initializeDatabase();
    }

    public void drive(){
        PersonBean joe = getPersonFromDatabase("Joe Javabean");
        PersonBean ownerProxy = getOwnerProxy(joe);
        System.out.println("Name is "+ownerProxy.getName());
        ownerProxy.setInterests("bowling,Go");
        System.out.println("Interests set from owner proxy");
        try {
            ownerProxy.setGeekRating(10);
        } catch (Exception e) {
            System.out.println("can't set rating from owner proxy");
        }

        System.out.println("Rating is "+ownerProxy.getGeekRating());
        PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
        System.out.println("Name is "+nonOwnerProxy.getName());
        try {
            nonOwnerProxy.setInterests("bowling, Go");
        }catch (Exception e){
            System.out.println("Can't set interests from non owner proxy");
        }
        nonOwnerProxy.setGeekRating(3);
        System.out.println("Rating set from non owner proxy");
        System.out.println("Rating is "+nonOwnerProxy.getGeekRating());

    }

    PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),personBean.getClass().getInterfaces(),new OwnerInvocationHandler(personBean));
    }

    PersonBean getNonOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),personBean.getClass().getInterfaces(),new NonOwnerInvocationHandler(personBean));
    }

    PersonBean getPersonFromDatabase(String name) {
        return (PersonBean)datingDB.get(name);
    }

    private void initializeDatabase() {
        PersonBean personBean = new PersonBeanImpl();
        personBean.setName("Joe Javabean");
        personBean.setInterests("cars,computers，music");
        personBean.setGeekRating(7);
        datingDB.put(personBean.getName(),personBean);

        PersonBean kelly = new PersonBeanImpl();
        kelly.setName("Kelly Javabean");
        kelly.setInterests("ebay,movies，music");
        kelly.setGeekRating(6);
        datingDB.put(kelly.getName(),kelly);
    }


}
