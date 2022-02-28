package com.janson.algorithm.interview;

/**
 * @Description: 乘积小于k的子数组
 * <p>
 * 题目：输入一个由正整数组成的数组和一个正整数k，请问数组中有多少个数字乘积小于k的连续子数组？例如，输入数组[10，5，2，6]，k的值为100，
 * 有8个子数组的所有数字的乘积小于100，它们分别是[10]、[5]、[2]、[6]、[10，5]、[5，2]、[2，6]和[5，2，6]。
 * 分析：虽然这个题目是关于子数组数字的乘积的，和面试题8（关于子数组数字之和）看起来不太一样，但求解的思路大同小异，仍然可以利用两个指针求解。
 * @Author: shanjian
 * @Date: 2022/2/28 4:07 下午
 */
public class NumSubarrayProductLessThanK {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        long product = 1;
        int left = 0;
        int count = 0;
        for (int right = 0; right < nums.length; ++right) {
            product *= nums[right];
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            count += right >= left ? right - left + 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 5, 2, 6};
        int k = 100;
        System.out.println(numSubarrayProductLessThanK(nums, k));
    }
}
