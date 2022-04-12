package com.janson.jvm;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/11 4:31 下午
 */
public class TestCode {
    public int foo() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
