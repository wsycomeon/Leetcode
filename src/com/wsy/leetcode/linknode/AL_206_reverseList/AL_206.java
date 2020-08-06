package com.wsy.leetcode.linknode.AL_206_reverseList;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AL_206 {

    public static void main(String[] args) {


        test1();
        test2();
        test3();
    }

    private static void test1() {
//        ListNode node5 = new ListNode(5);
//        ListNode node4 = new ListNode(4);
//        node4.next = node5;
//        ListNode node3 = new ListNode(3);
//        node3.next = node4;

        ListNode node2 = new ListNode(2);
//        node2.next = node3;
        node2.next = null;
        ListNode node1 = new ListNode(1);
        node1.next = node2;

        System.out.println(new Official().reverseList(node1));

    }

    private static void test2() {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        node4.next = node5;
        ListNode node3 = new ListNode(3);
        node3.next = node4;
        ListNode node2 = new ListNode(2);
        node2.next = node3;
        ListNode node1 = new ListNode(1);
        node1.next = node2;

        System.out.println(new Official().reverseList2(node1));

    }

    private static void test3() {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        node4.next = node5;
        ListNode node3 = new ListNode(3);
        node3.next = node4;
        ListNode node2 = new ListNode(2);
        node2.next = node3;
        ListNode node1 = new ListNode(1);
        node1.next = node2;

        System.out.println(new Official().reverseList3(node1));

    }
}
