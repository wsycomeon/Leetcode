package com.wsy.leetcode.linknode.AL_23_mergeKLists;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Official {

    /**
     * 合并 K个有序链表
     * 1、暴力法：两两合并，合并的结果 和 下一个合并
     * 2、优先级队列
     * 3、分治算法
     */

    /**
     * 1、暴力 merge
     * 假设 单个链表的最长长度 是 n；
     * 有 k 个 链表 ！
     * <p>
     * 时间 O(k^2 * n)
     * 空间 O(1)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        // TODO: 2020/6/22 其实 对于 链表 来说，一个 node 就是一个链表（当然 前提是 这货 是 头结点！）
        ListNode mergedList = lists[0];

        for (int i = 1; i < lists.length; i++) {
            mergedList = merge(mergedList, lists[i]);
        }

        return mergedList;
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        // 哨兵
        ListNode dummy = new ListNode();
        // 3指针
        ListNode current = dummy;
        ListNode p1 = node1;
        ListNode p2 = node2;


        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }

            current = current.next;
        }

        // TODO: 2020/6/22 草！这都能忘记！
        current.next = p1 == null ? p2 : p1;
        return dummy.next;
    }

    /**
     * 2、优先级队列
     * 完全利用 优先级队列的 add即自动排序的特点。。。
     * 每次弹出的就是 所有链表的头结点中的、最小的
     * 拼接到 最终链表 即可
     * <p>
     * 时间：O(kn * logk)
     * 空间：O(k)  --> 时间换空间
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 1、创建优先级队列 --》存 各队列的 起始的 node，且会给他们排序
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // 哨兵
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        // 2、一开始 就将所有 链表的起始结点 入队（入队的时候，就会 按照val 自动 排序的）
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        // 3、只要 不为null，就一直poll
        while (!queue.isEmpty()) {
            // 保证 每次 poll 出的 是 k个队列的、起始最小的node，就加入 最终链表
            current.next = queue.poll();

            // 移动下一位
            current = current.next;
            // 看下 当前链表 还有元素没，有的话，就加入进去（这是一个名额呢）
            if (current.next != null) {
                // 还是会 自动排序的
                queue.add(current.next);
            }
        }

        return dummy.next;
    }


    /**
     * 3、分治算法 --> 这个 时间、空间 最优
     * 二分，最多 logk 次。
     * 时间：O(kn * logk)
     * 空间：O(logk)
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        // 1、终止条件
        if (left == right)
            return lists[left];

        // 2、处理当前层，求 分区点
        int mid = left + (right - left) / 2;

        // 3、递归： 左、右分治
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);

        // 4、善后：合并
//        return mergeTwoLists(l1, l2);

        // 前面的写法，感觉 容易 溢出了。。
        return merge(l1, l2);
    }

//
//    /**
//     * todo 我擦，链表合并 当然 也是可以 用递归 处理的。。
//     * 但是 这么写。。。
//     * 递归 函数 套递归、套递归。。
//     * 这尼玛 很容易 溢出啊！
//     */
//    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null) return l2;
//        if (l2 == null) return l1;
//        if (l1.val < l2.val) {
//            l1.next = mergeTwoLists(l1.next, l2);
//            return l1;
//        } else {
//            l2.next = mergeTwoLists(l1, l2.next);
//            return l2;
//        }
//    }

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