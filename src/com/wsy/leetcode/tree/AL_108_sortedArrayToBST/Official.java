package com.wsy.leetcode.tree.AL_108_sortedArrayToBST;

public class Official {

    // TODO: 2020-08-12 15:08:17 将一个升序数组，转换成 一个 平衡、二叉搜索树
    // TODO: 2020-08-12 15:12:15 根据 BST 又是 高度平衡的树的性质，
    // 感觉 我们用二分法，每次 取 区间的中间值 做 根结点 最后返回，然后 dfs 拼接 左、右子树的root 即可
    // TODO: 2020-08-12 15:24:47 自己代码 写完了

    // TODO: 2020-08-12 15:37:09 官方思路 和 我的一样，就是取 mid 坐标 的 计算，可以有三种 罢了


    /**
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/
     * <p>
     * 方法1：
     * 对一个 升序 区间，构造 一颗 平衡的 二叉搜索树，
     * todo 取 mid 值 为 根结点，然后 二分 左、右 区间 来 重复操作，返回 左、右 子 平衡 二叉搜索树，拼接上 即可！
     * <p>
     * 时间：O(n) --> 数组中的每个数值 都被 访问一次，创建为 结点。
     * 空间：O(logn) --> 完全二分，就是 logn 次，递归调用栈的深度 也是 logn
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }


        return dfs(nums, 0, nums.length - 1);
    }


    /**
     * todo 数组 二分后 的 每一个区间 对应一棵树，返回 [left , right] 区间的 树
     * 即 其 根结点，且 还要 拼接上 dfs 的 左、右子树
     */
    private TreeNode dfs(int[] nums, int left, int right) {
        // 1、终止条件 --》也可以说是 区间 空了，是 空树
        if (left > right) {
            return null;
        }

        // 2、处理当前层
        // TODO: 2020-08-12 15:37:27 其实，这里的 mid 取值 是 不固定的，当 区间 长度是 偶数时
        // 可以是 (left + right) / 2 这样取 中间值 左值 或者 (left + right + 1) / 2 取 中间值 右值
        // 都行的，只不过 我们习惯了 左值，那就选 左边 吧 ！
        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        // 3、递归
        TreeNode leftChild = dfs(nums, left, mid - 1);
        TreeNode rightChild = dfs(nums, mid + 1, right);

        // 4、善后 --> 拼接
        node.left = leftChild;
        node.right = rightChild;

        return node;
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
