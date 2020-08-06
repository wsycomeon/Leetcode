package com.wsy.leetcode.linknode.AL_148_sortList;

public class Official {


    /**
     * 1、归并算法，递归做法
     * <p>
     * 时间复杂度 是 O(nlogn)
     * 链表在合并时 不像数组 需要 O(n)的空间，只要改变指针位置 就行，所以是 O(1)
     * 但是因为递归，栈的 空间复杂度 是 O(logn)
     * 不符合 O(1) 的要求！
     * <p>
     * --》所以 还是要 二分 的思路，但是 不能用 递归 来做。
     */
    public ListNode sortList(ListNode head) {
        // 1、边界条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、处理当前层
        // 通过 快、慢指针（1倍速 2倍速），找到中间结点
        ListNode slow = head;

        // todo 注意！快指针 一开始 就要是 next 比 slow 快一步，否则 stackOverFlow ！
        ListNode fast = head.next;

        // 直到 fast 先到头了，找到 中间结点 slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 通过 slow 切分链表
        ListNode rightHead = slow.next;
        // 断开
        slow.next = null;

        // 3、递归 对两边 分别排序

        // 获取 排序后 两边 新的头结点 --》不要 直接修改 旧的指针，以防万一。。
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 4、善后 ！
        // 将 排序的后的链表 进行合并成 新链表，最终 返回结果
        ListNode pre = new ListNode(-1);

        // 新链表的 指针
        ListNode newNode = pre;

        while (left != null && right != null) {

            if (left.val <= right.val) {
                newNode.next = left;
                left = left.next;
            } else {
                newNode.next = right;
                right = right.next;
            }

            newNode = newNode.next;
        }

        // 退出循环后，拼接 非空的另一半
        newNode.next = left == null ? right : left;

        return pre.next;
    }

    /**
     * 2、归并算法的 迭代实现
     * 本身 归并 就是拆分成 1 2 4 8 这种大小的 元素区间（链表），然后 合并、合并、合并
     * 直到 没得合并了；
     * 可以理解成：
     * 区间从最小开始 增大到 超越 链表 整个长度
     * <p>
     * 最开始 拆成 1 大小的区间，两两合并 一轮；合并完的 要和prev 链接起来
     * 然后 增加到 2大小的区间，两两合并一轮，一样操作
     * 直到 拆分的大区间的长度 比 链表长度 还大，结束
     * 返回 当前链表 即可
     */

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1、获取链表长度
        int len = getListLength(head);

        // 2、哨兵节点，也有叫傀儡节点（处理 链表问题的一般技巧）
        ListNode pre = new ListNode(-1);
        // 链接起来
        pre.next = head;

        // 3、区间大小 按照 从小到大 ，用来 依次拆分、排序，链接起来，直到 区间长度 达到 整个长度（循环 logn 次）
        // todo ： <<= 1，即 x= 2
        for (int rangeSize = 1; rangeSize < len; rangeSize *= 2) {

            System.out.println("小区间大小 = " + rangeSize);

            // 每次都从 链表的头 开始
            ListNode prev = pre;
            ListNode nextStart = pre.next;

            // 开始 以 rangeSize 长度 从前往后，依次 拆分，排序、拼接（循环 n 次）
            while (nextStart != null) {
                // 计算 俩 待排序的、merge的小区间
                ListNode left = nextStart;
                // 前面的可能一个 range都填不满，所以 right 可能为null
                ListNode right = split(left, rangeSize);

                // 下一轮 拆分、排序拼接的点 --> 需要 先计算 出来。
                nextStart = split(right, rangeSize);

                // todo 合并两区间，拼接到 prev 上 !!! --> 好吧，prev 在最开始 就是 pre节点，所以一开始，pre 就链接上了 正确的 初始结点
                prev.next = mergeTwoLists(left, right);

                // todo 注意！！！这是 while循环，拼接之后，要将 prev 指针 一直移动到 当前区间的最后，好拼接 下一处的两个小区间的合并结果
                while (prev.next != null) {
                    prev = prev.next;
                }
            }

            // 前面 按照 rangeSize ，将 原链表 局部 排序后，再 x2 进行 下一轮的 局部排序

        }

        return pre.next;
    }

    // 根据步长 拆分链表（可能剩余的 不够一个步长，也没关系，下面做判空了）
    private ListNode split(ListNode head, int step) {
        if (head == null)
            return null;

        // 自身 就算一个了！
        int count = 1;

        while (head.next != null && count < step) {
            head = head.next;
            count++;
        }

        // 当前的head 属于 本区间，下一区间的起点 是 next
        ListNode right = head.next;
        // 断开链接
        head.next = null;

        return right;
    }

    // 获取链表的长度
    private int getListLength(ListNode head) {
        int length = 0;

        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }

    // 合并 两个有序链表
    private ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        ListNode pre = new ListNode(-1);
        ListNode curr = pre;

        while (node1 != null && node2 != null) {

            if (node1.val < node2.val) {
                curr.next = node1;
                node1 = node1.next;
            } else {
                curr.next = node2;
                node2 = node2.next;
            }

            // 后移
            curr = curr.next;
        }

        curr.next = node1 != null ? node1 : node2;

        return pre.next;
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