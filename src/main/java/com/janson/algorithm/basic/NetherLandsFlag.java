package com.janson.algorithm.basic;

import java.util.Arrays;

/**
 * @Description: 荷兰国旗问题
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @Author: shanjian
 * @Date: 2022/8/16 9:31 上午
 */
public class NetherLandsFlag {

    public static void main(String[] args) {
        int[] arr = new int[]{1,100,2300,5,7,7,7,2,0,3,4,8,9,10,23};
        System.out.println(Arrays.toString(partition(arr,0,arr.length-2,7)));
        System.out.println("arr[]:"+Arrays.toString(arr));
    }



    public static int[] partition(int[] arr,int L,int R,int num) {
        int less = L-1;
        int more = R +1;
        int cur = L;
        while (cur<more) {
            if (arr[cur]<num) {
                swap(arr,++less,cur++);
            } else if (arr[cur]>num){
                swap(arr,--more,cur);
            } else {
                // ==num
                cur++;
            }

        }
        return new int[] {less+1,more-1};
    }

    public static void swap(int[] arr,int i,int j) {
        int tmp = arr[i];
        arr[i]= arr[j];
        arr[j] = tmp;
    }
}
