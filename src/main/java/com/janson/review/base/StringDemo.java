package com.janson.review.base;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/14 5:41 下午
 */
public class StringDemo {
    public static void main(String[] args) {

        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        String s3 = s1.intern();
        System.out.println(s1.intern() == s3);  // true
    }
}
