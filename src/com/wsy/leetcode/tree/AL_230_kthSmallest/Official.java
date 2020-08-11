package com.wsy.leetcode.tree.AL_230_kthSmallest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Official {

    // TODO: 2020-08-11 17:33:06 BST的 第 K 小 的 元素。。。
    // TODO: 2020-08-11 17:35:21 根据 bst，中序遍历后 就是 从小到大的排序的数组 性质，取 index = k-1 的即可 --》复杂度 O(n) 中序遍历
    // TODO: 2020-08-11 17:36:24 如果 二叉搜索树 经常被修改（插入/删除 操作）并且你 需要 频繁地 查找 第 k 小的值，你将如何优化 kthSmallest 函数？
    // 能比 O(n) 还要好的，那就是 二分 查找了，可以 用一个数值 维护 树上的结点个数 length ，那么此时 root节点 就是。。。em --》todo 不对，这没说 是 平衡树啊！！
    // 用 平衡树 来搞 --》todo 官方也没解释。。。


    // TODO: 2020-08-11 17:56:05 看完 官方 解法，方法 1 一样（先存储 中序遍历 后 的排序结果 , O(n)的空间）
    // todo 方法2 做了优化，用了一个 计数方法 来做，这样 不用 全部遍历，且 空间是 O(height + k)
    // 至于，具体的做法 还是 俩 dfs模板 任意一个 + count 计数 即可；


    // todo 在这期间，和 别人聊天 50 分钟。。。

    // TODO: 18:43 ----》2020-08-11 19:08:50 整理 完毕 ！

    // TODO: 2020-08-11 19:22:42 实际 dfs的 递归 模板 不如 dfs的 迭代 模板 更容易 计数 ！


    /**
     * 方法1：
     * <p>
     * 1、中序遍历，从小到大的结果，存到list，
     * 2、然后取  第 k-1 个 就好了
     * <p>
     * 时间：O(n) --》所有结点 都访问一次
     * 空间：O(n) --》额外空间有：递归调用栈空间 height <= n 、 排序后的空间占用list 是 n，所以，取大是 O(n)
     * <p>
     * todo --》当然了，这里 用 dfs 递归模板 + count 计数 一样可以做，和 下面的 迭代模板 + count一样效果
     */
    public int kthSmallest1(TreeNode root, int k) {
        // 题目 约束了 边界条件，这里就 不考虑了
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(k - 1);
    }

    // bst 的 中序遍历
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    /**
     * 方法2：
     * dfs 深度优先搜索，用 stack + 迭代 来实现 --》todo 最优 解法！
     * 中序 遍历
     * --》这样的话，可以控制 访问到 第 k 个 就结束了，不需要 全部 访问到 ！
     * 还是 用 模板方法 来写
     * <p>
     * 时间：O(height + k) --》这样 dfs 一定会到达 最左下角，但是 左下角 不一定是 height 高，而 height 和 k的大小不确定，所以是 +
     * 空间：O(height + k) --》同样 原因
     */
    public int kthSmallest(TreeNode root, int k) {
        int count = 0;

        Stack<Object> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object obj = stack.pop();

            // TODO: 2020-08-11 18:57:07 草！又忘记 判空了
            if (obj == null) {
                continue;
            }

            if (obj instanceof Integer) {
                count++;
                if (count == k) {
                    // 找到了，第 k 小的元素
                    return (int) obj;
                }
            } else {
                // 否则，就是 node 类型，入栈
                TreeNode node = (TreeNode) obj;

                // 中序遍历，即 左 根 右，逆序入栈，就是 右 根 左
                stack.push(node.right);
                stack.push(node.val);
                stack.push(node.left);
            }
        }

        // 不可能 找不到，随便返回
        return -1;
    }


    /**
     * 方法3：dfs 递归模板 + 计数 方案
     * todo --> 这个方案 不太好 控制 返回值 --》要考虑 边界条件！
     */
    int count;

    public int kthSmallest3(TreeNode root, int k) {
        // 题目 约束了 边界条件，这里就 不考虑了
        count = 0;

        return dfs2(root, k);
    }

    private int dfs2(TreeNode node, int k) {

        if (node != null) {

            // 中序遍历 模板 --> 左 根 右
            int i = dfs2(node.left, k);
            if (i != -1) {
                return i;
            }

            count++;
            if (count == k) {
                return node.val;
            }

            int j = dfs2(node.right, k);
            if (j != -1) {
                return j;
            }

        }

        return -1;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
