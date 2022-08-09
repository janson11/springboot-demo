package com.janson.algorithm.basic;

import java.util.Arrays;

/**
 * @Description: 冒泡排序 时间复杂度O(N^2)
 * 思想：0->1 总是对0和1的两个数进行排序，如果左边的数据大于右边的数就交互位置，每次只会把排出来一个数放到最右边。
 * 第一次 0——>N    index：0和1
 * 第二次 0——>N-1  index：1和2
 * 第三次 0——>N-2  index：2和3
 * 第四次 0——>N-3  index：3和4
 * <p>
 * aN^2+bN+1 ~=O(N^2)
 * ...
 * @Author: shanjian
 * @Date: 2022/8/8 10:13 上午
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 7, 0, 3, 4, 22, 19, 43, 100};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
