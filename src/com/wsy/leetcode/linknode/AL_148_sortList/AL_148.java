package com.wsy.leetcode.linknode.AL_148_sortList;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AL_148 {

    public static void main(String[] args) {

        test1();
        test1_2();

        test2();
        test2_2();

    }

    private static void test1() {
        ListNode node4 = new ListNode(3);
        ListNode node3 = new ListNode(1);
        node3.next = node4;
        ListNode node2 = new ListNode(2);
        node2.next = node3;
        ListNode node1 = new ListNode(4);
        node1.next = node2;

        System.out.println(new Official().sortList(node1));
    }

    private static void test1_2() {
        ListNode node4 = new ListNode(3);
        ListNode node3 = new ListNode(1);
        node3.next = node4;
        ListNode node2 = new ListNode(2);
        node2.next = node3;
        ListNode node1 = new ListNode(4);
        node1.next = node2;

        System.out.println(new Official().sortList2(node1));
    }

    private static void test2() {
        ListNode node4 = new ListNode(0);
        ListNode node3 = new ListNode(4);
        node3.next = node4;
        ListNode node2 = new ListNode(3);
        node2.next = node3;
        ListNode node1 = new ListNode(5);
        node1.next = node2;
        ListNode node = new ListNode(-1);
        node.next = node1;

        System.out.println(new Official().sortList(node));
    }

    private static void test2_2() {
        ListNode node4 = new ListNode(0);
        ListNode node3 = new ListNode(4);
        node3.next = node4;
        ListNode node2 = new ListNode(3);
        node2.next = node3;
        ListNode node1 = new ListNode(5);
        node1.next = node2;
        ListNode node = new ListNode(-1);
        node.next = node1;

        System.out.println(new Official().sortList2(node));
    }
}
