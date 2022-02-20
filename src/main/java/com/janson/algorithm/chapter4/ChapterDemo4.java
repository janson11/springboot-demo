package com.janson.algorithm.chapter4;

/**
 * @Description: 最后我们留一道课后练习题。给定一个含有 n 个元素的链表，现在要求每 k 个节点一组进行翻转，打印翻转后的链表结果。其中，k 是一个正整数，且可被 n 整除。
 * <p>
 * 例如，链表为 1 -> 2 -> 3 -> 4 -> 5 -> 6，k = 3，则打印 321654。我们给出一些提示，这个问题需要使用到链表翻转的算法。
 * @Author: Janson
 * @Date: 2022/2/20 10:33
 **/
public class ChapterDemo4 {

    class ListNode {
        private int val;
        private ListNode next = null;
    }

    public static void main(String[] args) {

    }


    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode end = head;
        for (int i = 0; i < k; i++) {//找到翻转部分尾节点的下一个节点
            if (end == null) {
                return head;
            }
            end = end.next;
        }
        ListNode pre = null, cur = head;
        while (cur != end) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        head.next = reverseKGroup(end, k);//尾节点指向下一个翻转的头节点
        return pre;
    }


}
