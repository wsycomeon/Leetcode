package com.wsy.leetcode.linknode.AL_237_deleteNode;

public class Official {


    /**
     * 删除 给定的节点！
     * 注意！这个题目，已经把 各种 边界条件 给你 删掉了！
     * 而且 不给你 头结点。直接给了你 指定节点，让删掉它！
     * 那就没办法 通过 pre 来删除；
     * 通过 值的复制、删除 来做
     * todo 将当前的节点的值 赋值为 下一个节点的值（当前不是尾节点，必然有下一个节点），然后 删除 下一个节点 ！
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}