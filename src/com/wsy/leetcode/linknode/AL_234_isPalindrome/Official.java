package com.wsy.leetcode.linknode.AL_234_isPalindrome;

import java.util.ArrayList;
import java.util.List;

public class Official {


    /**
     * 判断 某个链表 是不是 回文链表
     * <p>
     * 方法1：
     * 找到中间节点（快慢指针），将 后半段链表 反转，然后 和 前半段 一一比较；
     * 当然了，最后 还要反转换来 --》最好 不要改变 原链表的 数据结构！
     * <p>
     * 时间：O(N)
     * 空间：O(1)
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 找到 中间节点（奇数个的话，比如 5，这里就是3了，所以 second 可能短一些）
        ListNode firstEnd = endOfFirstHalf(head);
        // TODO 反转 后半部分
        ListNode secondStart = reverseList(firstEnd.next);

        ListNode p1 = head;
        ListNode p2 = secondStart;

        // 最后结果，默认是 true
        boolean result = true;
        // 数值 一一比较
        while (p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }

            // 相等，就后移
            p1 = p1.next;
            p2 = p2.next;
        }

        // todo 最后 将后半部分 反转回来！
        firstEnd.next = reverseList(secondStart);

        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);

        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }

        return dummy.next;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 注意，判空 条件！
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    /**
     * 方法2：
     * 1、将链表的值 都放入 数组
     * 2、然后，头、尾 双指针，往中间靠拢，一一比对
     * <p>
     * 时间：O(N)
     * 空间：O(N)
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 存到链表
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }

        // 双指针
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {

            // 有 不想等的 ！
            if (!list.get(start).equals(list.get(end))) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}