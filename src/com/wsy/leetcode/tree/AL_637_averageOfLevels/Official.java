package com.wsy.leetcode.tree.AL_637_averageOfLevels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-10 09:47:07 求一个 非空、二叉树 每层结点的平均值 组成的数组
    // TODO: 2020-08-10 09:50:27 那就是 BFS 广度优先搜索 遍历 所有的结点，每遍历一层 开始累加，到末尾 就平均值 就好了
    // 关键是 如何 区分 每一层的结点，暂时两个思路：
    // 1、双队列 queue1、 queue2，一个当前层，一个下一层， 每次遍历 queue1 的结点，将其非空的 子结点 加入queue2，--> queue1的结点累加完之后，/ size；然后 交换 queue1 queue2的指针即可，重复
    // 2、不需要双队列，直接 每一层 开始的时候，记录当前层的size，当前层还没遍历到的 count = size，然后poll一个，count--，直到0，求平均值，加入list；然后 开始下一层 即可
    // TODO: 2020-08-10 10:11:26 自己先写完了 两种方案


    // TODO: 2020-08-10 10:42:30 我曹，看完官方答案，一个dfs，一个bfs（就是 我那个双队列） ---> 我的一直报错！原来是因为 int值 相加 可能 越界，所以这里 要用 long 存 cum ！

    // TODO: 2020-08-10 11:02:29 整理完毕，一个 超级简单的题目。。。搞了 70分钟，我曹！

    /**
     * 方法1：BFS 双队列
     * <p>
     * 时间：O(n) --》所有结点 入队 被访问 一次
     * 空间：O(maxCount) --》单层 最多的 结点数
     */
    public List<Double> averageOfLevels1(TreeNode root) {
        // 题目 已说是 非空的树了，不用 初始 判空了

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);

        List<Double> list = new ArrayList<>();

        while (!queue1.isEmpty()) { // 还有 新一层的结点

            // 当前层 的个数
            int size = queue1.size();

            // 当前层 元素的累加和
            // TODO: 2020-08-10 10:40:40 我 草草草草，int 值 相加，可能越界，所以 这里要用 long！
            long sum = 0;

            // 计算 累加和
            while (!queue1.isEmpty()) {
                TreeNode node = queue1.poll();
                sum += node.val;

                // 把 非空的 子结点 入 queue2
                if (node.left != null) {
                    queue2.offer(node.left);
                }

                if (node.right != null) {
                    queue2.offer(node.right);
                }
            }

            // 计算 当前层的平均值，入队
            double v = sum * 1.0 / size;
            list.add(v);

            // queue1 全部遍历完之后，交换 queue1 queue2
            Queue<TreeNode> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        return list;
    }


    /**
     * 方法2：BFS 单队列
     * 和 上述一样的 时间、空间 复杂度
     */
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Double> list = new ArrayList<>();

        while (!queue.isEmpty()) { // 还有 新一层的结点
            int size = queue.size();

            // TODO: 2020-08-10 10:42:00 防 int 越界！
            long sum = 0;

            // 当前层 尚未 遍历的结点
            int count = size;

            while (count > 0) {
                // 取一个
                TreeNode node = queue.poll();
                count--;

                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // 当前层 遍历结束
            list.add(sum * 1.0 / size);
        }

        return list;
    }


    /**
     * 方法3：DFS，用 temp 双列表，存 每一层的 个数 和 累加值 --》最后 依次 求平均值 入 list 即可
     * <p>
     * 时间：O(n) --》 所有结点 遍历一次
     * 空间：O(height) --》额外空间占用有：
     * 两个 list（存 每一层 对应的sum和count，就是 height值），递归调用栈的深度 ，最多 也是 height --》todo 所以，就是 O(height)
     */
    public List<Double> averageOfLevels3(TreeNode root) {
        List<Long> sum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();

        dfs(root, 0, sum, count);

        // 返回 平均值 集合
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < count.size(); i++) {
            list.add(sum.get(i) * 1.0 / count.get(i));
        }
        return list;
    }

    // dfs 遍历，更新 每一层（index）的 sum 和 以及 count++
    private void dfs(TreeNode node, int index, List<Long> sum, List<Integer> count) {
        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理当前层
        if (index < count.size()) {
            // 说明 当前结点 所在的层，之前 已经记录过，直接累加更新
            sum.set(index, sum.get(index) + node.val);
            count.set(index, count.get(index) + 1);
        } else {
            // 否则，说明是 新的一层，直接 add 到 队尾 就好了 --> 不需要 指定 index
            sum.add((long) node.val);
            count.add(1);
        }

        // 3、递归
        dfs(node.left, index + 1, sum, count);
        dfs(node.right, index + 1, sum, count);

        // 4、善后

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