package com.janson.algorithm.interview;

/**
 * @Description: 展平多级双向链表
 * <p>
 * 问题：在一个多级双向链表中，节点除了有两个指针分别指向前后两个节点，还有一个指针指向它的子链表，并且子链表也是一个双向链表，
 * 它的节点也有指向子链表的指针。请将这样的多级双向链表展平成普通的双向链表，即所有节点都没有子链表。例如，图4.14（a）所示是一个多级双向链表，它展平之后如图4.14（b）所示。
 * @Author: shanjian
 * @Date: 2022/3/4 10:20 上午
 */
public class FlattenGetTail {

    public Node flatten(Node head) {
        return null;
    }

    private static Node flattenGetTail(Node head) {
        Node node = head;
        Node tail = null;
        while (node != null) {
            Node next = node.next;
            if (node.child != null) {
                Node child = node.child;
                Node childTail = flattenGetTail(node.child);
                node.child = null;
                node.next = child;
                child.prev = node;
                childTail.next = next;
                if (next != null) {
                    next.prev = childTail;
                }
            } else {
                tail = node;
            }
        }
        return tail;
    }

    public static class Node {
        private int val;
        private Node child;
        private Node next;
        private Node prev;

        public Node(int val) {
            this.val = val;
        }
    }
}
