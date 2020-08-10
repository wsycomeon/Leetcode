package com.wsy.leetcode.tree.AL_637_averageOfLevels;

public class AL_637 {


    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     *
     *
     *
     * 示例 1：
     *
     * 输入：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 输出：[3, 14.5, 11]
     * 解释：
     * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
     *
     *
     * 提示：
     *
     * 节点值的范围在32位有符号整数范围内。
     */
    public static void main(String[] args) {

//        {2147483647,2147483647,2147483647}
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);

        System.out.println(new Official().averageOfLevels(root));
//        System.out.println(new Official().averageOfLevels3(root));


    }
}
