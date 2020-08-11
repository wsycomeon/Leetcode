package com.wsy.leetcode.tree.AL_669_trimBST;

public class Official {

    // BST  binary search tree 二叉搜索树 --》性质是：
    // 1、树上的 任意一颗子树的 根结点，>= 其左子树的所有结点，<= 其右子树上的所有结点，是一个 中间值
    // 2、中序遍历，即为 从小到大 排序 的 数组 ！


    // TODO: 2020-08-11 16:36:02 修剪 BST ，只保留 指定范围 的结点
    // TODO: 2020-08-11 16:51:05 思路大概有了，需要 DFS 深度遍历，每一个结点，传入其 parent 和 左、右 子树的标记，以及 l r
    // 并且 rootNode 要是一个 全局变量，发现是 null的时候，就将第一个有效，符合 区间条件的 结点 赋值上去 即可
    // TODO: 2020-08-11 17:06:50 写完了 --》测试通过，注意 parent 判空！


    // TODO: 2020-08-11 17:14:17 我曹 ---》一样的思路，但是 官方的实现 非常棒
    // TODO: 2020-08-11 17:29:17 整理 完毕  ！


    /**
     * 方法2 ： 官方思路，dfs 实现
     * <p>
     * 将 trimBST(node) 的 返回值 看做，给当前 根结点为 node（待 修剪的） 的 子树 进行 修剪后，返回的 符合条件的 子树 --》给 父结点 用
     * 和 我下面的思路一致的 修剪方法；
     * <p>
     * 时间: O(n)
     * 空间：O(height)
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            return trimBST(root.right, L, R);
        }

        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        // 否则的话，就说明 当前子树的 根节点 符合要求
        // dfs 去修剪 它的左、右子树 即可，赋值给 它的 左、右 子节点
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }


    TreeNode newRoot;

    /**
     * 方法1： dfs 遍历 所有结点，符合 要求的留下，不符合的删掉：
     * <p>
     * 具体做法是：
     * 1、当前 结点 符合条件，就继续 dfs 排查、修剪 它的左右子树
     * 2、不符合的话
     * （1）node.val < L，那么 node 的 左子树 更小，不用考虑，只 修剪 它的 右子树 即可
     * （2）node.val > R，那么 node 的 右子树 更大，不用考虑，只 修剪 它的 左子树 即可
     * <p>
     * ----》
     * todo 我下面的 方法实现，总体 思想一致，复杂度 也一样，但是 太啰嗦了，官方的明显 更好
     * <p>
     * 时间：O(n) --》所有结点 遍历一遍
     * 空间：O(height) --》递归调用栈 深度
     */
    public TreeNode trimBST2(TreeNode root, int L, int R) {
        dfs(root, null, false, L, R);
        return newRoot;
    }

    private void dfs(TreeNode node, TreeNode parent, boolean isLeft, int l, int r) {
        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理当前层
        if (node.val >= l && node.val <= r) {
            // 符合 区间条件，当前结点 不被 剪枝
            // root 判断
            if (newRoot == null) {
                newRoot = node;
            }

            // 3、递归
            dfs(node.left, node, true, l, r);
            dfs(node.right, node, false, l, r);
        } else if (node.val < l) {
            // 当前值 太小，还有 其左子树 就不用考虑了，只用 遍历 其 右子树，作为 parent 的 指定方向的 子结点

            // todo 剪枝，拼接 起来，不然 就断了 --> parent 判空！
            if (parent != null) {
                if (isLeft) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            }

            dfs(node.right, parent, isLeft, l, r);

        } else {
            // 否则，就是 当前值 太大了，其 右子树 也不用考虑了，只遍历 其左子树

            // 剪枝，拼接
            if (parent != null) {
                if (isLeft) {
                    parent.left = node.left;
                } else {
                    parent.right = node.left;
                }
            }

            dfs(node.left, parent, isLeft, l, r);
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
