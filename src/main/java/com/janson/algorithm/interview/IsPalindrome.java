package com.janson.algorithm.interview;

/**
 * @Description: 有效的回文
 * <p>
 * 题目：给定一个字符串，请判断它是不是回文。假设只需要考虑字母和数字字符，
 * 并忽略大小写。例如，"Was it a cat I saw？"是一个回文字符串，而"race a car"不是回文字符串。
 * @Author: shanjian
 * @Date: 2022/3/2 2:02 下午
 */
public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (!Character.isLetterOrDigit(ch1)) {
                i++;
            } else if (!Character.isLetterOrDigit(ch2)) {
                j--;
            } else {
                ch1 = Character.toLowerCase(ch1);
                ch2 = Character.toLowerCase(ch2);
                if (ch1 != ch2) {
                    return false;
                }
                i++;
                j--;

            }
        }
        return true;
    }


    /**
     * 问题：如何判断一个链表是不是回文？要求解法的时间复杂度是O（n），并且不得使用超过O（1）的辅助空间。
     * 如果一个链表是回文，那么链表的节点序列从前往后看和从后往前看是相同的。例如，图4.13中的链表的节点序列从前往后看和从后往前看都是1、2、3、3、2、1，因此这是一个回文链表。
     * <p>
     * <p>
     * 分析：如果不考虑辅助空间的限制，直观的解法是创建一个新的链表，链表中节点的顺序和输入链表的节点顺序正好相反。如果新的链表和输入链表是相同的，那么输入链表就是一个回文链表。只是这种解法需要创建一个和输入链表长度相等的链表，因此需要O（n）的辅助空间。
     * 仔细分析回文链表的特点以便找出更好的解法。回文链表的一个特性是对称性，也就是说，如果把链表分为前后两半，那么前半段链表反转之后与后半段链表是相同的。在如图4.13所示的包含6个节点的链表中，前半段链表的3个节点反转之后分别是3、2、1，后半段链表的3个节点也分别是3、2、1，因此它是一个回文链表。
     * <p>
     * 1——>2——>3——>3——>2——>1
     *
     * @param head
     * @return
     */
    public boolean listNodeIsPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode secondHalf = slow.next;
        if (fast.next != null) {
            secondHalf = slow.next.next;
        }
        slow.next = null;


        return equals(secondHalf, ListNode.reverseList(head));
    }

    private boolean equals(ListNode head1, ListNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    public static void main(String[] args) {
        String s = "Was it a cat I saw";
        String s1 = "race a car";

        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome(s1));


    }
}
