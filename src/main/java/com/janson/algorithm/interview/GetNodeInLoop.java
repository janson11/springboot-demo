package com.janson.algorithm.interview;


/**
 * @Description: 链表中环的入口节点
 * <p>
 * 题目：如果一个链表中包含环，那么应该如何找出环的入口节点？从链表的头节点开始顺着next指针方向进入环的第1个节点为环的入口节点。
 * <p>
 * 分析：解决这个问题的第1步是如何确定一个链表中包含环。如果一个链表中没有环，那么自然不存在环的入口节点，此时应该返回null。
 * 受到面试题21的启发，仍可以用两个指针来判断链表中是否包含环。和解决前面的问题一样，可以定义两个指针并同时从链表的头节点出发，
 * 一个指针一次走一步，另一个指针一次走两步。如果链表中不包含环，走得快的指针直到抵达链表的尾节点都不会和走得慢的指针相遇。如果链表中包含环，走得快的指针在环里绕了一圈之后将会追上走得慢的指针。因此，可以根据一快一慢两个指针是否能够相遇来判断链表中是否包含环。
 * @Author: shanjian
 * @Date: 2022/3/3 4:45 下午
 */
public class GetNodeInLoop {


    public static ListNode getNodeInLoop(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = slow.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode inLoop = getNodeInLoop(head);
        if (inLoop == null) {
            return null;
        }

        int loopCount = 1;
        for (ListNode n = inLoop; n.next != inLoop; n = n.next) {
            loopCount++;
        }
        ListNode fast = head;
        for (int i = 0; i < loopCount; i++) {
            fast = fast.next;
        }
        ListNode slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
