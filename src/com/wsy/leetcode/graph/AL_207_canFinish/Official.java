package com.wsy.leetcode.graph.AL_207_canFinish;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-07 16:18:01
    // TODO: 2020-08-07 16:35:50 看起来 这个图的表示形式 是 弧表示法。。。看不太懂，没啥思路。。看 官方题解 吧
    // TODO: 2020-08-07 17:07:59 em，大概看完了，但是两个问题：
    // 1、字节跳动 好像 不考 graph ！
    // 2、这题就是求 有先决、依赖关系的、 拓扑排序的，深度优先搜索 是一种逆向思维，显然 很难受，还是用 广度优先搜索（入度 = 0，入 队列）更好理解 ！
    // TODO: 2020-08-07 17:39:15 整理 完毕!


    /**
     * 其实 就是求 当前 有向图 有没有 有效的、拓扑排序 路径！
     * todo --》如果 有环 ，就没有 ； 无环 才有 ！
     * ---》还是 求拓扑排序 路径 那一套，这里，直接 使用 入度 为 0的、 广度优先 来写了 ！
     * --》拓扑排序的 结果 不是唯一的！
     * <p>
     * todo --》本题 不求路径，只求 有没有 拓扑路径 --》不用 记录路径，只要记录 求出的 能学习的count数 是否等于 总课程数 即可！
     * <p>
     * <p>
     * 方法1: 广度 优先搜索
     * <p>
     * 时间：O(n+m)  即 课程数 + 边数 --》广度优先、深度优先 搜索 基本都是 这个时间复杂度 --》全部访问一遍
     * 空间：O(n+m)  使用的额外空间 有： 邻接表（n+m），入度数组（n），队列（最多 n个，极端情况 --》没有边，都是 独立顶点，初始化 时 ，全入队！）
     */
    public boolean canFinish(int n, int[][] prerequisites) {

        // todo 题目 给的 先决条件，很蛋疼，需要 转换成 我们熟悉的 邻接矩阵 或者 邻接表 来处理 后续的计算 ！
        // --》这里 使用 邻接表 Adjacency List

        // 1、建表 --》因为 每个顶点的 边的 个数 不确定，所有 都用 list
        List<List<Integer>> adj = new ArrayList<>();
        // n个 顶点，每个 对应 一个链表
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // 2、计算 所有顶点 --> 顶点 取值 [0 , n-1] 的入度
        int[] indegree = new int[n];

        // 3、填充 邻接表 和 顶点 入度值
        for (int i = 0; i < prerequisites.length; i++) {
            // 里面的每个元素，都是一个 容量为2的数组，表示 一条边， 0 是 入点，1 是 出点
            int[] edge = prerequisites[i];

            // 0 的 入度值 ++
            indegree[edge[0]]++;
            // 1 的链表，加入 0
            adj.get(edge[1]).add(edge[0]);
        }

        // 前面 完成了，邻接表的初始化 和 入度值的计算，下面 可以进行 广度 优先遍历了
        Queue<Integer> queue = new LinkedList<>();
        // 4、 queue 填入 初始值，入度 = 0 的点
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // todo 如果 图中 存在环，则 组成环的 那几个顶点，他们的 入度 永远 减不到 0 --》就 永远 入不了 队
        // 从 队中 poll 出的顶点 是 已学习过的
        int visitedCount = 0;

        // 还有 现在 就可以学习的 课程（顶点）--》入度 = 0 的顶点 才能 进！
        while (!queue.isEmpty()) {

            // poll 出来一个
            Integer current = queue.poll();
            // 表明 可以学习 这个，count++
            visitedCount++;

            // 将 current 相邻顶点的 入度值 -1 --》处理 这一层
            for (int i = 0; i < adj.get(current).size(); i++) {
                Integer neighbor = adj.get(current).get(i);
                indegree[neighbor]--;

                // --后， 某个顶点 入度 = 0 ，可以 入队了！
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }

            }

        }


        return visitedCount == n;
    }


}
