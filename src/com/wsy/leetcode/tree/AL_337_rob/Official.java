package com.wsy.leetcode.tree.AL_337_rob;

import java.util.HashMap;

public class Official {


    // TODO: 2020-08-09 21:10:57 ---》求 所有 不直接相连的 结点的和的最大值
    // TODO: 2020-08-09 21:19:28 么啥思路，看起来 就是比较 两个集合、 各自和 的最大值 --》奇数层 结点、 偶数层 结点
    // dfs、或者 bfs 遍历完之后，比较 两个和 就好
    // TODO: 2020-08-09 21:39:02  --》哎，果然错了，这是一套 dp 题目，求 最优方案 --》方案 显然不是 我们上述说的 就两种！

    // TODO: 2020-08-09 22:30:19 整理完了，太难了，哎
    //  --》dfs 后续遍历，俩dp函数：任意结点 被选中、不被选中时，其作为 根结点的树上 被选中的 结点的最大权值和
    //  --》用 hashmap 存所有的、 或者 空间 优化后的，只存 当前node的 左、右子结点的 --》一对 数组


    /**
     * 方法1：求 不相连的 结点集合的 和 的最大值
     * https://leetcode-cn.com/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
     * 这是一套 最优解的 题目 --》dp
     * todo dp 问题转化：
     * 对于一颗二叉树，上面的 任意一个结点，就是 选 与 不选 两种情况，且 每个结点 都有一个权值
     * 在 不能 同时选中 父、子结点的前提下，能选中的 结点的 最大权值和 是多少？
     * <p>
     * 我们定义两个函数：left 、 right 代表 node 结点的左、右子结点
     * 1、include(node) 表示，选中 当前结点node的前提下，node结点为root的子树上，被选择的结点的 最大权值和；
     * 2、exclude(node) 表示，不选中 当前结点node的前提下，其子树上，被选择的结点的 最大权值和
     * <p>
     * 则 有如下 状态转移方程：
     * <p>
     * 1、include(node) = node.val + exclude(left) + exclude(right)
     * node 被转中，left、right 必然 不能被转中
     * <p>
     * 2、exclude(node) = Math.max( include(left) , exclude(left) ) + Math.max( include(right) , exclude(right) )
     * node 不被选中时。left、right 是否选中 是 不固定的，我们 要让 俩子结点的 贡献 最大，即 权值和 最大，所以 取 max 值
     * <p>
     * todo 可知：要想求 node 的 include 和 exclude ，只需要 知道 ；left、right 各自的include、exclude 即可
     * <p>
     * 方法1：
     * 直接用 俩 hashMap 来存 所有node 对应的 include 时的最大权值和、exclude 时的最大权值和
     * 而，node 依赖于 left、right ，所以 深度遍历，且是 后序遍历 --》 填充 俩 hashMap
     * 这样的话，最上面的结点 的权值和 最大； --》 故 最后返回 root 的 include 和 exclude 的 较大值 即可！
     * <p>
     * 时间：O(n) --》每个子结点 都被访问一次，计算 其 include、exclude
     * 空间：O(n) --》额外占用空间 有：递归调用栈的深度 O(height)、两个hashmap，都是 O(n) --》所以 最后是 O(n)
     */

    HashMap<TreeNode, Integer> include;
    HashMap<TreeNode, Integer> exclude;

    public int rob1(TreeNode root) {
        include = new HashMap<>();
        exclude = new HashMap<>();

        dfs(root);

        return Math.max(include.getOrDefault(root, 0), exclude.getOrDefault(root, 0));
    }

    // 深度 优先搜索、后序遍历 填充 俩 map
    private void dfs(TreeNode node) {
        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理当前层

        // 3、递归 --》这里是 后序遍历，所以 先递归
        dfs(node.left);
        dfs(node.right);

        // 4、善后
        // 当前结点 被选中时，其子树的 权值和 最大值
        include.put(node, node.val + exclude.getOrDefault(node.left, 0) + exclude.getOrDefault(node.right, 0));

        // 不被选中时，其 各子树 要 取其 最大值（选中、不选中）
        exclude.put(node,
                Math.max(include.getOrDefault(node.left, 0), exclude.getOrDefault(node.left, 0))
                        + Math.max(include.getOrDefault(node.right, 0), exclude.getOrDefault(node.right, 0)));
    }


    /**
     * 方法2：
     * 还是上述 dfs 后续遍历，计算 某个结点 include 和 exclude 时，其子树上 被转中的结点的 最大权值和 ！
     * 但是，根据 算法的 步骤分析 发现，
     * 求 node 的 include、exclude 函数。
     * 只需要 其 左、右子结点 俩结点的 include、exclude 就够了，并不需要 其他 所有结点的，所以 俩 hashmap 没必要，浪费空间；
     * 但是，dfs 函数 就得 改造了，需要 返回值了，返回 某个结点的 include、exclude的 子树的 被选中的权值和 --》一个二维数组 就可以了
     * todo int[] sums : 当前node 被选中时的 子树上 被选中的结点的 最大权值和，不被选中时。。。
     * 最后，还是 检查 root结点 的 include、exclude 即可；
     * <p>
     * 时间：O(n) --》还是 全访问一次
     * 空间：O(height) --》此时 额外空间，没有 hashmap 了，只有 递归调用栈的空间 height
     * --》每次递归时，只是需要 2个 2 int 数组即可，和 n无关，是 常数级。
     */
    public int rob(TreeNode root) {
        int[] sums = dfsSum(root);

        return Math.max(sums[0], sums[1]);
    }

    // 递归 计算 当前 结点的 include、exclude
    private int[] dfsSum(TreeNode node) {
        // 1、终止条件
        if (node == null) {
            return new int[]{0, 0};
        }

        // 2、处理当前层
        // 3、递归
        int[] leftSum = dfsSum(node.left);
        int[] rightSum = dfsSum(node.right);

        // 4、善后
        int selectedSum = node.val + leftSum[1] + rightSum[1];
        int notSelectedSum = Math.max(leftSum[0], leftSum[1]) + Math.max(rightSum[0], rightSum[1]);

        return new int[]{selectedSum, notSelectedSum};
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
