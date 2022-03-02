package com.janson.algorithm.interview;

/**
 * @Description: 不含重复字符的最长子字符串
 * 题目：输入一个字符串，求该字符串中不含重复字符的最长子字符串的长度。
 * 例如，输入字符串"babcca"，其最长的不含重复字符的子字符串是"abc"，长度为3。
 * @Author: shanjian
 * @Date: 2022/3/2 10:51 上午
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] counts = new int[256];
        int i = 0;
        int j = -1;
        int longest = -1;
        for (; i < s.length(); i++) {
            counts[s.charAt(i)]++;
            while (hasGreaterThan1(counts)) {
                j++;
                counts[s.charAt(j)]--;
            }
            longest = Math.max(i - j, longest);
        }

        return longest;
    }

    private static boolean hasGreaterThan1(int[] counts) {
        for (int count : counts) {
            if (count > 1) {
                return true;
            }
        }
        return false;
    }


    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] counts = new int[256];
        int i = 0;
        int j = -1;
        int longest = 1;
        int countDup = 0;
        for (; i < s.length(); i++) {
            counts[s.charAt(i)]++;
            if (counts[s.charAt(i)] == 2) {
                countDup++;
            }
            while (countDup > 0) {
                j++;
                counts[s.charAt(j)]--;
                if (counts[s.charAt(j)] == 1) {
                    countDup--;
                }
            }
            longest = Math.max(i - j, longest);
        }
        return longest;

    }


    public static void main(String[] args) {
        String s = "babcca";
//        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring1(s));

        System.out.println(s.charAt(0));
        System.out.println(s.charAt(1));
    }


}
