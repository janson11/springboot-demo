package com.janson.algorithm.interview;


/**
 * @Description: 链表中的数字相加
 * 题目：给定两个表示非负整数的单向链表，请问如何实现这两个整数的相加并且把它们的和仍然用单向链表表示？
 * 链表中的每个节点表示整数十进制的一位，并且头节点对应整数的最高位数而尾节点对应整数的个位数。例如，在图4.10（a）和图4.10（b）中，两个链表分别表示整数123
 * @Author: shanjian
 * @Date: 2022/3/3 6:20 下午
 */
public class AddTwoNumbers {


    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        head1 = ListNode.reverseList(head1);
        head2 = ListNode.reverseList(head2);
        ListNode reversedHead = addReversed(head1, head2);
        return ListNode.reverseList(reversedHead);


    }

    private ListNode addReversed(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        int carry = 0;
        while (head1 != null || head2 != null) {
            int sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            carry = sum >= 10 ? 1 : 0;
            sum = sum >= 10 ? sum - 10 : sum;
            ListNode newNode = new ListNode(sum);
            sumNode.next = newNode;
            sumNode = sumNode.next;
            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
            if (carry > 0) {
                sumNode.next = new ListNode(carry);
            }
        }
        return dummy.next;
    }

}
