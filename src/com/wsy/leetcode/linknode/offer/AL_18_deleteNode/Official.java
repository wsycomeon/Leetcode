package com.wsy.leetcode.linknode.offer.AL_18_deleteNode;

public class Official {


    /**
     * 从 值唯一 的链表中，删除 指定值的节点
     * 方法1：
     * 哨兵
     * 然后 保存pre节点，找到 current值和val相等，直接 删除 即可
     * <p>
     * 哨兵 好处很多，一些边界条件，比如：头结点的删除、空链表、单链表等，都非常方便
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        while (pre.next != null) {
            ListNode current = pre.next;

            // 值相等，找到了，删除它
            if (current.val == val) {
                pre.next = current.next;
                break;
            }

            // 否则，后移
            pre = current;
        }

        return dummy.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}