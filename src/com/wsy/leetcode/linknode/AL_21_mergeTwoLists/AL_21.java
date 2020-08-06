package com.wsy.leetcode.linknode.AL_21_mergeTwoLists;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class AL_21 {


    public static void main(String[] args) {

        test1();
        test2();

    }

    private static void test2() {
        ListNode head1_3 = new ListNode();
        head1_3.val = 4;
        ListNode head1_2 = new ListNode();
        head1_2.val = 2;
        head1_2.next = head1_3;
        ListNode head1_1 = new ListNode();
        head1_1.val = 1;
        head1_1.next = head1_2;

        ListNode head2_3 = new ListNode();
        head2_3.val = 4;
        ListNode head2_2 = new ListNode();
        head2_2.val = 3;
        head2_2.next = head2_3;
        ListNode head2_1 = new ListNode();
        head2_1.val = 1;
        head2_1.next = head2_2;

        System.out.println(new Official().mergeTwoLists2(head1_1, head2_1));
    }

    private static void test1() {
        ListNode head1_3 = new ListNode();
        head1_3.val = 4;
        ListNode head1_2 = new ListNode();
        head1_2.val = 2;
        head1_2.next = head1_3;
        ListNode head1_1 = new ListNode();
        head1_1.val = 1;
        head1_1.next = head1_2;

        ListNode head2_3 = new ListNode();
        head2_3.val = 4;
        ListNode head2_2 = new ListNode();
        head2_2.val = 3;
        head2_2.next = head2_3;
        ListNode head2_1 = new ListNode();
        head2_1.val = 1;
        head2_1.next = head2_2;

        System.out.println(new Official().mergeTwoLists(head1_1, head2_1));
    }




}
