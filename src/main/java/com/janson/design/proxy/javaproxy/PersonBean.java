package com.janson.design.proxy.javaproxy;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/23 1:54 下午
 */
public interface PersonBean {

    String getName();

    String getGender();

    String getInterests();

    int getGeekRating();

    void setName(String name);

    void setGender(String gender);

    void setInterests(String interests);

    void setGeekRating(int rating);


}
