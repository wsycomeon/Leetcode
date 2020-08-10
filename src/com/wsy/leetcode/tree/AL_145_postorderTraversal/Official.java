package com.wsy.leetcode.tree.AL_145_postorderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Official {


    // TODO: 2020-08-10 00:18:26 后序遍历
    // TODO: 2020-08-10 00:24:42 两种 模板 书写完毕

    // TODO: 2020-08-10 12:05:31 补充 官方题解 链接
    // https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode/




    /**
     * 方法1：
     * 迭代 实现 dfs 模板
     */
    public List<Integer> postorderTraversal(TreeNode root) {
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
                // todo --> 后序遍历，就是 左 右 根 --》根 右 左 逆序入队
                stack.push(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }

        }

        return list;
    }


    /**
     * 方法2：递归 实现 dfs 模板
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        // 后序 遍历 --》左、右、根
        dfs(node.left, list);
        dfs(node.right, list);

        list.add(node.val);
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