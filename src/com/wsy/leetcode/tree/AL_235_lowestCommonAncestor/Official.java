package com.wsy.leetcode.tree.AL_235_lowestCommonAncestor;

public class Official {


    // TODO: 2020-08-12 09:52:52 BST 上的 最近公共祖先
    // TODO: 2020-08-12 10:07:52 大概有思路了， dfs，递归，当前root结点 在 p q 组成的区间内，有 如下关系：
    // 1、root < min[p,q] ，去右子树 去找，必然 找得到；
    // 2、root > max[p,q], 去左子树 去找；
    // 3、root 为 p q 其中之一，因为是 dfs 深度遍历，那就是 root；
    // 4、p，q为其左右 子结点，那也 就是 root

    // TODO: 2020-08-12 10:21:22 一共 半个小时，搞定 ---》其实 就是 画图 看下


    // TODO: 2020-08-12 10:33:10 看了下 官方解法，思路 完全一致，就是 除了 方法1的 递归 实现外，还有一个方法2 迭代实现，将 空间复杂度 压缩到 O(1)
    // TODO: 2020-08-12 10:44:07 写完了，就这样了


    /**
     * 方法1：
     * 求 p q 的最近公共祖先，且 p q 都是 树上的结点，那么 必然存在 这个结点；
     * 就按照 如下 情况 分类处理，就行了。
     * 当前node 在 p q 区间的左侧 或者 右侧 ，那么 node 都不可能是 他俩 最近的祖先；
     * 因为 是 dfs的，所以 一旦 可能是 其祖先，那就是 最近的
     * <p>
     * 时间：O(n) --》所有结点 都可能 遍历一遍
     * 空间：O(height) --》递归调用栈 深度
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val = root.val;
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);

        if (val < min) {
            // 1、当前值 过小，必然 在其 右子树上
            return lowestCommonAncestor(root.right, p, q);
        } else if (val > max) {
            // 2、当前值 过大，必然 在其 左子树上
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // 3、否则，说明 当前结点 在 p，q之间，就是 p q的最近祖先
            return root;
        }

    }

    /**
     * 方法2：
     * 还是 上述思路，只不过 改成 迭代 来实现 --》 todo 性能更好
     * <p>
     * 时间：O(n) --》最多 全部遍历 一遍
     * 空间：O(1) --》只用了 几个指针。。
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        int pVal = p.val;
        int qVal = q.val;

        // 开始 遍历 树
        while (root != null) {
            int val = root.val;

            if (val < pVal && val < qVal) {
                // 当前值 过小，去 右子树 找
                root = root.right;
            } else if (val > pVal && val > qVal) {
                root = root.left;
            } else {
                return root;
            }
        }

        return null;
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