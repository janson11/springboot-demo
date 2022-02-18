package com.janson.algorithm.chapter1;

import java.util.Arrays;

/**
 * @Description: 如何衡量程序运行的效率
 * <p>
 * 可见，对于同一个问题，采用不同的编码方法，对时间和空间的消耗是有可能不一样的。
 * 因此，工程师在写代码的时候，一方面要完成任务目标；另一方面，也需要考虑时间复杂度和空间复杂度，以求用尽可能少的时间损耗和尽可能少的空间损耗去完成任务。
 *
 *
 * 在这里，我们给出一些经验性的结论：
 *
 * 一个顺序结构的代码，时间复杂度是 O(1)。
 *
 * 二分查找，或者更通用地说是采用分而治之的二分策略，时间复杂度都是 O(logn)。这个我们会在后续课程讲到。
 *
 * 一个简单的 for 循环，时间复杂度是 O(n)。
 *
 * 两个顺序执行的 for 循环，时间复杂度是 O(n)+O(n)=O(2n)，其实也是 O(n)。
 *
 * 两个嵌套的 for 循环，时间复杂度是 O(n²)。
 *
 *
 * 复杂度通常包括时间复杂度和空间复杂度。在具体计算复杂度时需要注意以下几点。
 *
 * 它与具体的常系数无关，O(n) 和 O(2n) 表示的是同样的复杂度。
 *
 * 复杂度相加的时候，选择高者作为结果，也就是说 O(n²)+O(n) 和 O(n²) 表示的是同样的复杂度。
 *
 * O(1) 也是表示一个特殊复杂度，即任务与算例个数 n 无关。
 * @Author: shanjian
 * @Date: 2022/2/18 5:14 下午
 */
public class ChapterDemo1 {

    /**
     * 这段代码的输入数据是 a，数据量就等于数组 a 的长度。
     * 代码中有两个 for 循环，作用分别是给b 数组初始化和赋值，其执行次数都与输入数据量相等。因此，代码的时间复杂度就是 O(n)+O(n)，也就是 O(n)。
     * <p>
     * 空间方面主要体现在计算过程中，对于存储资源的消耗情况。上面这段代码中，我们定义了一个新的数组 b，它与输入数组 a 的长度相等
     * 。因此，空间复杂度就是 O(n)。
     */
    public static void swap1() {
        int a[] = {1, 2, 3, 4, 5};
        int b[] = new int[5];
        System.out.println("a:" + Arrays.toString(a) + " b:" + Arrays.toString(b));
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        System.out.println("a1:" + Arrays.toString(a) + " b1:" + Arrays.toString(b));
        for (int i = 0; i < a.length; i++) {
            b[a.length - i - 1] = a[i];
        }
        System.out.println(Arrays.toString(b));
    }

    /**
     * 这段代码包含了一个 for 循环，执行的次数是数组长度的一半，时间复杂度变成了 O(n/2)。根据复杂度与具体的常系数无关的性质
     * ，这段代码的时间复杂度也就是 O(n)。
     * <p>
     * 空间方面，我们定义了一个 tmp 变量，它与数组长度无关。也就是说，输入是 5 个元素的数组，
     * 需要一个 tmp 变量；输入是 50 个元素的数组，依然只需要一个 tmp 变量。因此，空间复杂度与输入数组长度无关，即 O(1)。
     */
    public static void swap2() {
        int a[] = {1, 2, 3, 4, 5};
        int tmp = 0;
        for (int i = 0; i < (a.length / 2); i++) {
            tmp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = tmp;
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 方法就是，暂存当前最大值并把所有元素遍历一遍即可。因为代码的结构上需要使用一个 for 循环，对数组所有元素处理一遍，所以时间复杂度为 O(n)。
     */
    public static void swap3() {
        int a[] = {1, 4, 3};
        int maxVal = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > maxVal) {
                maxVal = a[i];
            }
        }
        System.out.println(maxVal);
    }

    /**
     * 下面的代码定义了一个数组 a = [1, 3, 4, 3, 4, 1, 3]，并会在这个数组中查找出现次数最多的那个数字：
     *
     * 我们采用了双层循环的方式计算：第一层循环，我们对数组中的每个元素进行遍历；
     * 第二层循环，对于每个元素计算出现的次数，并且通过当前元素次数 time_tmp 和全局最大次数变量 time_max 的大小关系，持续保存出现次数最多的那个元素及其出现次数。由于是双层循环，这段代码在时间方面的消耗就是 n*n 的复杂度，也就是 O(n²)
     *
     *
     */
    public static void swap4() {
        int a[] = {1, 3, 4, 3, 4, 1, 3};
        int valMax = -1;
        int timeMax = 0;

        for (int i = 0; i < a.length; i++) {
            int timeTmp = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == a[j]) {
                    timeTmp += 1;
                }
            }
            if (timeTmp > timeMax) {
                timeMax = timeTmp;
                valMax = a[i];
            }
        }
        System.out.println(valMax);
    }


    /**
     *
     * 时间复杂度：O(n*n*n) + O(n*n*n) = O(n^3)+O(n^3) =2 O(n^3)即时间复杂度O(n^3)
     *
     */
    private static void swap5(){
        int n=100;
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {

                }

                for (int m = 0; m <n; m++) {

                }
            }
        }
    }


    public static void main(String[] args) {
//        swap1();
//        swap2();
//        swap3();
        swap4();
    }


}
