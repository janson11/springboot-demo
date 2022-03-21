package com.janson.algorithm.interview;


/**
 * @Description: 两个链表的第1个重合节点
 * 题目：输入两个单向链表，请问如何找出它们的第1个重合节点。例如，图4.5中的两个链表的第1个重合节点的值是4。
 * @Author: shanjian
 * @Date: 2022/3/3 5:20 下午
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int count1 = countList(headA);
        int count2 = countList(headB);
        int delta = Math.abs(count1 - count2);
        ListNode longer = count1 > count2 ? headA : headB;
        ListNode shorter = count1 > count2 ? headB : headA;
        ListNode node1 = longer;
        for (int i = 0; i < delta; i++) {
            node1 = node1.next;
        }
        ListNode node2 = shorter;
        while (node1 != node2) {
            node2 = node2.next;
            node1 = node1.next;
        }
        return node1;
    }

    private int countList(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
