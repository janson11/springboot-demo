package com.janson.algorithm.interview;

/**
 * @Description: 最多删除一个字符得到回文
 * <p>
 * 题目：给定一个字符串，请判断如果最多从字符串中删除一个字符能不能得到一个回文字符串。
 * 例如，如果输入字符串"abca"，由于删除字符'b'或'c'就能得到一个回文字符串，因此输出为true。
 * 分析：和面试题18类似，本题还是从字符串的两端开始向里逐步比较两个字符是不是相同。
 * 如果相同，则继续向里移动指针比较里面的两个字符。如果不相同，按照题目的要求，在删除一个字符之后再比较其他的字符就能够形成一个回文。由于事先不知道应该删除两个不同字符中的哪一个，因此两个字符都可以进行尝试。这种思路对应的Java的参考代码如下所示
 * @Author: shanjian
 * @Date: 2022/3/2 2:15 下午
 */
public class ValidPalindrome {

    public static boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        for (; start < s.length() / 2; ++start, --end) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
        }
        return start == s.length() / 2 || isPalindrome(s, start, end - 1) || isPalindrome(s, start + 1, end);
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
            start++;
            end--;
        }
        return start >= end;
    }

    public static void main(String[] args) {
        String s = "abca";
        System.out.println(validPalindrome(s));
    }
}
