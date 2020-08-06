package com.wsy.leetcode.linknode.AL_141_hasCycle;

import java.util.HashSet;

public class Official {


    /**
     * 这题，也是判断 链表 是否有环！
     * todo 但是，注意！这题目，没有说 要求 入环点！
     * 所以，虽然 解决方案 仍然 是 那两种，
     * 但是 实现可以更简单一些！
     * <p>
     * 方法1：哈希表记录
     * <p>
     * 时间：O(N)
     * 空间：O(N)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        // hash表 记录 访问过的点
        HashSet<ListNode> set = new HashSet<>();

        ListNode current = head;
        while (current != null) {
            // 之前记录过，那就是 有环了！
            if (set.contains(current)) {
                return true;
            }

            // 否则，就记录下来
            set.add(current);
            current = current.next;
        }

        // 到头，都没有，就是 无环 ！
        return false;
    }

    /**
     * 方法2：快、慢双指针
     * 如果 无环，那就直接走到头了；
     * 有环的话，在环里，肯定 套圈 能追上，追上 就返回true！
     * <p>
     * 时间：O(N + K) K为 环形部分的 长度
     * 空间：O(1)
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 如果 还相遇，就 有环 ！
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}