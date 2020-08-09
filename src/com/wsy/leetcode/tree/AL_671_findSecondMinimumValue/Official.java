package com.wsy.leetcode.tree.AL_671_findSecondMinimumValue;

public class Official {

    // TODO: 2020-08-09 22:48:50 求一个特殊的 二叉树（值 都是 正数） 的 第二小的值 --》不存在 第二小，就是 -1
    // TODO: 2020-08-09 22:55:24 有点思路了，还是 要遍历 所有结点，由题意知，最小值为 root的值，有一个第二小的值，默认 = -1；

    // TODO: 2020-08-09 23:13:02 我曹，官方的暴力解法 我都没想到 --》不过 性能差
    // TODO: 2020-08-09 23:28:34 整理完毕。

    /**
     * 官方 解法：
     *
     * 方法1：暴力枚举
     * dfs 遍历 所有结点，用 set 去重，
     * 然后 遍历 set，取第二小的数字 ---》第一小的是 root.val
     *
     * 时间：O(n) --》dfs 遍历时是 O(n)，然后 遍历 set，最多是O(n)
     * 空间：O(n) --》set的容量，因为 去重，最多是 n 个 ！
     *
     * --》todo 性能 比 下面的差
     */


    /**
     * 方法2：
     * <p>
     * 这个树 很有特点：
     * 非空、结点数值 都是 正数、子结点的数量 只能是 0 或者 2；
     * 若一个结点 不是 叶子结点，它的值 是 最小的 那个
     * <p>
     * 则，根结点 必然 是最小的
     * 这样的话，就可以 dfs遍历
     * <p>
     * 时间：O(n) --》所有结点 最多 遍历一次 --》中间有 剪枝 优化
     * 空间：O(height) --》递归调用栈的深度
     */
    int smallest;
    int secondSmallest;

    public int findSecondMinimumValue(TreeNode root) {
        smallest = root.val;
        secondSmallest = -1;

        // 开始 深度遍历
        dfs(root);

        return secondSmallest;
    }

    private void dfs(TreeNode node) {
        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理 当前层，和 最小值 比较
        if (node.val != smallest) {
            // 不相等，就必然 > 最小值
            // --> 看 当前 是否是 第二小的
            secondSmallest = secondSmallest == -1 ? node.val : Math.min(secondSmallest, node.val);

            // todo 因为 每个子树上，root 都是 最小的，而 当前的 root > smallest，
            //  所以 其子树上的 更大的值，是 不可能 是 第二小的，那就 剪枝，不用 遍历 ！

        } else {
            // 3、递归 --》当前 root 还是 smallest 的时候，下面的 才有可能 是第二小，才有 遍历的意义！
            dfs(node.left);
            dfs(node.right);
        }

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
