package com.janson.algorithm.basic;

/**
 * @Description: 归并排序 时间复杂度：O(N*logN)，额外空间复杂度O(N)
 * @Author: shanjian
 * @Date: 2022/8/12 10:05 上午
 */
public class MergeSort {
    
    public static void mergeSort(int[] arr) {
        if (arr==null ||arr.length<2) {
            return;
        }
        sortProcess(arr,0,arr.length-1);
    }


    public  static void sortProcess(int[] arr,int L,int R) {
        if (L==R){
            return;
        }
        // L和R中点的位置 (L+R)/2
        int mid = L +((R-L)>>1);
        // T(N/2)
        sortProcess(arr,L,mid);
        // T(N/2)
        sortProcess(arr,mid+1,R);
        // O(N)
        merge(arr,L,mid,R);
        // T(N) = 2T(N/2)+O(N)
//        T(N)=a*T(N/b) +O(N^d)
        // a =2  b=2 d=1
//        2） log(b,a) =d ->复杂度为O(N^d*logN)
        // 时间复杂度：O(N*logN)
    }



    // L ------mid |mid+1 --------R
    // p1          | p2
    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R-L+1];
        int i=0;
        int p1= L;
        int p2 = mid+1;
        while (p1<=mid && p2<=R) {
            help[i++]=arr[p1]<arr[p2] ?arr[p1++]:arr[p2++];
        }
        //两个必有且只有一个越界
        while (p1<=mid) {
            help[i++] = arr[p1++];
        }
        while (p1<=R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j]=help[j];
        }
    }
}
