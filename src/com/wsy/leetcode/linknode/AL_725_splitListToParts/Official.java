package com.wsy.leetcode.linknode.AL_725_splitListToParts;

public class Official {


    /**
     * 方法1：
     * 将原链表 分成 k个链表 数组，
     * 且前面的链表长度 >= 后面的；
     * 1、计算出 原链表长度
     * 2、再计算出 最短的链表的长度；
     * 其他的 链表的长度 最多 +1；一共有 mod 个 额外 +1 的
     * <p>
     * 时间复杂度：O(N + k)  --> N 指的是 链表的结点数，若 k 很大，则还需要添加 许多 空列表 ???
     * 空间复杂度：O(k)，存放答案所需的空间。
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        // 1、计算 总长度
        int length = 0;
        ListNode current = root;

        while (current != null) {
            length++;
            current = current.next;
        }

        // 2、计算 每个链表的长度，和 最多几个 需要 +1的
        int perLength = length / k;
        int mod = length % k;

        // 最终的链表 数组
        ListNode[] result = new ListNode[k];

        current = root;

        // todo 开始 填充数组，注意 current 提前达到 末尾的情况
        for (int i = 0; i < k && current != null; i++) {

            // 先填充 当前链表的 第一个元素
            result[i] = current;

            // 计算 当前的链表的长度
            int currentLength = mod > 0 ? perLength + 1 : perLength;
            mod--;

            // 再填充 currentLength - 1 个 元素，到那时候 截取
            for (int j = 0; j < currentLength - 1; j++) {
                current = current.next;
            }

            // 断开 current 后面的链表
            ListNode next = current.next;
            current.next = null;
            current = next;
        }

        return result;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}