package com.wsy.leetcode.linknode.AL_24_swapPairs;

public class Official {


    /**
     * 方法1：反转 n个 节点的 特例 --》 n = 2
     * 其实 就是 反转 相邻的 两个节点罢了
     * <p>
     * 时间复杂度：O(N)，其中 N 指的是链表的节点数量。
     * 空间复杂度：O(1)
     */
    public ListNode swapPairs(ListNode head) {

        // 哨兵占位
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 开始 反转
        ListNode pre = dummy;

        // 两两反转
        while (pre.next != null && pre.next.next != null) {
            // 取出 节点
            ListNode first = pre.next;
            ListNode second = first.next;
            ListNode next = second.next;

            // 交换
            first.next = next;
            second.next = first;
            pre.next = second;

            // 下一组
            pre = first;
        }


        return dummy.next;
    }

    /**
     * 方法2：
     * 递归做法
     * 时间复杂度：O(N)，其中 N 指的是链表的节点数量。
     * 空间复杂度：O(N)，递归过程使用的堆栈空间。
     */
    public ListNode swapPairs2(ListNode head) {
        // 1、终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、处理当前层
        ListNode first = head;
        ListNode second = first.next;

        // 3、递归，每次返回的是 后面两个节点的头节点
        first.next = swapPairs2(second.next);
        second.next = first;

        // 4、善后，返回 当前 头节点
        return second;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
