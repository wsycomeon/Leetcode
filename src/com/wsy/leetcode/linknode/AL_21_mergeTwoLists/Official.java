package com.wsy.leetcode.linknode.AL_21_mergeTwoLists;

public class Official {


    /**
     * 一、递归合并
     * 1、比较 两个链表的 head，较小的作为 最终的 head
     * 2、然后 当前链表的头 是  当前head.next
     * 3、让 新链表 和 另一个原链表 去同样操作，假设拼接完成
     * 4、只要将 最终的head + 第3步的 最终链表 拼接起来 就好
     * <p>
     * ====
     * 时间复杂度是 O(m+n)
     * 空间复杂度是 O(m+n) --》主要 取决于 递归的深度
     */
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        // 1、终止条件
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        // 2、处理当前层
        if (head1.val <= head2.val) {
            // 3、递归 新链表 + 另一个链表 合并
            // 4、善后，和 上一步的合并结果，拼接，返回 即可
            head1.next = mergeTwoLists(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeTwoLists(head1, head2.next);
            return head2;
        }

    }


    /**
     * 二、不是上面的 递归方式了，虽然仍是遍历，但是去除 迭代方式（可能会有 栈溢出问题），
     * 改为 while 循环 迭代
     * <p>
     * 1、借用 哨兵思想，一开始有一个占位哨兵 作为 最终链表的 head，最终 返回 哨兵.next 就好了
     * 2、然后，两个链表 都从前往后遍历，每次 两两比较，
     * 小的，链接到最终链表上去，然后 其后移一位；
     * 3、直到一个链表空了，就将 另一个直接拼接上 就好了
     */
    public ListNode mergeTwoLists2(ListNode head1, ListNode head2) {
        // TODO: 2020/6/22 注意，本身 需要做 边界判断的，但是 本身后面算法，不会触发问题，做了兼容，所以 这里 不做处理
        ListNode dummy = new ListNode();
        dummy.val = -1;

        // 新链表 等待拼接 下一个节点的指针
        ListNode prev = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                prev.next = head1;
                // 链表自身的指针 后移
                head1 = head1.next;
            } else {
                prev.next = head2;
                head2 = head2.next;
            }

            // 新链表 指针也要后移
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多 只有一个 还未被合并完，我们直接 将链表末尾 指向 未合并完的链表即可
        prev.next = head1 == null ? head2 : head1;

        // 返回 哨兵的下一位节点，就是 真正的头结点！
        return dummy.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
