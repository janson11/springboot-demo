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
//    public static void main(String[] args) {
//        int[] arr = new int[]{2, 5, 7, 0, 3, 4, 22, 19, 43, 100};
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));
//    }


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


    // for test 大样本测试
    public static void main(String[] args) {
        int testTime = 500000;
        int size = 10;
        int value = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(size, value);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            bubbleSort(arr1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr3);
                break;
            }

        }
        System.out.println(succeed ? "Nice!" :"");
    }

    private static void printArray(int[] arr3) {
        System.out.println(Arrays.toString(arr3));
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
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
