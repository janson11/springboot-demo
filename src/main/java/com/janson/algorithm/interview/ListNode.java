package com.janson.algorithm.interview;

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


}
