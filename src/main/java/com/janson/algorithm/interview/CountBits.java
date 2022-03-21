package com.janson.algorithm.interview;

import java.util.Arrays;

/**
 * @Description: 前n个数字二进制形式中1的个数
 * 题目：输入一个非负数n,请计算0到n之间每个数字的二进制形式中1的个数，并输出一个数组。
 * 例如：输入的n为4，由于0、1、2、3、4的二进制形式中1的个数分别为0、1、1、2、1，因此输出数组[0、1、1、2、1]
 * @Author: shanjian
 * @Date: 2022/2/25 3:40 下午
 */
public class CountBits {

    public static int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; ++i) {
            int j = i;
            while (j != 0) {
                result[i]++;
                j = j & (j - 1);
            }
        }
        return result;
    }


    public static int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    public static int[] countBits2(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(4)));
        System.out.println(Arrays.toString(countBits1(4)));
        System.out.println(Arrays.toString(countBits2(4)));
    }

}
