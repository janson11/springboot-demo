package com.janson.algorithm.interview;

/**
 * @Description: 单词的最大长度
 * 题目：输入一个字符串数字words,请计算不包含相同字符的两个字符串words[i]和words[j]的长度乘积的最大值。如果所有
 * 字符串都包含至少一个相同字符，那么返回0。假设字符串中只包含英文小写字母。例如，输入的字符串数组words为["abcw","foo",
 * "bar","fxyz","abcdef"],数组中的字符串"bar"与"foo"没有相同的字符，它们长度的乘积为9。"abcw"与"fxyz"也没有相同的字符
 * 它们长度的乘积为16，这是该数组不包含相同字符的一对字符串的长度乘积的最大值。
 * @Author: shanjian
 * @Date: 2022/2/25 4:55 下午
 */
public class MaxProduct {


    /**
     * 上述代码分为两步。第1步，初始化每个字符串对应的哈希表。如果数组words的长度为n，平均每个字符串的长度为k，那么初始化哈希表的时间复杂度是O（nk）。
     * 第2步，根据哈希表判断每对字符串是否包含相同的字符。总共有O（n2）对字符串，判断每对字符串是否包含相同的字符需要的时间为O（1），因此这一步的时间复杂度是O（n2）。
     * 于是这种解法的总体时间复杂度是O（nk+n2）。
     *
     * @param words
     * @return
     */
    public static int maxProduct(String[] words) {
        boolean[][] flags = new boolean[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                flags[i][c - 'a'] = true;
            }
        }
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int k = 0;
                for (; k < 26; k++) {
                    if (flags[i][k] && flags[j][k]) {
                        break;
                    }
                }
                if (k == 26) {
                    int prod = words[i].length() * words[j].length();
                    result = Math.max(result, prod);
                }

            }
        }
        return result;
    }


    public static int maxProduct1(String[] words) {
        int[] flags = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                flags[i] |= 1 << (c - 'a');
            }
        }
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((flags[i] & flags[j]) == 0) {
                    int prod = words[i].length() * words[j].length();
                    result = Math.max(result, prod);
                }

            }
        }
        return result;
    }


    public static void main(String[] args) {
        String[] array = new String[]{"abcw", "foo", "bar", "fxyz", "abcdef"};
        System.out.println(maxProduct(array));
        System.out.println(maxProduct1(array));

    }
}
