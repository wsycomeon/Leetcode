package com.wsy.leetcode.tree.AL_103_zigzagLevelOrder;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class AL_103 {

    static TreeNode node1;

    static {
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(15);
        TreeNode node5 = null;
        TreeNode node4 = null;
        TreeNode node3 = new TreeNode(20);
        TreeNode node2 = new TreeNode(9);
        node1 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
    }


    public static void main(String[] args) {
        // 树的初始化，从1开始，从前往后  就好了 --》不像 链表 是逆序 初始化的！

        System.out.println(new Official().zigzagLevelOrder(node1));
        System.out.println(new Official().zigzagLevelOrder2(node1));
    }


}
