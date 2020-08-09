package com.wsy.leetcode.tree.AL_404_sumOfLeftLeaves;

public class Official {


    // TODO: 2020-08-09 17:01:37 所有 左 叶子结点 之和
    // TODO: 2020-08-09 17:03:17 dfs 遍历下 不就好了
    // TODO: 2020-08-09 17:12:19 自己的 dfs 写完了
    // TODO: 2020-08-09 17:15:23 没有 官方解法。。 自己的测试 就可以了
    // TODO: 2020-08-09 17:21:46 整理完毕。

    int sum = 0;

    /**
     * 方法1：
     * dfs 遍历 所有的 结点，发现 是 左叶子结点 时，累加其值 即可。
     * --》叶子结点，容易 区分，
     * todo 难点 在于 如何区分 其是 左 还是 右结点 --》其实 在 dfs 递归 时，传进来 即可。
     * <p>
     * 时间：O(n) --》所有 结点 都会被 访问一次。
     * 空间：O(height) --》即 递归调用 方法栈 的深度 --》即 树的高度 height --》最极端 就是 单链表，那时候 是 O(n)
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        sum = 0;

        dfs(root, false);

        return sum;
    }

    /**
     * 递归 累加 左子结点的值
     */
    private void dfs(TreeNode node, boolean isLeft) {

        //1、终止条件：是 叶子结点
        if (node.left == null && node.right == null) {

            // 只有 左子结点 才累加
            if (isLeft) {
                sum += node.val;
            }

            return;
        }

        // 2、处理 当前层 不是 叶子结点

        // 3、递归处理，左、右 子树
        if (node.left != null) {
            dfs(node.left, true);
        }

        if (node.right != null) {
            dfs(node.right, false);
        }

        // 4、善后，没啥 处理的。
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
