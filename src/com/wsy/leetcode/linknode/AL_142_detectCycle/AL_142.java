package com.wsy.leetcode.linknode.AL_142_detectCycle;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * <p>
 * <p>
 *  
 * <p>
 * 进阶：
 * 你是否可以 不用额外空间 解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AL_142 {

    public static void main(String[] args) {

        test1();
        test1_2();
        test1_3();
        System.out.println();

        test2();
        test2_2();
        test2_3();

    }

    private static void test1() {
        ListNode node1 = new ListNode(-4);
        ListNode node2 = new ListNode(0);
        node2.next = node1;
        ListNode node3 = new ListNode(2);
        node3.next = node2;
        ListNode node4 = new ListNode(3);
        node4.next = node3;

        // 注意，添加 入环点
        node1.next = node3;

        printNodeValue(new Official().detectCycle(node4));

    }

    private static void test2() {
        ListNode node1 = new ListNode(-4);
        ListNode node2 = new ListNode(0);
        node2.next = node1;
        ListNode node3 = new ListNode(2);
        node3.next = node2;
        ListNode node4 = new ListNode(3);
        node4.next = node3;

        // 注意，添加 入环点
        node1.next = node3;

        printNodeValue(new Official().detectCycle2(node4));

    }

    private static void test1_2() {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        node2.next = node1;
        // 注意，添加 入环点
        node1.next = node2;

        printNodeValue(new Official().detectCycle(node2));

    }

    private static void test2_2() {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        node2.next = node1;
        // 注意，添加 入环点
        node1.next = node2;

        printNodeValue(new Official().detectCycle2(node2));

    }


    private static void test1_3() {
        ListNode node1 = new ListNode(1);

        printNodeValue(new Official().detectCycle(node1));

    }

    private static void test2_3() {
        ListNode node1 = new ListNode(1);

        printNodeValue(new Official().detectCycle2(node1));

    }


    private static void printNodeValue(ListNode node) {
        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("null");
        }
    }

}
