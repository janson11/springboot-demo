package com.janson.algorithm.basic;

import java.util.Arrays;

/**
 * @Description: 插入排序  时间复杂度O(N^2)，额外空间复杂度O(1)
 *
 * 从index=1位置进行和之前的位置继续比较交换
 * 扑克牌，拿到一个新牌，需要插入到已经整理好的手中的牌，依据从小到大进行排列
 *
 * 数据正序 1 2 3 4 5 时间复杂度O(N)
 * 数据逆序 5 4 3 2 1 时间复杂度O(N^2)
 * 时间复杂度是和数据有关系的。
 *
 *
 *
 * @Author: shanjian
 * @Date: 2022/8/9 9:36 上午
 */
public class InsertionSort {


    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 7, 0, 3, 4, 22, 19, 43, 100};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
//        arr[i]= arr[i] ^ arr[j];
//        arr[j]= arr[i] ^ arr[j];
//        arr[i]= arr[i] ^ arr[j];
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
