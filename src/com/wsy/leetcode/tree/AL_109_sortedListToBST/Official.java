package com.wsy.leetcode.tree.AL_109_sortedListToBST;

import java.util.ArrayList;
import java.util.List;

public class Official {

    // TODO: 2020-08-12 15:45:52 将一个 升序的、单向链表 转换为 平衡的 BST
    // TODO: 2020-08-12 15:46:52 方法1，将 链表 遍历一遍，转换成 数组存储，然后 用之前 升序数组 转 BST的方法 即可
    // 时间 O(n)，空间 O(n) --》数组 占用 空间 n，比 二分、递归的 logn 大


    // TODO: 2020-08-12 16:10:19 好吧，看了下 官方解法，一共有三个，一个比一个优化，我上面的解法 是 第二个
    // 时、空分别是：nlogn + logn ---> n + n (空间 换 时间) --> n + logn


    // TODO: 2020-08-12 17:07:33 写完了 --》方法2  最容易 理解，方法3 性能 最优


    /**
     * 方法1：
     * <p>
     * --》构造 平衡的、BST 的核心，还是 找到 中间结点，然后，二分区间，分别构造 左、右的 子 BST
     * <p>
     * 数组 可以直接 用 index，O(1) 定位，
     * todo 而 链表不行，链表定位 中心结点的方法，就是 快慢双指针法
     * ---》对了，定位到 中心结点之后，需要 断开 链表 为两部分，用一个 pre指针 存储 上一次的slow 即可
     * 然后，递归处理 左、右 子链表 拼接即可
     * <p>
     * <p>
     * 时间：O(nlogn) --》一次二分，slow 需要走 n/2 定位；即 单次定位 是 O(n)； 并且，一共 二分了 logn 次
     * 空间：O(logn) --》递归调用栈的深度，本身是 height，但是 本题目 是 平衡树，所以 height = logn
     * <p>
     * <p>
     * --》本方法的意思是，将一条 升序 链表，构造成一个 平衡的 子 BST 树，返回
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1、找到中间结点
        ListNode midNode = findMiddle(head);
        TreeNode node = new TreeNode(midNode.val);

        // todo 2、如果 链表上 就这么一个结点，后面 就不能 二分处理 了！
        if (midNode == head) {
            return node;
        }

        // 3、否则，传入 俩 链表头  递归 生成 左、右 子 平衡 BST
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(midNode.next);

        return node;
    }

    // 找到 一个链表的 中间结点，并且 断开 原链表
    private ListNode findMiddle(ListNode head) {
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // TODO: 2020-08-12 16:27:00 注意，这里要 断开 链表！
        if (pre != null) {
            pre.next = null;
        }

        return slow;
    }

    /**
     * 方法2：
     * 上面，每次找一个链表（一个区间）的中间结点 都要 重复找，所以 总的 复杂度 比较高
     * 这里，先 转换成 升序数组，以后 每次 就能 快速定位 了 --》空间 换 时间 !
     * 之后，就是 之前的 升序数组 生成 平衡 BST 的做法了。。
     * <p>
     * 时间：O(n) --》转换成 数组 一次 n，构建树 又是 n
     * 空间：O(n) --》数组 是 n，构建树 压栈 是 logn，所以 整体是 O(n)
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1、转换成 升序数组
        List<Integer> list = getSortedList(head);

        // 2、生成 BST 树
        return sortedRangeToBST(list, 0, list.size() - 1);
    }

    private TreeNode sortedRangeToBST(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));

        node.left = sortedRangeToBST(list, left, mid - 1);
        node.right = sortedRangeToBST(list, mid + 1, right);

        return node;
    }

    private List<Integer> getSortedList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }


    /**
     * 方法3：
     * 将一个升序链表，或者说 一个 升序空间，模拟 中序遍历 的 过程 来构建成 一个BST
     * 一棵树的组成： 需要 根节点，左子树，右子树，
     * 那么 中序遍历 构建 自然就是，依次构建 左子树、根节点、右子树了
     * <p>
     * 根据 题目要求 可知，构建一颗树 需要 一条链表，或者 一条链表上的一个区间，
     * 那么 整个链表的区间，就是 [0 , length-1] ，
     * 但是 我们这里 定位 根结点 不再用 前面的 双指针，太慢了。。。
     * 而是 直接计数，或者说，node 走完了 左子区间，自然 就到达了 中间结点，
     * 左子区间的定位 需要 指针，或者说 index，
     * 即 left、mid、right；
     * <p>
     * 思路如上，具体做法 如下：
     * 1、计算出 链表长度 length
     * 2、然后，从 head 结点开始，依次构建，开始 递归调用 区间创建 bst树 函数 即可；
     * <p>
     * 时间：O(n) --》计算长度 遍历一次 是 n；构建 整棵树，又遍历了一次，也是 n
     * 空间：O(logn) --》递归调用栈的深度，从左下角开始构建，这是一颗 平衡树，那么 height = logn
     */

    // head 得是 递归 能修改 的指针
    ListNode head;

    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) {
            return null;
        }

        int length = getLength(head);
        this.head = head;

        return getBST(0, length - 1);
    }

    // 从当前 head 开始，区间为 [left , right] 构建一个 bst 子树
    private TreeNode getBST(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        // 中序遍历，即 左 根 右 ，所以 要 先进行 左子树 的构建
        TreeNode leftBST = getBST(left, mid - 1);

        // 构建 根结点
        TreeNode node = new TreeNode(head.val);
        node.left = leftBST;

        // todo 当前 head 被使用了，链表指针 head 后移一位，开始 构建 右子树
        head = head.next;

        node.right = getBST(mid + 1, right);

        return node;
    }

    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
