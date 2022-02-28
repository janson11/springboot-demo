package com.janson.algorithm.interview;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;

/**
 * @Description: 排序数组中的两个数字之和
 * 题目：输入一个递增排序的数组和一个值k，请问如何在数组中找出两个和为k的数字并返回它们的下标？
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。例如，输入数组[1，2，4，6，10]，k的值为8，
 * 数组中的数字2与6的和为8，它们的下标分别为1与3。
 * @Author: shanjian
 * @Date: 2022/2/28 11:10 上午
 */
public class TwoSum {


    /**
     * 两个指针的解法
     * 时间复杂度是O(n),空间复杂度为O(1) 另外一个解法：使用Map,时间复杂度是O(n),空间复杂度为O(n)
     *
     * @param numbers
     * @param target
     */
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j && numbers[i] + numbers[j] != target) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{i, j};
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 4, 6, 10};
        int target = 8;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }
}
