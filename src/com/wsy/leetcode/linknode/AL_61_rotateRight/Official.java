package com.wsy.leetcode.linknode.AL_61_rotateRight;

public class Official {

    /**
     * 原链表 每个非null节点 向右移动 k个位置
     * 方法1：
     * 其实就是从 倒数 第 k % length 个节点开始的后半段链表  直接移动到 原链表 最前面
     * <p>
     * 时间：O(N)
     * 空间：O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 0或者1个节点的链表，没必要操作，直接返回
        if (head == null || head.next == null) {
            return head;
        }


        // 1、求原链表长度
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 2、求是倒数 第几个位置
        int count = k % length;

        // TODO: 2020/7/23 注意，特殊情况，如果 count 恰好 = 0，就是 不需要移动，直接返回 即可！
        if (count == 0) {
            return head;
        }


        // 3、双指针 走到目标位置，都从head 开始走 就好了

        // 快指针先走，拉开 距离
        ListNode fast = head;
        while (count > 0) {
            fast = fast.next;
            count--;
        }

        // 然后，一起走，直到最后 , slow 就是 pre节点
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 此时 slow 就是 后半段链表的 pre节点；而 fast 就是后半段链表的 尾节点
        ListNode newHead = slow.next;
        slow.next = null;
        // 拼接 前半段
        fast.next = head;

        return newHead;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}