package com.janson.algorithm.basic;

/**
 * @Description: 堆排序
 * 时间复杂度O(N*logN),额外空间复杂度O(1)
 * 堆结构非常重要
 * 1、堆结构的heapInsert与heapify
 * 2、堆结构的增大和减少
 * 3、如果只是建立堆的过程，时间复杂度为O(N)
 * 4、优先级队列结构，就是堆结构
 * @Author: shanjian
 * @Date: 2022/8/19 10:07 上午
 */
public class HeapSort {

    public static void heapsort(int[] arr) {
        if (arr ==null || arr.length<2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 0~i
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while (heapSize>0) {
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }

    /**
     * 将数组变换成大根堆（节点的父子比节点大）
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        // (index-1)/2 父位置
        while(arr[index] >arr[(index-1)/2]) {
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
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
