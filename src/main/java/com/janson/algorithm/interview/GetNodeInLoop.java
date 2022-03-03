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


    /**
     * 上述代码需要求出链表的环中节点的数目。如果仔细分析，就会发现其实并没有必要求得环中节点的数目。如果链表中有环，快慢两个指针一定会在环中的某个节点相遇。慢的指针一次走一步，假设在相遇时慢的指针一共走了k步。由于快的指针一次走两步，因此在相遇时快的指针一共走了2k步。因此，到相遇时快的指针比慢的指针多走了k步。另外，两个指针相遇时快的指针比慢的指针在环中多转了若干圈。也就是说，两个指针相遇时快的指针多走的步数k一定是环中节点的数目的整数倍，此时慢的指针走过的步数k也是环中节点数的整数倍。
     * 此时可以让一个指针指向相遇的节点，该指针的位置是之前慢的指针走了k步到达的位置。接着让另一个指针指向链表的头节点，然后两个指针以相同的速度一起朝着指向下一个节点的指针移动，当后面的指针到达环的入口节点时，前面的指针比它多走了k步，而k是环中节点的数目的整数倍，相当于前面的指针在环中转了k圈后也到达环的入口节点，两个指针正好相遇。也就是说，两个指针相遇的节点正好是环的入口节点。
     * 简化之后的代码如下所示，和前面的代码相比，此处省略了得到环中节点的数目的步骤
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle1(ListNode head) {
        ListNode inLoop = getNodeInLoop(head);
        if (inLoop == null) {
            return null;
        }
        ListNode node = head;
        while (node != inLoop) {
            node = node.next;
            inLoop = inLoop.next;
        }
        return node;
    }

}
