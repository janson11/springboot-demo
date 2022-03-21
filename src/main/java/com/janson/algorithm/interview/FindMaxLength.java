package com.janson.algorithm.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 0和1个个数相同的字数组
 * <p>
 * 题目：输入一个只包含0和1的数组，请问如何求0和1的个数相同的最长连续子数组的长度？例如，在数组[0，1，0]中有两个子数组包含相同个数的0和1，分别是[0，1]和[1，0]，它们的长度都是2，因此输出2。
 * <p>
 * 分析：只要把这个题目稍微变换一下就能重用解决面试题10的解题思路。首先把输入数组中所有的0都替换成-1，那么题目就变成求包含相同数目
 * 的-1和1的最长子数组的长度。在一个只包含数字1和-1的数组中，如果子数组中-1和1的数目相同，那么子数组的所有数字之和就是0，因此这个题目就变成求数字之和为0的最长子数组的长度。
 * 和前面的解法类似，可以在扫描数组时累加已经扫描过的数字之和。如果数组中前i个数字之和为m，前j个数字（j>i）之和也为m，那么从第i+1个数字到第j个数字的子数组的数字之和为0，这个和为0的子数组的长度是j-i。
 * 如果扫描到数组的第j个数字并累加得到前j个数字之和m，那么就需要知道是否存在一个i（i＜j）使数组中前i个数字之和也为m。可以把数组从第1个数字开始到当前扫描的数字累加之和保存到一个哈希表中。
 * 由于我们的目标是求出数字之和为0的最长子数组的长度，因此还需要知道第1次出现累加之和为m时扫描到的数字的下标。因此，哈希表的键是从第1个数字开始累加到当前扫描到的数字之和，而值是当前扫描的数字的下标
 * @Author: shanjian
 * @Date: 2022/2/28 5:03 下午
 */
public class FindMaxLength {

    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sumToIndex.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - sumToIndex.get(sum));
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0};
        System.out.println(findMaxLength(nums));
    }

}
