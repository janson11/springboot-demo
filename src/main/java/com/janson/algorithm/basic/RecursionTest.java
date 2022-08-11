package com.janson.algorithm.basic;

/**
 * @Description: 递归测试
 *  任何递归行为都可以改成非递归【一定对的，改成非递归自己压栈】
 *  剖析递归行为和递归行为时间复杂度的估算
 *  T(N)=a*T(N/b) +O(N^d)
 *  N ：原始大小的样本量
 *  n/b:子过程的样本量
 *  a: 发生子过程的次数
 *  N^d ：除去调用子过程之外，剩下的代价
 *
 *  1） log(b,a) >d ->复杂度为O(N^log(b,a)
 *  2） log(b,a) =d ->复杂度为O(N^d*logN)
 *  3） log(b,a) <d ->复杂度为O(N^d)
 *
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
