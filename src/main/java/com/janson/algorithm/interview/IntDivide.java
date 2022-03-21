package com.janson.algorithm.interview;

/**
 * @Description: 面试题1：整数除法
 * 题目：输入2个int型整数，它们进行除法计算并返回商，要求不得使用乘号'*'
 * 除号'/'及其余符号'%'。当发生溢出时，返回最大的整数值。假设除数不为0。例如输入15和2，输出15/2结果，即7
 * @Author: shanjian
 * @Date: 2022/2/25 11:12 上午
 */
public class IntDivide {

    /**
     * 1、直观解法是基于减法实现除法，但是需要优化，当被除数很大但除数很小时，减法操作执行的次数会很多
     *
     * @param dividend 被除数
     * @param divisor  除数
     */
    public static int divide(int dividend, int divisor) {
        // 0x80000000 为最小的int型整数 即-2^31
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 2;
        if (dividend > 0) {
            negative--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negative--;
            divisor = -divisor;
        }
        int result = divideCore(dividend, divisor);
        return negative == 1 ? -result : result;
    }

    /**
     * 使用减法实现两个负数的除法，当除数和被除数有一个负数时，商为负数。
     * 因此在使用函数divideCore计算商之后，需要根据除数和被除数的负数的个数调整商的正负号。
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    private static int divideCore(int dividend, int divisor) {
        int result = 0;
        while (dividend <= divisor) {
            int value = divisor;
            int quotient = 1;
            while (value >= Integer.MAX_VALUE / 2 && dividend <= value + value) {
                quotient += quotient;
                value += value;
            }
            result += quotient;
            dividend -= value;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(divide(15,2));
    }
}
