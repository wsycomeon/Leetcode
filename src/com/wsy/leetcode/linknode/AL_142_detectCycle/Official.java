package com.wsy.leetcode.linknode.AL_142_detectCycle;

import java.util.HashSet;
import java.util.Set;

public class Official {


    /**
     * 如果链表有环，寻找 入环点
     * 1、第一个方法，比较简单，直接 使用hashSet 存 所有的结点
     * 然后，next的时候，检测，是否包含 就好了。。。
     * 遍历一遍就够了，所以 时间复杂度是 O(n)
     * 但是，因为 要把所有结点，都存一遍，所以 空间复杂度 也是 O(n) ---> 下面 有更好的办法处理
     *
     * @return 返回 入环点！
     */
    public ListNode detectCycle(ListNode head) {
        // 1、HashSet 记录，访问过的结点
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;

        while (node != null) {
            // 访问过的结点，又被访问，说明 这就是 入 环 口，返回
            if (visited.contains(node)) {
                return node;
            }

            // 否则，记录之
            visited.add(node);

            // 向后遍历 下一个结点
            node = node.next;
        }

        // 前面没有返回，说明 访问到了末端，也没看到环，那就 是 无环的！
        return null;
    }


    /**
     * 方法2：
     * 这个是根据 数学推导 得来的结论：
     * 具体的看：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/
     * 1、首先 使用 快慢指针，分别从前往后遍历，寻找 相遇点
     * （1）如果有环，二者后面一定会套圈、相遇，二者的第一次相遇，一定是在环内的某个位置；
     * （2）如果没还，快指针 早就访问到头了，直接返回 null
     * 2、如果上面有环，重新取 俩指针，一个从 链表头，一个从 第一次相遇点，
     * 都以一倍速 继续next遍历，
     * 二者的 下一次相遇 一定是在入环点 ----》todo 这个是 可以证明的！
     * 返回这个点 就好了
     * <p>
     * =======================================================================
     * 简略记录下 证明过程；
     * --> 图的话，自己画，或者 官网看
     * <p>
     * 1、假设有环，起点到入环点 需要走 F步，环走一圈 需要 C步，
     * 假设 第一次相遇 是在 环上 超过 入环点的第a步
     * 此时：快指针 已经走了n圈了。这个要看 C 和 F 的 相对大小了
     * 则：
     * 慢指针路程 = F + a
     * 快指针路程 = F + n * C + a
     * 而 快 = 2 * 慢 ，所以，F + n * C + a  = 2(F + a)
     * 即，F + (n-1) * C + (C-a + a)+a = 2(F+a)
     * 即 todo  F = C-a + (n-1)*C
     * =============》 这个等式很重要
     * 即，有环的话，第一次 在环上的a处相遇，能达到上述 结论：
     * 2、此时。
     * 让两个普通指针，p1、p2 以 相同速度 从 头 和 a点 一起走；
     * p1 走了 F 步，达到入环点时，
     * p2 也会走 F步，此时的点在哪儿呢？
     * p2 最开始在a处，所以 a + F = a + C-a + (n-1)*C = n * C ，即 距离 入口点 n*C 远。。
     * todo p2 也恰好 走到了 入环点！
     * 则 p1、p2 相遇的点 就是入环点，返回 即可 ！
     * <p>
     * =======================================================================
     * <p>
     * 主要还是下面的代码实现！
     * <p>
     * Floyd 的 快慢指针 算法 仅需要几个指针，所以只需 常数级别的额外空间。
     */
    public ListNode detectCycle2(ListNode head) {
        // 临界条件
        if (head == null) {
            return null;
        }

        // 1、寻找，用快慢指针 寻找 第一次相遇点！
        ListNode intersect = getIntersect(head);
        // 没有相遇点，那就是 无环！
        if (intersect == null) {
            return null;
        }


        // 2、然后根据 上面的结论，用另外两个 同速指针 都往前走，相遇的点 就是入环点！
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;

        // 直到相遇，否则 同步走
        while (ptr1 != ptr2) {
            // 同步走
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // 返回 入环点！
        return ptr1;
    }


    private ListNode getIntersect(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // fast 移动快，所以 最先可能到达 末尾，所以 以fast判断
        while (fast != null && fast.next != null) {
            // 移动
            slow = slow.next;
            fast = fast.next.next;

            // 只有有环，二者 才可能 相遇
            if (slow == fast) {
                return slow;
            }
        }

        // 前面 没有相遇，跳出了循环，那就是 五环
        return null;
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
