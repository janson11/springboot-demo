package com.janson.algorithm.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 和为k的子数组
 * <p>
 * 题目：输入一个整数数组和一个整数k，请问数组中有多少个数字之和等于k的连续子数组？例如，输入数组[1，1，1]，k的值为2，有2个连续子数组之和等于2。
 * <p>
 * <p>
 * 分析：这个题目看起来也可以利用双指针来解决。我们还是用指针P1和P2指向数组中的两个数字，两个指针之间的数字组成一个子数组。如果两个指针之间的子数组的数字之和大于或等于k就向右移动指针P1，如果子数组的数字之和小于k就向右移动指针P2。
 * 这个使用双指针的解法基于如下假设：向右移动指针P2相当于在子数组中添加一个新的数字，从而得到更大的子数组的数字之和。如果新添加的数字是正数，那么这个假设是成立的。但本题中的数组并没有说明是由正整数组成的，因此不能保证在子数组中添加新的数字就能得到和更大的子数组。同样，也不能保证删除子数组最左边的数字就能得到和更小的子数组。
 * 既然双指针的解法不适用于这个题目，就只能尝试其他的解法。下面先分析蛮力法的时间复杂度。在一个长度为n的数组中有O（n2）个子数组，如果求每个子数组的和需要O（n）的时间，那么总共需要O（n3）的时间就能求出所有子数组的和。
 * 如果稍微做一些优化，那么用O（1）的时间就能求出一个子数组的所有数字之和。在求一个长度为i的子数组的数字之和时，应该把该子数组看成在长度为i-1的子数组的基础上添加一个新的数字。如果之前已经求出了长度为i-1的子数组的数字之和，那么只要再加上新添加的数字就能得出长度为i的子数组的数字之和，只需要一次加法，因此需要O（1）的时间。优化之后总的时间复杂度是O（n2）。
 * 下面换一种思路，在从头到尾逐个扫描数组中的数字时求出前i个数字之和，并且将和保存下来。数组的前i个数字之和记为x。如果存在一个j（j＜i），数组的前j个数字之和为x-k，那么数组中从第i+1个数字开始到第j个数字结束的子数组之和为k。
 * 这个题目需要计算和为k的子数组的个数。当扫描到数组的第i个数字并求得前i个数字之和是x时，需要知道在i之前存在多少个j并且前j个数字之和等于x-k。所以，对每个i，不但要保存前i个数字之和，还要保存每个和出现的次数。分析到这里就会知道我们需要一个哈希表，哈希表的键是前i个数字之和，值为每个和出现的次数。
 * 基于计算并保存数组前i个数字之和的思路可以写出如下代码：
 * @Author: shanjian
 * @Date: 2022/2/28 4:34 下午
 */
public class SubarraySum {

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumToCouunt = new HashMap<>();
        sumToCouunt.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            count += sumToCouunt.getOrDefault((sum - k), 0);
            sumToCouunt.put(sum, sumToCouunt.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        System.out.println(subarraySum(nums, 2));
    }
}
