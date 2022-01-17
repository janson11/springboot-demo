package com.janson.thread.chapter44;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/17 17:59
 **/
public class Test {
    public static void main(String[] args) {
        final int arr[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * 10;
            System.out.println(arr[i]);
        }
    }
}
