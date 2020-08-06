package com.wsy.leetcode.linknode.AL_23_mergeKLists;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class AL_23 {
    public static void main(String[] args) {

        test1();
        test2();


        test3();
    }

    private static void test1() {
        ListNode[] array = new ListNode[3];

        ListNode node1_1 = new ListNode(5);
        ListNode node1_2 = new ListNode(4);
        node1_2.next = node1_1;
        ListNode node1_3 = new ListNode(1);
        node1_3.next = node1_2;
        array[0] = node1_3;


        ListNode node2_1 = new ListNode(4);
        ListNode node2_2 = new ListNode(3);
        node2_2.next = node2_1;
        ListNode node2_3 = new ListNode(1);
        node2_3.next = node2_2;
        array[1] = node2_3;

        ListNode node3_1 = new ListNode(6);
        ListNode node3_2 = new ListNode(2);
        node3_2.next = node3_1;
        array[2] = node3_2;

        System.out.println(new Official().mergeKLists(array));
    }

    private static void test2() {
        ListNode[] array = new ListNode[3];

        ListNode node1_1 = new ListNode(5);
        ListNode node1_2 = new ListNode(4);
        node1_2.next = node1_1;
        ListNode node1_3 = new ListNode(1);
        node1_3.next = node1_2;
        array[0] = node1_3;


        ListNode node2_1 = new ListNode(4);
        ListNode node2_2 = new ListNode(3);
        node2_2.next = node2_1;
        ListNode node2_3 = new ListNode(1);
        node2_3.next = node2_2;
        array[1] = node2_3;

        ListNode node3_1 = new ListNode(6);
        ListNode node3_2 = new ListNode(2);
        node3_2.next = node3_1;
        array[2] = node3_2;

        System.out.println(new Official().mergeKLists2(array));
    }

    private static void test3() {
        ListNode[] array = new ListNode[3];

        ListNode node1_1 = new ListNode(5);
        ListNode node1_2 = new ListNode(4);
        node1_2.next = node1_1;
        ListNode node1_3 = new ListNode(1);
        node1_3.next = node1_2;
        array[0] = node1_3;


        ListNode node2_1 = new ListNode(4);
        ListNode node2_2 = new ListNode(3);
        node2_2.next = node2_1;
        ListNode node2_3 = new ListNode(1);
        node2_3.next = node2_2;
        array[1] = node2_3;

        ListNode node3_1 = new ListNode(6);
        ListNode node3_2 = new ListNode(2);
        node3_2.next = node3_1;
        array[2] = node3_2;

        System.out.println(new Official().mergeKLists3(array));
    }
}
