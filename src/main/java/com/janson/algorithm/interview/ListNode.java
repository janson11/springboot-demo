package com.janson.algorithm.interview;

import lombok.NoArgsConstructor;

/**
 * @Description: 单向链表
 * @Author: shanjian
 * @Date: 2022/3/3 4:10 下午
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 哨兵节点是为了简化处理链表边界条件而引入的附加链表节点。哨兵节点通常位于链表的头部，它的值没有任何意义。
     * 在一个有哨兵节点的链表中，从第2个节点开始才真正保存有意义的信息。
     * <p>
     * 链表的一个基本操作是在链表的尾部添加一个节点。由于通常只有一个指向单向链表头节点的指针，
     * 因此需要遍历链表中的节点直至到达链表的尾部，然后在尾部添加一个节点。可以用如下所示的Java代码实现这个过程
     *
     * @param head
     * @param value
     * @return
     */
    public ListNode append(ListNode head, int value) {
        ListNode newNode = new ListNode(value);
        /**
         * 当输入的链表头节点为null时，输入的链表为空。此时新添加的节点成为链表中唯一的节点，也就是链表的头节点
         */
        if (head == null) {
            return newNode;
        }
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
        return head;
    }

    /**
     * 链表的尾部添加一个节点。首先创建一个哨兵节点，并把该节点当作链表的头节点，
     * 然后把原始的链表添加在哨兵节点的后面。当完成添加操作之后，再返回链表真正的头节点，也就是哨兵节点的下一个节点
     *
     * @param head
     * @param value
     * @return
     */
    public ListNode sentinelAppend(ListNode head, int value) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode newNode = new ListNode(value);
        ListNode node = dummy;
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
        return dummy.next;
    }


    /**
     * 链表删除操作
     *
     * @param head
     * @param value
     * @return
     */
    public ListNode delete(ListNode head, int value) {
        if (head == null) {
            return head;
        }
        // 输入的链表为空，被删除的节点是原始链表的头节点
        if (head.val == value) {
            return head.next;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.next.val == value) {
                node.next = node.next.next;
                break;
            }
            node = node.next;
        }
        return head;
    }


    /**
     * 用哨兵节点简化链表删除操作
     *
     * @param head
     * @param value
     * @return
     */
    public ListNode sentinelDelete(ListNode head, int value) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == value) {
                node.next = node.next.next;
                break;
            }
            node = node.next;
        }
        return dummy.next;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        //变量cur指向当前遍历到的节点，变量prev指向当前节点的前一个节点，而变量next指向下一个节点。
        // 每遍历一个节点之后，都让变量prev指向该节点。在遍历到尾节点之后，变量prev最后一次被更新，因此，变量prev最终指向原始链表的尾节点，也就是反转链表的头节点。
        //显然，上述代码的时间复杂度是O（n），空间复杂度是O（1）。
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /**
     * 重排链表
     * <p>
     * 问题：给定一个链表，链表中节点的顺序是L0→L1→L2→…→Ln-1→Ln，请问如何重排链表使节点的顺序变成L0→Ln→L1→Ln-1→L2→Ln-2→…？
     * 例如，输入图4.12（a）中的链表，重排之后的链表如图4.12（b）所示。
     * ￼
     */
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        ListNode temp = slow.next;
        slow.next = null;
        link(head, reverseList(temp), dummy);

    }

    private void link(ListNode node1, ListNode node2, ListNode head) {
        ListNode prev = head;
        while (node1 != null && node2 != null) {
            ListNode temp = node1.next;
            prev.next = node1;
            node1.next = node2;
            prev = node2;
            node1 = temp;
            node2 = node2.next;
        }
        if (node1 != null) {
            prev.next = node1;
        }
    }

}
