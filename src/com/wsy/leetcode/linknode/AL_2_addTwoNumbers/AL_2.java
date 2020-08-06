package com.wsy.leetcode.linknode.AL_2_addTwoNumbers;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照  逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * <p>
 * --->
 * todo 这题是 链表 已经 逆序好 的情况；
 * 445题 是 正序链表 相加的情况（所以 要达到 逆序 效果，用 stack ！）
 */
public class AL_2 {

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {
        ListNode node1_3 = new ListNode(3);
        ListNode node1_2 = new ListNode(4);
        node1_2.next = node1_3;
        ListNode node1_1 = new ListNode(2);
        node1_1.next = node1_2;

        ListNode node2_3 = new ListNode(4);
        ListNode node2_2 = new ListNode(6);
        node2_2.next = node2_3;
        ListNode node2_1 = new ListNode(5);
        node2_1.next = node2_2;

        System.out.println(new Official().addTwoNumbers(node1_1, node2_1));

    }


}
