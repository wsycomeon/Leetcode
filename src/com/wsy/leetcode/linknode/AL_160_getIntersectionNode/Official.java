package com.wsy.leetcode.linknode.AL_160_getIntersectionNode;

import java.util.HashSet;
import java.util.Set;

public class Official {


    /**
     * 判断 两个链表 有没有相交
     * 或者说，返回 第一个相交结点
     * <p>
     * 方法1：哈希算法
     * 一边走完，放入hashSet，另一边 再走，每次 拿元素去看，
     * 是否在 链表1中，
     * 时间复杂度是 O(m + n)，
     * 而 空间复杂度 是 O(m) 或者 O(n)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 1、过一遍 A， 放进set
        Set<ListNode> set = new HashSet<>();
        ListNode node1 = headA;
        while (node1 != null) {
            set.add(node1);
            node1 = node1.next;
        }

        // 2、过一遍B，有的话，返回
        ListNode node2 = headB;
        while (node2 != null) {
            if (set.contains(node2)) {
                return node2;
            }

            node2 = node2.next;
        }

        // 如果前面没有，那就是没有相交
        return null;
    }


    /**
     * 方法2：
     * A、B 一般是 会有一长、一短（等长 也没关系，相交的话，一轮就搞定；不想交的话，正好 同时到末尾，也可以结束了）
     * 俩指针 p1 、p2 一开始 分别指向 A、B，同步往后走
     * 如果 某个走到头了，就换 对方的头 再来一遍
     * todo 此时，因为 俩指针 都是 走了 一样的长度 ---》目标 就是这个，让他们走一样的长度
     * 如果 二者 不想交，最终走完，会同时到达末尾，下一个结点都是null，跳出循环，但是 不会 相交（相交是说 走到的结点 是同一个）；
     * 如果 二者相交，相交点后面有相同的长度 ，又因为 总长度一样（A+B 和 B+A）
     * 则，在相交点 前面的长度 也是一样的，且二者速度相同，
     * 则 必然 会在 初次相交点 相遇！
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;

        // todo 这里 给两个指针 创造 相同的路程，保证 二者 最终 会同时 到达末尾，即 指针 会同时指向 null，一定会 退出循环，这是保底的！
        // 若在这之前，就有 指针 指向 同一个结点，那就是 有相交了，即 初次相交的结点！
        while (p1 != p2) {
            // 一端 走完了，等于 null，换另一方走
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        // 最终返回的 要么是 null，要么是 二者的相交结点！
        return p1;
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