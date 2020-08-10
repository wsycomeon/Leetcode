package com.wsy.leetcode.tree.AL_144_preorderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Official {

    // TODO: 2020-08-09 23:42:38 二叉树的 前序 遍历
    // TODO: 2020-08-10 00:04:56 整理了 树 遍历的 相关知识点，并且 回忆、书写 完了 迭代的 dfs 模板；
    // TODO: 2020-08-10 00:13:28 递归的 dfs 模板 书写完毕


    // TODO: 2020-08-10 12:05:19 补充 官方题解 链接
    // https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode/


    /**
     * 关于 二叉树的 遍历 ----》两大类
     * <p>
     * todo 1、广度 优先搜索 BFS ：就是 层次顺序 遍历 的场景
     * --》就是 用到了 queue
     * <p>
     * <p>
     * todo 2、深度 优先搜索 DFS：有 前序遍历（根、左、右）、中序遍历（左、根、右）、后序遍历（左、右、根）几种 典型的 场景
     * --》就是 用到了 stack --》stack的 实现方式 有两种：
     * （1）递归调用 --》类似 模板，实现 非常简单
     * 前序：
     * void dfs(TreeNode root) {
     * visit(root);
     * dfs(root.left);
     * dfs(root.right);
     * }
     * <p>
     * 中序：
     * void dfs(TreeNode root) {
     * dfs(root.left);
     * visit(root);
     * dfs(root.right);
     * }
     * <p>
     * 后序：
     * void dfs(TreeNode root) {
     * dfs(root.left);
     * dfs(root.right);
     * visit(root);
     * }
     * <p>
     * <p>
     * （2）自己 new stack 来 迭代 实现 --》其实 也有一个 非常好的 模板
     * --》非常容易 实现 三种 dfs 遍历 --》颜色 标记法，或者说 是  类型 标记法
     * 如下实现：
     */

    /**
     * 方法1：
     * 迭代 实现 树的 dfs 的 通用模板，如下：
     * 时间：O(n)
     * 空间：O(height) --> 因为最多就是 一条边 一直到底，就是 树的高度
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        // 用 stack 来 迭代 实现 dfs --》前、中、后 序 都可以实现
        Stack<Object> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            // todo 注意 空指针！
            if (obj == null) {
                continue;
            }

            // 如果 是 某个值，就是 访问到了，需要入队
            if (obj instanceof Integer) {

                list.add((Integer) obj);

            } else {
                // 否则 就是一个结点，按照 需求 逆序 入队
                // 如果是前序的话，那就是 根左右 --》逆序后 就是 右 左 根 入队
                // 注意，因为 左、右 是根据 根 来 定位的，根的职责 已完毕，直接 入队 它的数值
                // todo --》 这里 只要根据 需求的 不同，修改顺序 就可以了 ！
                TreeNode node = (TreeNode) obj;
                stack.push(node.right);
                stack.push(node.left);
                stack.push(node.val);
            }

        }

        return list;
    }


    /**
     * 方法2：
     * 递归实现 dfs 的 方法
     * --》 也是 一个 模板
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    // 前、中、后 序的某种 dfs 遍历 --》 这里是 前序 遍历
    // 下面 也可以 按照 递归的 1234 来写，一样的
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        // todo 开始遍历 --》按需求 修改顺序 就可以了
        list.add(node.val);

        dfs(node.left, list);
        dfs(node.right, list);
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

