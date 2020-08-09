package com.wsy.leetcode.tree.AL_437_pathSum;

import java.util.HashMap;

public class Official {


    // TODO: 2020-08-08 18:31:20 求 树中 以任意结点为 起点的、向下的 路径和 = sum的 路径 个数 ！
    // TODO: 2020-08-08 18:55:40 自己只想到一个暴力枚举 --》没看到 官方的 方法，看到一个 前缀和的，看起来有点晕，先整理下把
    // TODO: 2020-08-08 19:46:28 是真的 不好理解啊。。。。还是 见识少啊。。。---》前缀和 思想！


    /**
     * 方法1：前缀和（其实就是 到达 本层 或者说 当前结点 之前的、方向向下的、任意结点 为起点的 路径之和） 思路
     * <p>
     * 是一个 hashMap，存的是，
     * 某个前缀和 或者说 路径长度 <---> 对应 已出现的 次数 count
     * <p>
     * <p>
     * https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
     * <p>
     * <p>
     * 具体做法：
     * 还有一个 curSum 值，表示 从root结点 到 当前结点之前（父结点） 的 path 和；
     * 假设 当前结点 为 B，到达 当前结点时，curSum + B.val 即 新的路径和 curSum
     * 新的路径和 curSum - 目标长度 target（即 题目的sum）就是 目标前缀和
     * <p>
     * --》从 map 看下 有几个 count ，即 之前 有几个结点 满足 如下要求：
     * 从那些个结点 开始 --》当前结点 就是一条符合的路径，
     * 将 count 累加起来
     * <p>
     * 因为当前结点的加入，还要更新 curSum 出现的次数 --》map
     * <p>
     * 然后 递归 看 左、右子结点的 符合的个数，再次 累加起来，
     * 之后 善后，因为 要 上去了，所以 要删除 本层的影响，map 要删除 当前 curSum 的 影响！
     * <p>
     * 时间：O(n)  --》所有结点 遍历 一次
     * 空间：O(n)  --》hashMap的容量，最多是 n。。。。待确认。。
     * --》其实最多是到 递归最深的一层，也就是 height，因为 没到一层，最多 新增一个key，也不对。。。新增key后，我们之后只是 -1 ，没有 remove 这个key。。
     * 所以 最多 还是 n 个吧
     */
    public int pathSum(TreeNode root, int sum) {
        // 初始化
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        // 前缀和 为0 的，记为 1 --》即 root 之前的
        prefixSumMap.put(0, 1);

        return recursionPathSum(root, prefixSumMap, sum, 0);
    }

    /**
     * 求 从 当前结点 node 开始的子树上
     * 满足 路径和 为 target 的 路径个数
     * todo 递归 标准写法
     */
    private int recursionPathSum(TreeNode node, HashMap<Integer, Integer> prefixSumMap, int target, int curSum) {
        // 1、 终止条件：
        if (node == null) {
            return 0;
        }

        // 2、处理 当前层（这里是 当前结点）
        int count = 0;

        // 先累加 当前结点的 path和
        curSum += node.val;

        // 再看下，从根节点（不一定是 起点）开始、方向向下、 到达 以 当前结点 为 终点的（路径方向 只有一条，就是 向下） 符合 条件的路径 现在 有几个，
        //todo 假设 这条方向、路径上 恰好 有 A 结点，其 前缀和 是 curSum - target（此时 不包含 A 结点），那么 A --》B 就是一条 符合要求的路径
        // 累加起来
        count += prefixSumMap.getOrDefault(curSum - target, 0);

        // 因为 当前路径上，当前结点的 加入，前缀和 map 要更新
        prefixSumMap.put(curSum, prefixSumMap.getOrDefault(curSum, 0) + 1);

        // 3、递归，求 子树上 满足的个数，累加 起来
        count += recursionPathSum(node.left, prefixSumMap, target, curSum);
        count += recursionPathSum(node.right, prefixSumMap, target, curSum);

        //todo 4、善后，因为 要回到 上一层了 --》或者说，路径向上 回溯了，我这个结点的 影响 需要消失。。
        // 所以 map里 要删除 当前结点的 影响，修改回去 ！
        prefixSumMap.put(curSum, prefixSumMap.getOrDefault(curSum, 0) - 1);

        return count;
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
