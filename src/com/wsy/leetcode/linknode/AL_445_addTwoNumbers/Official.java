package com.wsy.leetcode.linknode.AL_445_addTwoNumbers;

import java.util.Stack;

public class Official {


    /**
     * 方法1：
     * todo 不要在想 反转链表后 再依次相加的 愚蠢办法了！根本不需要
     * 直接 用 两个 stack，我们 要的是 逆序的！
     * 后进先出，从后往前 依次相加！
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建 栈
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);

        // 依次弹出，进行相加，进位等，注意：最后要的是 新链表，所以 需要一个 哨兵，把每次 加出来的数值，放到 哨兵后面

        // 进位值
        int carry = 0;
        ListNode dummy = new ListNode(-1);

        // todo 依旧需要进行 计算的条件
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop();
            int num2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = num1 + num2 + carry;

            // 更新
            carry = sum / 10;
            // 新结点 插入 最前面
            ListNode node = new ListNode(sum % 10);
            node.next = dummy.next;
            dummy.next = node;
        }

        // 返回 结果 链表
        return dummy.next;
    }

    private Stack<Integer> buildStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        return stack;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
