package com.janson.algorithm.chapter2;


import com.janson.algorithm.chapter1.ChapterDemo1;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据结构：将“昂贵”的时间复杂度转换成“廉价”的空间复杂度
 * <p>
 * 最终目标是：要采用尽可能低的时间复杂度和空间复杂度，去完成一段代码的开发。
 * @Author: shanjian
 * @Date: 2022/2/18 5:14 下午
 */
public class ChapterDemo2 {


    /**
     * 第 1 个例子，假设有任意多张面额为 2 元、3 元、7 元的货币，现要用它们凑出 100 元，求总共有多少种可能性
     * O(n^3)
     */
    public static void s1() {
        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 / 3); j++) {
                for (int k = 0; k <= (100 / 2); k++) {
                    if (i * 7 + j * 3 + k * 2 == 100) {
                        count += 1;
                    }
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 将方法s1()的无效计算、无效存储剔除，降低时间或者空间的复杂度
     * 时间复杂度O(n^2)
     */
    public static void s2() {
        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 / 3); j++) {
                if ((100 - i * 7 - j * 3 >= 0) && (100 - i * 7 - j * 3) % 2 == 0) {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }


    public static void s2_1() {
        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 - 7 * i / 3); j++) {
                if ((100 - i * 7 - j * 3 >= 0) && (100 - i * 7 - j * 3) % 2 == 0) {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 有两个for循环，不过这两个循环不是嵌套关系，而是顺序执行关系，两个都是O(n)复杂度
     * 因此总体的时间复杂度为O(n)+O(n)就是O(2n)。
     * 根据复杂度与具体的常系数无关的原则。也就是O(n)复杂度
     * 空间方面：由于定义了k-v字典，其字典的元素个数取决于输入数值元素的个数。因此空间复杂度也为
     * O(n)
     *
     * @see ChapterDemo1#swap4()
     */
    public static void s3() {
        int a[] = {1, 2, 3, 4, 5, 5, 6};
        Map<Integer, Integer> map = new HashMap<>(6);
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                map.put(a[i], map.get(a[i]) + 1);
            } else {
                map.put(a[i], 1);
            }
        }

        System.out.println(map);
        int valMax = -1;
        int timeMax = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) > timeMax) {
                timeMax = map.get(key);
                valMax = key;
            }
        }
        System.out.println(valMax);

    }

    public static void main(String[] args) {
//        s1();
        s2();
        s2_1();
//        s3();

    }


}
