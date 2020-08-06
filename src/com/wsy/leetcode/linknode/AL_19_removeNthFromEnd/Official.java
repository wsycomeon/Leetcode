package com.wsy.leetcode.linknode.AL_19_removeNthFromEnd;

public class Official {


    /**
     * 方法1:
     * （删除 倒数 第 n 个节点，且已经 保证是 有效的了，那就不做 边界检查了）
     * <p>
     * 快、慢 双指针 找到 待删节点：
     * 有一个 快指针 从 head 先走 n 步，然后 再有一个 慢指针 从 head 开始 一起往后走；
     * <p>
     * 这样的话， 快指针 一直领先 n 步 ！
     * 最后 快指针 先走到头的时候，慢指针 正好 还差 n个节点 走到头！
     * 即 慢指针 正好 走到了 待删节点的 pre 位置 --> slow，
     * 删除 紧跟着的节点 即可 ！
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head;
        // 快指针 先走 n 步，
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        // TODO: 2020/7/22  注意 边界条件，如果 走到头了。。。说明 待删的是 第一个节点，直接 返回 next即可
        if (fast == null) {
            return head.next;
        }

        // 然后 有一个 慢指针 一起走，快指针走到头的时候，慢指针正好走到合适位置
        ListNode slow = head;
        // 快指针 走的快，所以 只 fast.next 判空 即可
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除 待删节点 : 原 slow.next
        slow.next = slow.next.next;

        return head;
    }

    /**
     * 方法2：正向计算法
     * 要删除 倒数 第n个 元素，即 删除 正数 第 L - n + 1 个元素，即 找到 第 L - n 这个节点，删除它的next 即可；
     * 但是，需要 遍历一遍 计算出 链表长度 L，然后在删除
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 1、计算 length
        int length = 0;

        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }


        // 2、计算 pre 节点 所在的 count
        int count = length - n;

        // todo 这里，为了 计数方便，也为了 特殊情况（就一个元素，还删除1个，直接 删空了！）所以 使用一个 占位哨兵 ！
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 从哨兵之后 开始计数
        ListNode pre = dummy;

        while (count > 0) {
            pre = pre.next;
            count--;
        }

        // 删除 目标节点
        pre.next = pre.next.next;

        // TODO: 2020/7/22 返回 哨兵的 next ！
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