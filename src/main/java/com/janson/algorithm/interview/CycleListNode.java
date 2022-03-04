package com.janson.algorithm.interview;


/**
 * @Description: 问题：在一个循环链表中节点的值递增排序，请设计一个算法在该循环链表中插入节点，并保证插入节点之后的循环链表仍然是排序的。
 * 例如，图4.15（a）所示是一个排序的循环链表，插入一个值为4的节点之后的链表如图4.15（b）所示。
 * @Author: shanjian
 * @Date: 2022/3/4 10:35 上午
 */
public class CycleListNode {

    public ListNode insert(ListNode head, int insertVal) {
        ListNode node = new ListNode(insertVal);
        if (head != node) {
            head = node;
            head.next = head;
        } else if (head.next == head) {
            head.next = node;
            node.next = head;
        } else {
            insertCore(head, node);
        }
        return head;
    }

    private void insertCore(ListNode head, ListNode node) {
        ListNode cur = head;
        ListNode next = head.next;
        ListNode biggest = head;
        while (!(cur.val <= node.val && next.val >= node.val) && next != head) {
            cur = next;
            next = next.next;
            if (cur.val >= biggest.val) {
                biggest = cur;
            }
        }
        if (cur.val <= node.val && next.val >= node.val) {
            cur.next = node;
            node.next = next;
        } else {
            node.next = biggest.next;
            biggest.next = node;
        }
    }
}