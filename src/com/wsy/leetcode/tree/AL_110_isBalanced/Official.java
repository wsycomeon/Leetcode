package com.wsy.leetcode.tree.AL_110_isBalanced;

import sun.reflect.generics.tree.Tree;

public class Official {

    // TODO: 2020-08-07 23:14:37  
    // TODO: 2020-08-07 23:14:39 --》 求所有子树的高度值，判断 任一子树的 左右子树的高度 差 的绝对值 > 1，就是 false
    // TODO: 2020-08-07 23:34:36 官方 答案 还是有些门道的，两个方案，自顶向下 和 自底向上  --》都是 递归来做
    // TODO: 2020-08-08 00:12:09 整理 完毕！

    /**
     * https://leetcode-cn.com/problems/balanced-binary-tree/solution/ping-heng-er-cha-shu-by-leetcode/
     * <p>
     * <p>
     * 一棵 高度平衡 二叉树定义为：
     * 一个二叉树 每个节点 的左、右 两个子树的高度差的 绝对值 不超过 1，即 <= 1
     * <p>
     * <p>
     * 方法1：自顶向下的 判断方法：
     * todo 先判断 当前顶点的左、右子树高度 差值 绝对值 <= 1，在判断  左、右子树 也都是 平衡树 才行 ！
     * <p>
     * 时间：O(n*logn)
     * --》平衡二叉树的高度 <= logn，即 递归调用 isBalanced() 最多 logn 次，而 每次进入，都会求 height() 是 O(n)
     * <p>
     * 空间：O(logn) 但是，如果 树 完全倾斜，那就是 O(n)
     * <p>
     * <p>
     * <p>
     * ---》todo 注意，这个 自顶向下的 方案，计算出 树的高度 后，并没有 保存 其子树的高度，所以 导致 递归的时候，子树的高度 又要 重复计算，很不好 ！
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced1(root.left)
                && isBalanced1(root.right);
    }

    // 求一个结点 作为 根结点的树 的高度 --》时间复杂度是 O(n)
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }


    /**
     * 方法2：
     * <p>
     * 自底向上的方案：
     * <p>
     * ---> 要 记录 子树的高度 和 是否平衡 TreeInfo ！
     * todo 先判断 左、右子树 是否平衡，都平衡 再去判断 左右子树的高度差的绝对值 <= 1
     * <p>
     * --> todo 都是 递归 实现的 --》只不过 处理 当前层 和 子树递归的顺序 不同！
     * <p>
     * <p>
     * 时间：O(n)  --》会 求出 每个子树的信息 即 n 个 --》而 每次 都是先求 子树 是否平衡 ，再求 自身是否平衡 --》操作 都是 O(1)
     * 空间：最差就是 树 成了 单链表，就是 O(n)
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return getTreeInfo(root).balanced;
    }

    /**
     * 获取 当前树的 相关信息 --》高度、是否平衡
     * <p>
     * TreeInfo 只有 在当前子树 是 平衡树的时候，高度 才是 有意义的 --》todo 否则，子树 高度 直接 传 0 即可，反正 也用不到！
     */
    private TreeInfo getTreeInfo(TreeNode root) {

        if (root == null) {
            // todo 高度 此时 确实 为 0 ！
            return new TreeInfo(0, true);
        }

        // 判断 左、右子树 是否平衡
        TreeInfo leftInfo = getTreeInfo(root.left);

        // 子树 不平衡，那么 当前 树 肯定 不平衡了 !
        if (!leftInfo.balanced) {
            return new TreeInfo(0, false);
        }

        TreeInfo rightInfo = getTreeInfo(root.right);
        if (!rightInfo.balanced) {
            return new TreeInfo(0, false);
        }

        // 再判断 高度差值的 绝对值
        if (Math.abs(leftInfo.height - rightInfo.height) <= 1) {

            return new TreeInfo(Math.max(leftInfo.height, rightInfo.height) + 1, true);
        }


        // 否则，就不平衡！
        return new TreeInfo(0, false);
    }

    /**
     * 自底向上的方案 用到的 子树的信息 ！
     */
    static class TreeInfo {
        int height;
        boolean balanced;

        public TreeInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
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