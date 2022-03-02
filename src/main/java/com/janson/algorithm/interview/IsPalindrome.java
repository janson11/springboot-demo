package com.janson.algorithm.interview;

/**
 * @Description: 有效的回文
 * <p>
 * 题目：给定一个字符串，请判断它是不是回文。假设只需要考虑字母和数字字符，
 * 并忽略大小写。例如，"Was it a cat I saw？"是一个回文字符串，而"race a car"不是回文字符串。
 * @Author: shanjian
 * @Date: 2022/3/2 2:02 下午
 */
public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (!Character.isLetterOrDigit(ch1)) {
                i++;
            } else if (!Character.isLetterOrDigit(ch2)) {
                j--;
            } else {
                ch1 = Character.toLowerCase(ch1);
                ch2 = Character.toLowerCase(ch2);
                if (ch1 != ch2) {
                    return false;
                }
                i++;
                j--;

            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "Was it a cat I saw";
        String s1 = "race a car";

        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome(s1));


    }
}
