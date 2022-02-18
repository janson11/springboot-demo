# 算法



#### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

难度简单13211收藏分享切换为英文接收动态反馈

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。



示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]



提示：

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案
进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？



```
/**
 *
 * 思路
 * 标签：哈希映射
 * 这道题本身如果通过暴力遍历的话也是很容易解决的，时间复杂度在 O(n2)O(n2)
 * 由于哈希查找的时间复杂度为 O(1)O(1)，所以可以利用哈希容器 map 降低时间复杂度
 * 遍历数组 nums，i 为当前下标，每个值都判断map中是否存在 target-nums[i] 的 key 值
 * 如果存在则找到了两个值，如果不存在则将当前的 (nums[i],i) 存入 map 中，继续遍历直到找到为止
 * 如果最终都没有结果则抛出异常
 * 时间复杂度：$$
 *
 *
 * @param nums
 * @param target
 * @return
 */
public int[] twoSum2(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i< nums.length; i++) {
        if(map.containsKey(target - nums[i])) {
            return new int[] {map.get(target-nums[i]),i};
        }
        map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
}
```





#### [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

难度中等7377收藏分享切换为英文接收动态反馈

给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg)

输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
示例 2：

输入：l1 = [0], l2 = [0]
输出：[0]
示例 3：

输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]



**提示：**

- 每个链表中的节点数在范围 `[1, 100]` 内
- `0 <= Node.val <= 9`
- 题目数据保证列表表示的数字不含前导零



思路与算法

由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。

我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2，进位值为 carry，则它们的和为 n1+n2+carry；其中，答案链表处相应位置的数字为(n1+n2+carry)mod10，而新的进位值为 

[(n1+n2+carry)/10]

如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 00 。

此外，如果链表遍历结束后，有carry>0，还需要在答案链表的后面附加一个节点，节点的值为carry。



```
/**
 * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
 * <p>
 * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2n1,n2，进位值为 \textit{carry}carry，则它们的和为 n1+n2+\textit{carry}n1+n2+carry；其中，答案链表处相应位置的数字为 (n1+n2+\textit{carry}) \bmod 10(n1+n2+carry)mod10，而新的进位值为 \lfloor\frac{n1+n2+\textit{carry}}{10}\rfloor⌊
 * 10
 * n1+n2+carry
 * 
 * ⌋。
 * <p>
 * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 00 。
 * <p>
 * 此外，如果链表遍历结束后，有 \textit{carry} > 0carry>0，还需要在答案链表的后面附加一个节点，节点的值为 \textit{carry}carry。
 *
 * @param l1
 * @param l2
 * @return
 */
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null;
    ListNode tail = null;
    int carry = 0;
    while (l1 != null || l2 != null) {
        int n1 = l1 != null ? l1.val : 0;
        int n2 = l2 != null ? l2.val : 0;
        int sum = n1 + n2 + carry;
        if (head == null) {
            head = tail = new ListNode(sum % 10);
        } else {
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
        }
        carry = sum / 10;
        if (l1 != null) {
            l1 = l1.next;
        }
        if (l2 != null) {
            l2 = l2.next;
        }
    }
    if (carry > 0) {
        tail.next = new ListNode(carry);
    }
    return head;
```