package com.janson.algorithm.interview;

/**
 * @Description: 字符串中的变位词
 * <p>
 * 题目：输入字符串s1和s2，如何判断字符串s2中是否包含字符串s1的某个变位词？如果字符串s2中包含字符串s1的某个变位词，
 * 则字符串s1至少有一个变位词是字符串s2的子字符串。假设两个字符串中只包含英文小写字母。例如，字符串s1为"ac"，字符串s2为"dgcaf"，
 * 由于字符串s2中包含字符串s1的变位词"ca"，因此输出为true。如果字符串s1为"ab"，字符串s2为"dgcaf"，则输出为false。
 * @Author: shanjian
 * @Date: 2022/3/1 6:03 下午
 */
public class CheckInclusion {

    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }

        if (areAllZero(counts)) {
            return true;
        }

        for (int i = s1.length(); i < s2.length(); ++i) {
            counts[s2.charAt(i) - 'a']--;
            counts[s2.charAt(i - s1.length()) - 'a']++;
            if (areAllZero(counts)) {
                return true;
            }
        }
        return false;
    }

    private static boolean areAllZero(int[] counts) {
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s2 = "dgcaf";
        String s1 = "ac";
        System.out.println(checkInclusion(s1, s2));

    }
}
