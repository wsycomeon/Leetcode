package com.wsy.leetcode.tree.AL_543_diameterOfBinaryTree;

public class Official {

    // TODO: 2020-08-08 11:10:14 3分钟 有思路了，对于 任意一个结点 作为root结点的树，经过其的 最长直径的长度，应该等于 左子树 深度 + 右子树 深度
    // TODO: 2020-08-08 11:27:20 官方题解，完全一致。。。就是 多解释了一点，路径 = 结点数 - 1；

    //  ---》自己的想法 还有一个误区：---》 root 结点的直径 不一定是 最大直径，应该 求 所有结点的路径 取 最大值 --》 其实 题目 已经 明确 告知了！

    // TODO: 2020-08-08 11:49:02 看了下 视频解释 ---》比较基础的，还讲了 求树高度的方式、递归怎么写。。
    // TODO: 2020-08-08 11:57:16 书写完毕

    int max = 0;

    /**
     * 方法1：
     * 还是我上面写的方案：
     * 求整个树中 任一结点 作为根节点 连接左右路径的最小值的 最大值
     * 而这个路径长度 = 左子树 深度 + 右子树 深度
     * todo 所以，需要 求 子树的深度的方法 --》在这个过程中，就可以求得 经过当前结点的路径值了
     * <p>
     * <p>
     * 时间：O(n) 所有结点 都要 访问一遍
     * 空间：O(height) 递归调用 栈的深度 最多是 height
     */
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;

        depth(root);

        return max;
    }

    /**
     * 求 树 的深度
     */
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        // 比较更新 --> left + right 就是 新 路径长度
        max = Math.max(max, left + right);

        // 当前 树的高度
        return Math.max(left, right) + 1;
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
