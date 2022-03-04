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
        // nextHead指向链表中除去k个节点之后的头节点
        // 初始指向节点head
        ListNode nextHead = head;
        // 链表中剩余节点个数
        int remainNum = 0;

        while (remainNum < k) {
            // 根据题意，如果链表剩余节点个数不足k个
            // 则不需要反转，因此直接返回head
            if (nextHead == null) {
                return head;
            }
            remainNum++;
            nextHead = nextHead.next;
        }

        // 递归反转链表中除去前k个节点的后续节点
        ListNode subList = reverseKGroup(nextHead, k);
        // 反转链表中前k个节点的后续节点
        ListNode newHead = reverseTopN(head, k);
        // 前k个节点反转后，head指向的节点是反转后的链表中的最后一个节点
        // 因此head指向的节点的后继指针指向subList
        head.next=subList;
        return newHead;


    }

    private static ListNode reverseTopN(ListNode head, int n) {
        ListNode prev = null;
        // 当前考察的节点
        ListNode cur = head;
        // num小于n,则表示当前节点需要反转
        for (int num = 0; num < n; num++) {
            // 当前节点的下一个节点
            ListNode next = cur.next;
            // 当前节点的后续指针指向prev
            cur.next = prev;
            // prev指向当前节点
            // 表示其是next节点反转后的前一个节点
            prev = cur;
            // cur指向下一个节点
            // 表示下一个节点的待反转节点
            cur = next;
        }
        return prev;
    }


}
