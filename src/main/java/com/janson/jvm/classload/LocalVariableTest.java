package com.janson.jvm.classload;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/12/10 21:16
 **/
public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
    }
}
