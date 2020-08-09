package com.wsy.leetcode.tree.AL_112_hasPathSum;

import java.util.LinkedList;
import java.util.Queue;

public class Official {


    // TODO: 2020-08-08 17:36:31 2分钟 大概有思路，dfs 深度遍历，每次将 sum - 当前结点的值
    // TODO: 2020-08-08 17:45:06 自己先写一个
    // TODO: 2020-08-08 17:54:15 和 官方答案的 方法2 递归一致，不过 方法 1 相对来说 是更通用的方案：BFS 广度优先搜索
    // TODO: 2020-08-08 18:21:00 搞定


    /**
     * 求 从 根结点 到 某个叶子结点的 路径上 数值之和 = 目标值 是否成立
     * <p>
     * todo 其实 一定是 要遍历到 某个叶子结点的，然后 判断 是否等于 sum 的，区别 无非是：
     * 1、实现方式：深度 还是 广度 遍历
     * 2、是要 累加 求sum ，还是 累减 求 = 0 ！
     * <p>
     * <p>
     * 方法 1：
     * DFS 递归实现
     * 从 当前结点 到 叶子结点 满足 和为 sum；可以转化为，从其 某个子结点（左、右 任意一个） 到 叶子结点的 和 为 sum-val 即可
     * <p>
     * 时间：O(n) --》所有 结点 都要访问一遍
     * todo 空间：O(height) ---》主要是 函数 递归调用栈的深度，这个 和 树的深度 一样 --》最坏情况下 就是 单链表 那就是 O(n)，平均情况下 就是 O(logn)
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        // todo 注意！---》 根据 题意，null 结点 没有数值，永远是 false！
        if (root == null) {
            return false;
        }

        // 否则，去除 当前节点的值
        sum -= root.val;

        if (root.left == null && root.right == null) {
            // 当前是 叶子结点，看下 sum 是否 = 0，等于 0 ，说明这条路径 ok，否则 false，递归上去，看下 其他路径
            return sum == 0;
        }


        // 不是 叶子结点，看下 此时的 左、右子树 有一个符合 就可以
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * 方法2：广度 优先搜索 --》一般用 一个 queue 维护 待访问的结点
     * 而本题，访问到一个结点 ，就 记录下 从 根结点 到 它的sum和；所以 此时 用 两个 queue 实现 ！
     * <p>
     * poll 出来的时候，看下 是否到 叶子结点了，
     * （1）如果是的话，比较下 它的值 是不是 sum
     * --》是的话，true; 不是的话，说明 这条 路径 不行，continue
     * （2）否则，分别 入队 左、右子结点
     * <p>
     * 前面 没返回，那就是 false！
     * <p>
     * 时间：O(n) while 循环 所有 结点 都访问一次
     * 空间：O(n) 主要是 queue 的占用，最多 n个 全部入队！
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueValue = new LinkedList<>();
        // 1、初始值 入队
        queueNode.offer(root);
        queueValue.offer(root.val);

        // 还有 没处理过的 结点
        while (!queueNode.isEmpty()) {
            // 2、 处理 当前结点
            TreeNode node = queueNode.poll();
            // TODO: 2020-08-08 18:11:11 注意 --》这里是判断 到达 这个结点的 路径和 是否 等于 sum，不是 当前 结点的值！
            Integer currentSum = queueValue.poll();


            // 3、到达 某个叶子结点，开始判断
            if (node.left == null && node.right == null) {

                if (currentSum == sum) {
                    return true;
                }

                // 否则，此路不通，换下一个结点
                continue;
            }

            // 4、不是 叶子结点，处理 左、右子结点
            if (node.left != null) {
                queueNode.offer(node.left);
                queueValue.offer(currentSum + node.left.val);
            }

            if (node.right != null) {
                queueNode.offer(node.right);
                queueValue.offer(currentSum + node.right.val);
            }

        }

        // 5、没找到 返回，就是 false！
        return false;
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