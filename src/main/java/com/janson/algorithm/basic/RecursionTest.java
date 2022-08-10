package com.janson.algorithm.basic;

/**
 * @Description: 递归测试
 *  任何递归行为都可以改成非递归【一定对的，改成非递归自己压栈】
 * @Author: shanjian
 * @Date: 2022/8/10 10:22 上午
 */
public class RecursionTest {

    public static void main(String[] args) {
        int[] arr = {4,2,1,3};
        System.out.println(getMax(arr,0,arr.length-1));
    }

    /**
     * 求一个数组中的最大值
     * @param arr 数组
     * @param L 数组的起始位置
     * @param R 数据的末尾为
     * @return
     */
    public static int getMax(int[] arr,int L ,int R) {
        if (L==R) {
            return arr[L];
        }
        int mid = (L+R)/2;
        int maxLeft = getMax(arr,L,mid);
        int maxRight = getMax(arr,mid+1,R);
        return Math.max(maxLeft,maxRight);

    }
}
