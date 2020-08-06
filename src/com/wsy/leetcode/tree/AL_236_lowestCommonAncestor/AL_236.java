package com.wsy.leetcode.tree.AL_236_lowestCommonAncestor;

/**
 * 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class AL_236 {

    static TreeNode root;

    static TreeNode p1;
    static TreeNode q1;

    static TreeNode p2;
    static TreeNode q2;

    static TreeNode p3;
    static TreeNode q3;


    static TreeNode p4;
    static TreeNode q4;

    static {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);

        // todo！注意了，这里 压根 就没有结点，是空的 ，并不是 值为 null
        TreeNode node8 = null;
        TreeNode node9 = null;

        TreeNode node10 = new TreeNode(7);
        TreeNode node11 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;

        root = node1;

        p1 = node2;
        q1 = node3;

        p2 = node2;
        q2 = node11;

        p3 = node11;
        q3 = node6;

        p4 = node10;
        q4 = node6;
    }

    public static void main(String[] args) {

        System.out.println(new Official().lowestCommonAncestor(root, p1, q1).val);
        System.out.println(new Official().lowestCommonAncestor2(root, p1, q1).val);
        System.out.println(new Official().lowestCommonAncestor3(root, p1, q1).val);

        System.out.println();
        System.out.println(new Official().lowestCommonAncestor(root, p2, q2).val);
        System.out.println(new Official().lowestCommonAncestor2(root, p2, q2).val);
        System.out.println(new Official().lowestCommonAncestor3(root, p2, q2).val);

        System.out.println();
        System.out.println(new Official().lowestCommonAncestor(root, p3, q3).val);
        System.out.println(new Official().lowestCommonAncestor2(root, p3, q3).val);
        System.out.println(new Official().lowestCommonAncestor3(root, p3, q3).val);

   System.out.println();
        System.out.println(new Official().lowestCommonAncestor(root, p4, q4).val);
        System.out.println(new Official().lowestCommonAncestor2(root, p4, q4).val);
        System.out.println(new Official().lowestCommonAncestor3(root, p4, q4).val);


    }

}
