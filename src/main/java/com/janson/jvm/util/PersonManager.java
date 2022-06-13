package com.janson.jvm.util;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/13 10:48 上午
 */
public class PersonManager {

    private String interest ="";

    /**
     *  是否有兴趣
     */
    private boolean isInterest;

    public String getPersonInterest(){
        if (isInterest) {
            interest ="羽毛球";
        }
        return interest;
    }

    public void setInterest(Boolean interest) {
        isInterest = interest;
    }
}
