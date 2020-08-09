package com.wsy.leetcode.tree.AL_101_isSymmetric;

import java.util.LinkedList;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-09 11:41:45 判断 一棵树 是否是 对称二叉树（镜像 树）
    // TODO: 2020-08-09 11:42:14 思考了 5分钟后，大致知道，要递归 来做
    // 比较，每个子结点 都作为一棵树，翻转一下 左、右子结点，看下 是否相等
    // TODO: 2020-08-09 11:52:24 自己 先写了 递归算法 --》测试 通过！

    // TODO: 2020-08-09 12:21:51 看了 官方的，把 迭代的 也写了一遍 。。。


    /**
     * 方法1：
     * 递归做法
     * <p>
     * 时间：O(n) 所有结点 遍历一遍
     * 空间：O(height) 就是 递归调用 的 压栈深度，最多为 height，height 最多为 n
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return reverseEqual(root.left, root.right);
    }

    /**
     * 递归判断：dfs
     * <p>
     * 看 两个子树 是否 符合 翻转后 就相等 的条件 ！
     * --》子树 要 完全 翻转！
     */
    private boolean reverseEqual(TreeNode left, TreeNode right) {
        // 1、终止条件
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        // TODO: 2020-08-09 11:57:36 em  其实 下面的 三个 判断，一句话 就能搞定。。。

//        // 2、处理当前层
//        if (left.val != right.val) {
//            return false;
//        }
//
//        // 3、递归 检查 是否符合 翻转
//
//        // 有 一边 翻转 不等 ，就不等
//        if (!reverseEqual(left.left, right.right)) {
//            return false;
//        }
//
//        if (!reverseEqual(left.right, right.left)) {
//            return false;
//        }
//
//        // 4、善后 --》都符合 就是 true
//        return true;

        return left.val == right.val
                && reverseEqual(left.left, right.right)
                && reverseEqual(left.right, right.left);
    }


    /**
     * 方法2：迭代做法 --》广度 优先
     * 上述 递归方法 的 改写 ：
     * <p>
     * 用一个 queue ，维护 入队顺序，一次 入一对儿 ，poll 也是 poll出一对儿 进行 判断！
     * 比较 值 相当与否；
     * （1）不等 ，false
     * （2）否则，再将其 左、右子树 翻转 入队，循环比较
     * <p>
     * 最后，返回 true！
     * <p>
     * todo --》注意，初始时，可以将 root 入队两次，也可以 将其 左、右子树 入队 --》一样的
     * <p>
     * 时间：O(n)  所有结点 比较一次
     * 空间：O(n)  所有结点 都 只 入队一次，出队一次 --》所以 queue 最多 也就是 n
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        return checkPair(root.left, root.right);
    }

    /**
     * 用 queue 一对儿的判断
     */
    private boolean checkPair(TreeNode left, TreeNode right) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 初始的 一对儿 数据
        queue.offer(left);
        queue.offer(right);

        while (!queue.isEmpty()) {
            // poll 出 一对儿，比较之
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null) {
                continue;
            }

            if (node1 == null || node2 == null) {
                return false;
            }

            if (node1.val != node2.val) {
                return false;
            }

            // 本层 相等，各自的 左、右子结点 逆序 入队
            queue.offer(node1.left);
            queue.offer(node2.right);

            queue.offer(node1.right);
            queue.offer(node2.left);
        }


        // 前面 没有 不等条件，就是 true！
        return true;
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