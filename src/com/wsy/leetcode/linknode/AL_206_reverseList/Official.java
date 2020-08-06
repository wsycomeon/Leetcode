package com.wsy.leetcode.linknode.AL_206_reverseList;

public class Official {

    /**
     * 1、反转链表
     * 递归做法：
     */
    public ListNode reverseList1(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode current, ListNode prev) {
        // 1、终止条件
        if (current == null) {
            return prev;
        }

        // 2、处理当前层，翻转当前节点
        ListNode next = current.next;
        current.next = prev;

        // 3、递归
        return reverse(next, current);
    }


    /**
     * 2、迭代做法
     * 哨兵
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;

        // 下一结点
        ListNode next = null;

        while (head.next != null) {
            // 保存next指针先
            next = head.next;
            // 交换指针
            head.next = prev;
            prev = head;
            head = next;
        }

        // todo 跳出之后，head是最后一个结点，和之前的还没建立关联呢。。
        head.next = prev;

        return head;
    }

    /**
     * 3、擦。其实 迭代算法。。。我上面那样写 蛋疼了点
     * 下面的更好，不容易 漏掉最后的的结果
     */
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;

        // TODO: 2020/6/22 当前结点！
        ListNode current = head;

        ListNode next = null;

        while (current != null) {
            // 保存next指针先
            next = current.next;
            // 交换指针
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    /**
     * 反转 链表
     * 这里，不需要知道 这个链表 有多长，前面 已经处理好了（通过 for循环 k次，节点 不为 null）
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);

        while (head != null) {
            // 把新结点 从原链表删除，插入dummy的后面
            ListNode next = head.next;

            head.next = dummy.next;
            dummy.next = head;

            head = next;
        }

        return dummy.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
