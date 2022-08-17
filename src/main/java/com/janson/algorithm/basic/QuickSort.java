package com.janson.algorithm.basic;

/**
 * @Description: 快速排序
 * 经典快排，每次只搞定一个数
 * 改进的快排，每次搞定一个数组 new int[]{less + 1, more};
 * @Author: shanjian
 * @Date: 2022/8/17 9:57 上午
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr,L+(int)(Math.random()*(R-L+1)),R);
            int[] p =partition(arr,L,R);
            quickSort(arr,L,p[0]-1);
            quickSort(arr,p[1]+1,R);

        }
    }


    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                swap(arr, --more, L++);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
