package com.janson.algorithm.basic;

import java.util.Arrays;

/**
 * @Description:
 * 给定一个数组arr,和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @Author: shanjian
 * @Date: 2022/8/16 9:52 上午
 */
public class LeftNumRight {


    public static void main(String[] args) {
        int[] arr = new int[]{1,100,2300,5,7,7,7,2,0,3,4,8,9,10,23};
        leftNumRight(arr,0,7);
        System.out.println("arr[]:"+Arrays.toString(arr));
    }

    public static void leftNumRight(int[] arr,int L,int num) {
        int less = L-1;
        int cur = L;
        while (cur<arr.length) {
            if (arr[cur]<num) {
                swap(arr,++less,cur++);
            }  else {
                // >num
                cur++;
            }
        }
    }


    public static void swap(int[] arr,int i,int j) {
        int tmp = arr[i];
        arr[i]= arr[j];
        arr[j] = tmp;
    }

}
