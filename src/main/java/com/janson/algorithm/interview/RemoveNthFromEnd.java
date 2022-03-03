package com.janson.algorithm.interview;

/**
 * @Description: 删除倒数第K个节点
 * <p>
 * 题目：如果给定一个链表，请问如何删除链表中的倒数第k个节点？假设链表中节点的总数为n，那么1≤k≤n。要求只能遍历链表一次。
 * 例如，输入图4.1（a）中的链表，删除倒数第2个节点之后的链表如图4.1（b）所示。
 * @Author: shanjian
 * @Date: 2022/3/3 4:28 下午
 */
public class RemoveNthFromEnd {


    /**
     * 采用双指针
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode front = head;
        ListNode back = dummy;

        for (int i = 0; i < n; i++) {
            front = front.next;
        }
        while (front != null) {
            front = front.next;
            back = back.next;
        }

        back.next = back.next.next;
        return dummy.next;
    }


}
