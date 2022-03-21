package com.janson.algorithm.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 包含所有字符的最短字符串
 * <p>
 * 题目：输入两个字符串s和t，请找出字符串s中包含字符串t的所有字符的最短子字符串。
 * 例如，输入的字符串s为"ADDBANCAD"，字符串t为"ABC"，则字符串s中包含字符'A'、'B'和'C'的最短子字符串是"BANC"。
 * 如果不存在符合条件的子字符串，则返回空字符串""。如果存在多个符合条件的子字符串，则返回任意一个。
 * @Author: shanjian
 * @Date: 2022/3/2 11:30 上午
 */
public class MinWindow {


    public static String minWindow(String s, String t) {
        Map<Character, Integer> charToCount = new HashMap<>();
        for (char ch : t.toCharArray()) {
            charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
        }
        int count = charToCount.size();
        int start = 0;
        int end = 0;
        int minStart = 0;
        int minEnd = 0;
        int minLength = Integer.MAX_VALUE;
        while (end < s.length() || (count == 0 && end == s.length())) {
            if (count > 0) {
                char endCh = s.charAt(end);
                if (charToCount.containsKey(endCh)) {
                    charToCount.put(endCh, charToCount.get(endCh) - 1);
                    if (charToCount.get(endCh) == 0) {
                        count--;
                    }
                }
                end++;
            } else {
                if (end - start < minLength) {
                    minLength = end - start;
                    minStart = start;
                    minEnd = end;
                }
                char startCh = s.charAt(start);
                if (charToCount.containsKey(startCh)) {
                    charToCount.put(startCh, charToCount.get(startCh) + 1);
                    if (charToCount.get(startCh) == 1) {
                        count++;
                    }
                }
                start++;
            }
        }

        return minLength < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
    }

    public static void main(String[] args) {
        String s = "ADDBANCAD";
        String t = "ABC";
        System.out.println(minWindow(s, t));

    }

}
