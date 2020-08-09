package com.wsy.leetcode.tree.AL_94_inorderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Official {

    // TODO: 2020-08-10 00:27:04 树的 dfs 中序遍历
    // TODO: 2020-08-10 00:32:50 按照 两套 模板 写完。。。

    /**
     * 方法1：
     * 迭代 实现 dfs 模板
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Stack<Object> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj == null) {
                continue;
            }

            if (obj instanceof Integer) {
                list.add((Integer) obj);
            } else {
                TreeNode node = (TreeNode) obj;
                // todo 中序遍历，即 左 根 右 ，则 逆序： 右 根 左
                stack.push(node.right);
                stack.push(node.val);
                stack.push(node.left);
            }

        }

        return list;
    }

    /**
     * 方法2：
     * 递归 实现 dfs 模板
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        // todo 中序遍历，即 左 根 右
        dfs(node.left, list);

        list.add(node.val);

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
