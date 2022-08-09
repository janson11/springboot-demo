package com.janson.algorithm.basic;

import java.util.Arrays;

/**
 * @Description: 选择排序 时间复杂度O(N^2)
 * @Author: shanjian
 *
 * 0——> N-1
 * 1——> N-2
 *
 * @Date: 2022/8/9 9:17 上午
 */
public class SelectionSort {


    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 7, 0, 3, 4, 22, 19, 43, 100};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
