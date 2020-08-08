package com.wsy.leetcode.tree.AL_226_invertTree;

import java.util.LinkedList;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-08 11:59:58  em 这就是 嘲讽 homebrew 作者 翻转二叉树的题目啊
    // TODO: 2020-08-08 12:00:57  递归 翻转 一棵树 就好了 --》很简单啊
    // TODO: 2020-08-08 12:07:22 官方题解，多了一个 BFS 的思路 --》就是 queue 存储 当前 还没有 交互 子树的节点
    // TODO: 2020-08-08 12:21:31 搞定
    

    /**
     * 方法1：
     * DFS 翻转一棵树 --》递归 翻转完 左、右子树后，在翻转自身（交换 左、右子节点）
     * <p>
     * 自下而上
     *
     * <p>
     * 时间：O(n)
     * 空间：O(height)
     */
    public TreeNode invertTree(TreeNode root) {
        // 1、终止条件
        if (root == null) {
            return null;
        }

        // 2、处理当前层

        // 3、递归
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 4、善后
        root.left = right;
        root.right = left;

        return root;
    }


    /**
     * 方法2：
     * BFS 来翻转
     * queue 存储 尚未 翻转 子树的结点
     * <p>
     * 自上而下：先翻转为 自身，再翻转 子树
     * <p>
     * 时间：O(n)
     * 空间：O(n) --》 即 queue 的大小 --》对于二叉树来说，子结点 那一层 最多 n/2 个 叶子结点 --》画图 就知道了
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 要 翻转 子树的 node
            TreeNode node = queue.poll();

            // 翻转 子结点
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 不空的 子结点 要入队，等待 翻转
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
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