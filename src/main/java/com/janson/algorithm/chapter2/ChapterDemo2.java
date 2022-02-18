package com.janson.algorithm.chapter2;


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
     */
    public static void s1() {
        int count = 0;
        for (int i = 0; i < (100 / 7); i++) {
            for (int j = 0; j < (100 / 3); j++) {
                for (int k = 0; k < (100 / 2); k++) {
                    if (i * 7 + j * 3 + k * 2 == 100) {
                        count += 1;
                    }
                }
            }
        }
        System.out.println(count);

    }


    public static void main(String[] args) {
        s1();

    }


}
