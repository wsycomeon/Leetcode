package com.wsy.leetcode.tree.AL_687_longestUnivaluePath;

public class Official {

    // TODO: 2020-08-09 17:28:37 最长的 、相同数值 路径的长度， 即 边数 --> = 结点数 - 1 --》可以 不经过 根结点 ！
    // TODO: 2020-08-09 17:39:02 貌似，有个想法，dfs 遍历，计算 所有 相连的、相同的 结点数，取最大值，最后 -1；
    // TODO: 2020-08-09 18:09:43 哎，自己的想法，有点懵逼，还是看 官方的思路吧 --》写的也有点 晦涩

    // TODO: 2020-08-09 19:03:49 草，真的 不简单
    // TODO: 2020-08-09 19:21:19 坑，完毕！--》箭头 = 1（自身占一个） + 左、右子箭头的较大值 ；路径 = 左、右子箭头 之和 --》求 最大 路径值

    int max = 0;

    /**
     * 方法1：dfs 做法
     * 任意路径（值相等） ，都有一个 根结点 --》这个 根结点 是唯一的；
     * <p>
     * 这个路径的长度 = 从该 根结点 开始的（不包含 根节点） 向左的、数值相等的、紧密相连的结点的个数 + 对应 向右的路径上 相同的结点数
     * <p>
     * 这里 两个方向，可以说是从 某个根结点 的 左、右子结点 出发的 单向的 箭头值
     * <p>
     * --》箭头长度 = 包括 当前结点的 某个子结点 在内的，单向的 相同值的 最大结点数；
     * <p>
     * todo 则 对于 任一根结点来说，其 路径值 = 左子箭头长度 + 右子箭头长度；
     * 遍历 所有的结点，计算 其 路径值，取 最大的 即可 --》 依赖于 箭头长度
     * <p>
     * 定义 这么一个函数：arrowLength(node)
     * 求 node 作为一个方向箭头的 起点的 箭头长度
     * 即 = 1 + node 的 左、右 子箭头的 较大值
     * <p>
     * 时间：O(n) --》 所有 结点 遍历一次
     * 空间：O(height) --》 递归调用栈的深度，最多是height
     */
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        max = 0;

        arrowLength(root);

        return max;
    }

    /**
     * 求 node 作为 箭头的起点，该箭头的最大长度（和 node值 相同的 、单向的 结点数）
     */
    private int arrowLength(TreeNode node) {
        // 1、终止条件
        if (node == null) {
            return 0;
        }

        // 2、处理当前层

        // 3、递归
        // todo 先求其 左、右子结点 分别作为 各自箭头起点的 箭头值 --》这里 没判断 和 当前值的关系，先计算出来，即 不一定是 当前结点的 子箭头 ！
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);


        // 4、善后
        // 计算 和 当前值 相同的、 左、右 子箭头的值（结点数量）
        int leftArrow = 0;
        int rightArrow = 0;

        if (node.left != null && node.left.val == node.val) {
            leftArrow = left;
        }

        if (node.right != null && node.right.val == node.val) {
            rightArrow = right;
        }

        // 计算出：以 当前结点 为 根结点的 路径长度
        int pathLength = leftArrow + rightArrow;
        // 比较 最大值
        max = Math.max(max, pathLength);

        // 当前 箭头值 = 1 + 左、右 子箭头的较大值
        return 1 + Math.max(leftArrow, rightArrow);
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