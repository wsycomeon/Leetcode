package com.wsy.leetcode.tree.AL_617_mergeTrees;

import java.util.Stack;

public class Official {

    // TODO: 2020-08-08 12:25:19
    // TODO: 2020-08-08 12:27:28 直接 递归调用 就好了：merge 完 对应的左右子树，再merge自身，创建一个node返回 即可！
    // TODO: 2020-08-08 12:40:46 看完官方题解，方法 1 递归的思路一致，只不过 官方优化了下，没有 new 新的node，而是直接修改 t1 --》这样的话，必须是 前序遍历了（本打算自己创建的话，用后续遍历 ）
    // todo 关键是，官方 方法 2 把 dfs 递归 改成了 迭代的方式，难度 就上来了 --》 自己 维护一个 stack
    // TODO: 2020-08-08 13:12:17 写完了。。方法 2 真蛋疼！

    /**
     * todo 方法1：递归 实现 dfs --》简单易懂，且 高效！
     * <p>
     * 时间：O(n) n 是两个树的 结点数 的较小值
     * 空间：O(n) 递归调用 函数栈的深度，最多是 n 层 --》倾斜成 链表！
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 另一个树 为 null， 就不用 merge了，直接 返回就好
        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        // 直接 修改 t1 ，复用  不创建 新结点

        // 前序 遍历
        t1.val = t1.val + t2.val;
        // merge 子树
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }


    /**
     * todo 方法2： 自己维护 stack ，迭代 实现 dfs --》有点蛋疼！
     * 也是 复用 t1的空间
     * --》stack 存 待 merge 的 一对 结点 --》保证 左边的不为null，才 入栈！
     * <p>
     * stack 不为 null
     * pop 出来后，
     * 右边树的 结点 = null，跳过；
     * 先 value 相加，赋给 左边；
     * 在处理 左、右 子节点：
     * 左边的 子节点为 null --> 直接 用 右边的 赋值；
     * 否则，就将 两个 子节点 入栈，等待 下一次处理
     * <p>
     * 最后返回 t1
     * <p>
     * 时间：O(n) 两棵树的 较小 结点数
     * 空间：O(n) 最坏，倾斜成 单链表，stack 就是 n 个
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        // 这里，只用确保 t1 != null , 后面 就可以 发起 入栈了
        if (t1 == null) {
            return t2;
        }

        // 迭代 合并！

        // 存 两棵树上 对应的一对儿 结点
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});

        while (!stack.isEmpty()) {
            // 待 merge 的一对儿 结点
            TreeNode[] a = stack.pop();

            // TODO: 2020-08-08 13:06:59  a[0] 一定不为null，a[1] 可能为null，为 null ，就 不用 合并 对应的结点了，直接用 a[0] 即可
            if (a[1] == null) {
                continue;
            }

            // 值 merge
            a[0].val += a[1].val;

            // merge 左子结点
            if (a[0].left == null) {
                // 不用 merge，直接赋值 即可
                a[0].left = a[1].left;
            } else {
                stack.push(new TreeNode[]{a[0].left, a[1].left});
            }

            // merge 右子结点
            if (a[0].right == null) {
                a[0].right = a[1].right;
            } else {
                stack.push(new TreeNode[]{a[0].right, a[1].right});
            }

        }


        return t1;
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
