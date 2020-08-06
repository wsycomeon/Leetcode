package com.wsy.leetcode.linknode.AL_2_addTwoNumbers;

public class Official {


    /**
     * 链表 各位数字 相加，要注意：
     * 1、链表长度 可能不同，端的参与计算 默认补 0
     * 2、有一个 链表 可能为 空链表，都补0 就好
     * 3、最后链表 相加的结果 可能 又进一位，所以 最后 多加一个 1 结点
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 哨兵，新链表的起始点
        ListNode dummy = new ListNode(-1);

        ListNode node1 = l1, node2 = l2, newNode = dummy;

        // 上一次携带的 进位数字
        int carry = 0;

        // 只要有一个链表 没有计算完，就得 继续计算
        while (node1 != null || node2 != null) {
            // 计算完的链表，默认 补0
            int x = (node1 != null) ? node1.val : 0;
            int y = (node2 != null) ? node2.val : 0;

            // 计算和
            int sum = carry + x + y;
            carry = sum / 10;

            // 创建新结点
            newNode.next = new ListNode(sum % 10);

            // 3链表的 指针 都要后移
            newNode = newNode.next;

            if (node1 != null)
                node1 = node1.next;
            if (node2 != null)
                node2 = node2.next;
        }

        // 前面 跳出循环，意味着 都遍历都头了，计算完毕了！

        // todo 注意！最后仍有可能进位，加一个
        if (carry > 0) {
            newNode.next = new ListNode(carry);
        }

        // 返回 有效的、头结点！
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
