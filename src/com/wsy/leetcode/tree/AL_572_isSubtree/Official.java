package com.wsy.leetcode.tree.AL_572_isSubtree;

import java.util.LinkedList;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-09 09:32:07 求 b 是否是 a 的一颗子树
    // TODO: 2020-08-09 09:36:31 理论上来说，广度遍历 a 每一个结点，然后 以 该节点作为子树 c 匹配 b 是否完全相同（也是广度遍历 即可）
    //  --》这算暴力枚举，时间复杂度是 O(M*N) --> a 的结点数 * b 的结点数

    // TODO: 2020-08-09 09:57:56 看完官方题解，有点骚气 --》3个方法
    // todo https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/
    // 1、最简单的就是暴力匹配了，不过，官方这里用的 dfs + dfs 来 匹配，不是我打算用的 bfs + bfs
    // 2、用 前序遍历，将 每一棵树 都映射成 一个子序列，要有 leftNull 和 rightNull 之分，然后 KMP算法（某种 字符串、序列 匹配算法） 匹配，a的序列 是否 包含 b的序列
    // 3、将 每个子树 映射成 一个数值，然后判断 b的hash值 a的map中 是否存在--》主要是 这个hash函数的唯一性设计
    // 考虑到的要素有：当前结点的val、左、右子树的hash值、子树大小（用的 求素数。。。）、子树的不同权值 等 --》骚气
    // TODO: 2020-08-09 10:16:47 看了下 官方 视频题解，就 粗略的 讲了下 前两个方法 ，第三个压根儿没讲，没意思

    // TODO: 2020-08-09 11:15:18 暴力枚举 --》自己 写了一个小时
    //  --》 DFS（容易 写，也容易 理解！） 和 BFS（巨难写。。效率 还差）

    // TODO: 2020-08-09 11:19:56 算了，另外 两个 高深的算法，暂时 不浪费时间了 --》估计 要求 写出 暴力枚举 就够了
    // 将 子树 唯一性的 映射出一个序列 或者 映射成 一个 hash 数值！


    /**
     * 方法1：暴力枚举
     * dfs 算法：
     * 遍历 s上的 每个子树，看 是否 和 t 相等
     * 此时 --》遍历算法 用 dfs，匹配算法 也用 dfs
     * <p>
     * 时间：O(S * T) --》S上的 每个子树 都要匹配一次，一次最多 匹配T个
     * todo 空间：O( max(sHeight , tHeight) ) --》em，待验证
     * <p>
     * <p>
     * --》因为，我们在任意时刻 匹配的时候，实际上 也就是 两棵树的匹配，每一棵树 都可能先匹配完，--》栈 就结束
     * 比如 s的子树 先匹配完了，此时的 s的栈的深度 就是 s当前 某个方向的高度，不能超过 sHeight；
     * 如果 t的子树 先匹配完了，那就是 s的 还没匹配完，那就更小于 sHeight了 --》所以 最多是 sHeight把 ？？？
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // 1、终止条件
        // 判断 t 是否是 s 的子树 --》首先 空判断
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        // 当前 root 都不为 null，就可以正常判断了

        // 2、处理 当前层
        return isSameTreeDFS(s, t) // (1)、要么 t 和 当前的子树 s 完全相等，符合条件
                // 3、递归
                || isSubtree(s.left, t) // (2)、或者，左子树 有 t 也行
                || isSubtree(s.right, t); // (3)、或者，右子树 有 t 也行
    }

    /**
     * 判断 两课树 是否 完全相等
     * 当前 值 相等，并且 左、右子树  也得 完全相等
     */
    private boolean isSameTreeDFS(TreeNode s, TreeNode t) {
        // 1、终止条件
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        // 2、处理 当前层
        return s.val == t.val
                // 3、递归
                && isSameTreeDFS(s.left, t.left)
                && isSameTreeDFS(s.right, t.right);
    }

    /**
     * 方法2：暴力枚举
     * bfs 算法：
     * 遍历 s 上的 每个子树，看 是否 和 t 相等
     * 此时 --》遍历算法 用 bfs，匹配算法 也用 bfs
     * <p>
     * 时间：O(S * T) --> 一样，S个子树都要匹配，每次匹配 最多匹配 T个结点
     * 空间：O( max(S , T) ) --> 三个类型的 queue 的最大值
     * <p>
     * ---》todo 我勒个去，算法 巨难写。。效率 还差劲，哎 ！
     */
    public boolean isSubtree2(TreeNode s, TreeNode t) {

        // 1、边界条件
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        // 2、bfs 遍历 s 的子树 --》即 s 的每个 子结点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(s);

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        while (!queue.isEmpty()) {
            // poll 出一个结点，当做 一颗子树
            TreeNode node = queue.poll();

            // 当前子树 是否 和 t 完全相等，就是 true
            if (isSameTreeBFS(node, t, queue1, queue2)) {
                return true;
            }

            // 否则的话，看下 左、右结点 --> 判空！
            if (node != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return false;
    }

    /**
     * 也是 bfs 判断 两颗树 是否相等
     */
    private boolean isSameTreeBFS(TreeNode s, TreeNode t, Queue<TreeNode> queue1, Queue<TreeNode> queue2) {
        // 1、边界条件
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }


        // 2、bfs 判断
        queue1.clear();
        queue2.clear();

        queue1.offer(s);
        queue2.offer(t);

        // 队列 都有值的话
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            // poll的值 可能为 null，需要 判断！
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            // 当前值 不等，就是 false
            if (node1 != node2) {
                // 有一个为 null，就是 不相等
                if (node1 == null || node2 == null) {
                    return false;
                }

                // 都不为 null，值 不相等 也不行
                if (node1.val != node2.val) {
                    return false;
                }

            }


            // 当前 node 相等，看 子 node --》bfs 遍历

            if (node1 != null) {
                queue1.offer(node1.left);
                queue1.offer(node1.right);
            }

            if (node2 != null) {
                queue2.offer(node2.left);
                queue2.offer(node2.right);
            }

        }

        // 跳出循环后，如果 都为 null，也是相等的，否则 就是不等！
        return queue1.isEmpty() && queue2.isEmpty();
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
