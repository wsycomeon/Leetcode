package com.wsy.leetcode.linknode.AL_83_deleteDuplicates;

public class Official {

    /**
     * 方法1：递归
     * 当前head，直接 拼接 后面处理好的 去完重复的链表
     * 最后，返回 不带重复的head
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 1、终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、处理当前层
        // 3、递归
        head.next = deleteDuplicates(head.next);

        // 4、善后
        // TODO: 2020/7/22 因为 next的数值 还是 可能和 当前的head的数值 相等，所以 再次 去重！
        return head.val == head.next.val ? head.next : head;
    }

    /**
     * 方法2：
     * 官方做法，直接从 当前链表 删除 重复节点 即可。。
     * em，我之前还想 哨兵占位，拼接不重复的节点，是一个意思，不过 明显 没必要 这么麻烦的。。。官方 这样就可以了！
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {

            // 1、 若二者 数值相等，需要 从 原链表中 删除 next 节点，指针 不能后移
            if (current.val == current.next.val) {

                current.next = current.next.next;

            } else {

                // 2、否则，指针后移 即可
                current = current.next;
            }
        }

        // 返回 链表头 即可
        return head;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}