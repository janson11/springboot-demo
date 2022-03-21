package com.janson.algorithm.interview;


/**
 * @Description: 回文子字符串的个数
 * 题目：给定一个字符串，请问该字符串中有多少个回文连续子字符串？例如，字符串"abc"有3个回文子字符串，分别为"a"、"b"和"c"；而字符串"aaa"有6个回文子字符串，分别为"a"、"a"、"a"、"aa"、"aa"和"aaa"。
 * 分析：前面都是从字符串的两端开始向里移动指针来判断字符串是否是一个回文，其实也可以换一个方向从字符串的中心开始向两端延伸。如果存在一个长度为m的回文子字符串，则分别再向该回文的两端延伸一个字符，
 * 并判断回文前后的字符是否相同。如果相同，就找到了一个长度为m+2的回文子字符串。例如，在字符串"abcba"中，从中间的"c"出发向两端延伸一个字符，由于"c"前后都是字符'b'，因此找到了一个长度为3的回文子字符串"bcb"。
 * 然后向两端延伸一个字符，由于"bcb"的前后都是字符'a'，因此又找到一个长度为5的回文子字符串"abcba"。
 * @Author: shanjian
 * @Date: 2022/3/3 10:32 上午
 */
public class CountSubstrings {


    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += countPalindrome(s, i, i);
            count += countPalindrome(s, i, i + 1);
        }


        return count;
    }

    private static int countPalindrome(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            start--;
            end++;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(countSubstrings(s));
    }


}
