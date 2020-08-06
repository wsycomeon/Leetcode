package com.wsy.leetcode.tree.AL_113_pathSum;

import java.util.*;

public class Official {


    /**
     * 求路径和 为指定值的路径
     * 递归啊
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // 最终结果集合
        List<List<Integer>> result = new ArrayList<>();
        // 临时路径集合
        Stack<Integer> current = new Stack<>();
        // TODO: 2020/7/2 dfs 遍历， 回到上一层之前，要把 自己的 路径删掉
        dfs(root, sum, current, result);
        return result;
    }

    /**
     * 递归
     */
    private void dfs(TreeNode node, int lastValue, Stack<Integer> currentList, List<List<Integer>> result) {
        // 1、终止条件：到达 叶节点
        if (node == null) {
            return;
        }

        // 2、处理当前层
        int last = lastValue - node.val;

        // 当前是 叶节点，且数值合适
        if (last == 0 && node.left == null && node.right == null) {
            currentList.push(node.val);
            // 是一个合适结果，加入最终 结果集合
            result.add(new ArrayList<>(currentList));
            currentList.pop();
            return;
        }

        // 否则，不符合条件，继续往下找
        currentList.push(node.val);

        // 3、递归
        dfs(node.left, last, currentList, result);
        dfs(node.right, last, currentList, result);

        // 4、善后
        currentList.pop();
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