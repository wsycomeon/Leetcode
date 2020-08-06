package com.wsy.leetcode.tree.AL_103_zigzagLevelOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Official {


    /**
     * 锯齿形 遍历 二叉树的结点
     * <p>
     * 要求返回的结果 是 每一层 一个list（正序、逆序 来回交替的）
     * <p>
     * 方法1：BFS 配合 每层的 双端队列
     * 遍历 左、右子节点时
     * 想正序的一层 直接 插到尾部；
     * 想逆序的一层 直接 插到头部
     * <p>
     * todo 该题 主要的难点 就是： 不知道 怎么 判定 一层 遍历 结束 ---》这里很巧妙，一开始的 root后 就加了一个null --》即 每一层用 null 表示 该层结束，这样的话，需要保证 树的 null子结点 不能入队！
     * <p>
     * 擦擦擦！！！
     * 注意，不要想当然，
     * <p>
     * 这题 每一层结果的list 确实 需要 双端队列，因为 最终输出的每一层 有正序、有逆序，
     * 当我们从上往下 遍历每个节点时，
     * 根据 当前层的顺序，就可以将 每一个节点 按照要求 插入 头 或者 尾！
     * <p>
     * 至于，BFS 用的 LinkedList 就是一个队列，只是恰好是 双端队列 罢了
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        // 边界条件！
        if (root == null) {
            return results;
        }


        // todo 默认正序（从左到右），主要难点 就是 怎么判定一层到头了，好调整 遍历顺序！
        boolean left_to_right = true;


        // queue 会将树中 所有的非空元素 过一遍（且 每层 最后 加一个 null 表示 结束）
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        // 添加根元素 以启动BFS循环
        queue.addLast(root);
        // 添加 一层的终止符 --> 这里 null 用作一层的终止符，所以 子节点是null的时候，不要add进去！
        queue.addLast(null);


        // TODO: 2020/6/23 LinkedList 实现了 list 和 deque的接口 ！且 支持 null 元素

        // 存放 每一层 结果的list，换层 就要 new 新的！
        LinkedList<Integer> oneLevelList = new LinkedList<Integer>();


        while (queue.size() > 0) {
            // 每次 从双端队列的头部 开始读
            TreeNode current = queue.pollFirst();

            if (current != null) {
                // 表示 层数没有切换

                // 依据当前层级的flag，将自身 插入 头 或者 尾
                if (left_to_right)
                    oneLevelList.addLast(current.val);
                else
                    oneLevelList.addFirst(current.val);

                // 把该节点 自己的、非空（null 作为 层的终止符了！） 子结点 加入 BFS 遍历
                if (current.left != null)
                    queue.addLast(current.left);

                if (current.right != null)
                    queue.addLast(current.right);

            } else {
                // 遇到 结尾符号 null，否则，表示一层结束
                // 该切换 层级了，level 结束，new 新的
                results.add(oneLevelList);
                oneLevelList = new LinkedList<Integer>();

                // todo 如果当前 queue（下一层） 还有剩余元素，需要 加 null，给 下一层 加上终止符
                if (queue.size() > 0)
                    queue.addLast(null);

                // 顺序flag 对调！
                left_to_right = !left_to_right;
            }

        }


        // BFS 遍历结束，返回 层级的list（每层 顺序、逆序 交替） 即可
        return results;
    }


    /**
     * 上面的BFS 只是实现了 锯齿形的输出，但是 实际上 还是 顺序遍历的
     * <p>
     * 方法2：DFS
     * em 本方法 也只是 锯齿形输出。。。
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();

        if (root == null) {
            return levels;
        }

        dfs(root, 0, levels);

        return levels;
    }

    /**
     * 根据当前节点的层级 处理 当前节点（找到其合适的层级list，将其按照合适的顺序 插入），并递归处理 其子节点
     *
     * @param node   当前节点
     * @param level  所在层级
     * @param levels 最终结果集合，每层list的 集合
     */
    protected void dfs(TreeNode node, int level, List<List<Integer>> levels) {

        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理当前层

        // --》根据 自身层级 和 已统计层级的大小比较，做处理
        // level >= 已统计层级的个数，表示 现在是 到达 新的一层
        if (level >= levels.size()) {
            // new一层，且 将当前节点（每一层的 起点） 加入 当前层的list
            LinkedList<Integer> newLevel = new LinkedList<Integer>();
            newLevel.add(node.val);
            // 加入 层级list的集合
            levels.add(newLevel);
        } else {
            // 没有增加 新的层级
            if (level % 2 == 0) // 当前层是 偶数层 --》顺序遍历，所以 加入尾部
                // 获取自己对应层的list
                levels.get(level).add(node.val);
                // todo --》其实这里可以强转的
//            ((LinkedList) levels.get(level)).addLast(node.val);
            else
                // 奇数层，逆序加入
                levels.get(level).add(0, node.val);
//            ((LinkedList) levels.get(level)).addFirst(node.val);
        }

        // 3、递归 处理 左、右子节点

        dfs(node.left, level + 1, levels);
        dfs(node.right, level + 1, levels);

        // 4、善后。没有合并、排序之类的操作，不处理

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