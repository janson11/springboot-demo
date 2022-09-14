package com.janson.algorithm.basic;

/**
 * @Description: 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N),且要求不能用非基于比较的排序【算法必考，借用桶排序但是没有使用桶，算法非常妙】
 * @Author: shanjian
 * @Date: 2022/9/7 10:15 上午
 */
public class MaxGap {

    public static int maxGap(int[] nums) {
        if (nums==null ||nums.length<2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        if (min==max) {
            return 0;
        }

        boolean[] hasNum = new boolean[len+1];
        int[] maxs = new int[len+1];
        int[] mins = new int[len+1];
        int bid =0;
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i],len,min,max);
            mins[bid] = hasNum[bid]?Math.min(mins[bid],nums[i]):nums[i];
            maxs[bid] = hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
            hasNum[bid] = true;
        }
        int res =0;
        int lastMax = maxs[0];
        int i =1;
        for (;i<=len;i++) {
            if (hasNum[i]) {
                res = Math.max(res,mins[i]-lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private static int bucket(long num, long len, long min, long max) {
        return (int)((num-min)*len/(max-min));

    }

}
