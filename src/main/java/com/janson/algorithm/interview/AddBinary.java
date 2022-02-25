package com.janson.algorithm.interview;

/**
 * @Description: 二进制加法
 * 题目2：输入两个表示二进制的字符串，请计算它们的和，并以二进制字符串的形式输出。例如，输入的二进制字符串
 * 分别是"11"和"10",则输出"101"。
 * @Author: shanjian
 * @Date: 2022/2/25 2:22 下午
 */
public class AddBinary {

    /**
     * 上述代码中的加法是从字符串的右端开始，最低位保存在result的最左边，而通常数字最左边保存的是最高位。因此
     * 在返回之前要将result进行翻转。
     *
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int digitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int digitB = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = digitA + digitB + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            result.append(sum);
        }
        if (carry == 1) {
            result.append(1);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "10"));
    }


}
