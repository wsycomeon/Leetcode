package com.wsy.leetcode.tree.AL_104_maxDepth;

import java.util.LinkedList;
import java.util.Queue;

public class Official {


    // TODO: 2020-08-07 22:46:57
    // TODO: 2020-08-07 22:48:04 递归来做
    // TODO: 2020-08-07 22:54:40 看完官方答案， 官方答案 第一个方法 就是 优化的递归，第二个 则是 比较少见的 广度优先搜索的思路 --》其实有点 强行符合的感觉
    // TODO: 2020-08-07 23:10:43 验证完毕

    /**
     * 求树的最大深度 --》
     * <p>
     * 方法1：
     * 递归求，当前结点 + 左、右子树的最大高度  即 当前结点 为 根节点的树的最大高度
     * <p>
     * 时间：O(n) 每个结点 都会被 遍历一次
     * 空间：O(height) 递归的最大深度 就是 树的最大深度 ！
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 否则，就等于 左、右子树的最大高度的较大值 + 1
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 方法2：
     * todo 少见的、广度优先搜索的 变种方法 ---》 感觉 没必要 记录 这种 繁杂的方法
     * 其实，用队列 每次存一层的节点，然后开始遍历，层数+1，即 count +1， 一次 处理一层的结点，同时 将他们的子节点 入队 ！
     * <p>
     * 时间：O(n) 所有结点 还是要访问一次
     * 空间：最快情况下 是 O(n)
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        // 1、第一层 元素 入队
        queue.offer(root);

        int count = 0;

        // 2、只有 queue 不空，就还有 新的一层 结点，需要处理！
        while (!queue.isEmpty()) {

            count++;

            // 一次性 处理 这一层的结点，用 size 记录下 ！
            int size = queue.size();

            while (size > 0) {
                size--;
                // 处理 当前层的 一个顶点
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // 处理完一层了，继续 看下 有没有 下一层

        }

        return count;
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
