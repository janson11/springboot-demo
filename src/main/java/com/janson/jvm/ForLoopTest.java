package com.janson.jvm;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/12/10 22:12
 **/
public class ForLoopTest {
    private static int[] numbers = {1, 6, 8};

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        for (int number : numbers) {
            ma.submit(number);
        }
        double avg = ma.getAvg();
    }
}
