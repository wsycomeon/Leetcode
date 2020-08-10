package com.wsy.leetcode.tree.AL_513_findBottomLeftValue;

import java.util.LinkedList;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-10 11:07:45 找到 树 左下角的值
    // TODO: 2020-08-10 11:10:18 理论上来说，我们 dfs 或者 bfs 遍历一遍的时候，传入 其所在层的index 和 是否是左结点，进行比较 是否是 index更大的左结点 就好了吧。。
    // TODO: 2020-08-10 11:15:19 没有 官方题解，自己写吧
    // TODO: 2020-08-10 11:24:22 写完了


    // TODO: 2020-08-10 11:36:54 --> 草 不对 ，这题目 描述有问题 --》实际上 要求的是 --》当前树 最后一层 最左边的数值 --》没说 必须是 左子结点 ！！！
    // 草草草，那就是用 层序遍历 bfs，遍历一层元素的时候，先 右 后 左的入队 queue，这样的话，实现了 一层结点的 逆序 --》保证 左边的最后 被访问到 md
    // 相当于 整棵树 翻转了，最后访问到的 最右边，实际上 之前的最左边！
    // TODO: 2020-08-10 11:48:19 整理 完毕！

    /**
     * 方法1：bfs 广度遍历
     * todo --》访问结点时，子结点 逆序 入队，实现 顺序 翻转 --》最后 读出来的 就是 最后一层、最左边的
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 最后一个 按照逆序 被访问到的 就是 目标值 --》最后一层的 最左结点
        TreeNode node = root;

        while (!queue.isEmpty()) {
            node = queue.poll();

            // TODO: 2020-08-10 11:47:23 子结点 要 逆序入队，先 右 后 左 ！
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
        }

        return node.val;
    }


    /**
     * todo --------------------- 错误解法 ---------------------------------------------
     * 方法1：dfs 深度优先搜索 todo -----》这题目 描述有问题
     * <p>
     * 几个变量：树的最大深度、当前最深的 左子结点的深度 和 数值
     */
    int maxDepth;
    int maxLeftDepth;
    int maxLeftDepthVal;

    public int findBottomLeftValueX(TreeNode root) {
        // 题目说，假设 不是 空树。
        // 初始化
        maxDepth = 0;
        maxLeftDepth = 0;
        // TODO: 2020-08-10 11:29:19 看来题目的意思是，根 可以认为是 左、右子结点
        maxLeftDepthVal = root.val;

        dfs(root, 0, true);

//        return maxLeftDepth == maxDepth ? maxLeftDepthVal : -1;
        // TODO: 2020-08-10 11:30:01 本题 要的是 最深的 左子结点，而不是 最后一层的 左子结点（可能没有）

        return maxLeftDepthVal;
    }

    private void dfs(TreeNode node, int depth, boolean isLeft) {
        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理当前层
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        if (isLeft) {
            // 如果是 左结点，判断 是否是 更大的深度
            if (depth > maxLeftDepth) {
                maxLeftDepth = depth;
                maxLeftDepthVal = node.val;
            }
        }

        // 3、递归
        dfs(node.left, depth + 1, true);
        dfs(node.right, depth + 1, false);

        // 4、善后
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