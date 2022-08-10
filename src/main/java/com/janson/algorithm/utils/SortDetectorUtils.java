package com.janson.algorithm.utils;

import java.util.Arrays;

/**
 * @Description: 排序对数器工具类
 * @Author: shanjian
 * @Date: 2022/8/10 9:59 上午
 */
public class SortDetectorUtils {

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    // for test 2、绝对正确的方法
    public static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    // for test 对数器  1、随机数组发生器
    public static int[] generateRandomArray(int size, int value) {
        // Math.random() ->double [0,1)
        // (int) ((size + 1) * Math.random()) ——> [0,size]整数
        // 生成长度随机的数组
        int[] arr = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((value + 1) * Math.random()) - (int) (value * Math.random());
        }
        return arr;
    }
}
