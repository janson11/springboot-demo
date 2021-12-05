package com.janson.java8.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2021/12/5 17:05
 **/
public class StreamDemo {

    public static void main(String[] args) {
        System.out.println("使用 Java7：");
//        计算空字符
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表：" + strings);
        long count = getCountLengthUsingJava7(strings);
        System.out.println("字符串长度为3的数量为：" + count);
//        删除字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后端的列表：" + filtered);

        String mergedString = getMergedStringUsingJava7(strings, ",");
        System.out.println("合并字符串：" + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 3, 2, 7, 3, 5);
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表：" + squaresList);
        List<Integer> integerList = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        System.out.println("列表：" + integerList);
        System.out.println("列表中最大的数：" + getMax(integerList));
        System.out.println("列表中最小的数：" + getMin(integerList));
        System.out.println("所有数之和：" + getSum(integerList));
        System.out.println("平均数：" + getMax(integerList));
        System.out.println("随机数：");

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }
        System.out.println("使用Java8：");
        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符的数量为：" + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为3的数量：" + count);
        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表：" + filtered);
        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串：" + mergedString);
        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares list :" + squaresList);
        System.out.println("列表：" + integerList);
        IntSummaryStatistics stats = integerList.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表stats：" + stats.toString());
        System.out.println("列表中最大的数：" + stats.getMax());
        System.out.println("列表中最小的数：" + stats.getMin());
        System.out.println("所有数之和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());
        System.out.println("随机数：");
        random.ints().limit(10).sorted().forEach(System.out::println);

        count = strings.parallelStream().filter(string -> !string.isEmpty()).count();
        System.out.println("空字符串的数量为：" + count);

    }

    private static int getCountLengthUsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<String>();
        for (String string : strings) {
            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }


    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<Integer>();
        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());
            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }


    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List<Integer> numbers) {
        int sum = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }


}
