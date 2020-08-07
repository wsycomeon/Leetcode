package com.wsy.leetcode.graph.AL_210_findOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Official {


    // TODO: 2020-08-07 17:56:59 看完一遍，和 207 思路 完全一致，准备 直接 书写 代码
    // TODO: 2020-08-07 18:13:36 书写，整理完毕。。竟然要 17分钟 。。

    /**
     * 和 207 题目，要求、解法 完全一致，就是 返回值 不同
     * 207 要求，判断 是否 有 有效的 拓扑排序，没要 排序的结果 --》用个 count 计算 就可以了
     * 而 210 这里，要求 求出 拓扑排序 的结果
     * 且 如果 不存在的话，返回 一个 空数组 即可！
     * <p>
     * 方法1：
     * todo 还是 用 207 的 入度、广度优先搜索 方案 来求 --》稍微 修改下 即可
     * <p>
     * 时间：O(m+n) --》遍历 所有 顶点 和 边
     * 空间：O(m+n) --》额外空间有：邻接表（m+n）、入度表（n）、队列（最多n个，没有边，都是独立的顶点）、结果表（n）
     */
    public int[] findOrder(int n, int[][] prerequisites) {
        // 1、建立 邻接表 和 入度表
        int[] indegree = new int[n];

        List<List<Integer>> adj = new ArrayList<>();
        // 每个顶点 一个 链表
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // 2、填充 结果
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];

            indegree[edge[0]]++;
            adj.get(edge[1]).add(edge[0]);
        }


        // 3、广度 优先搜索
        Queue<Integer> queue = new LinkedList<>();
        // 初始值 入队！
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 最后 结果数组 --》顶点路径
        int[] result = new int[n];
        // 下一个 入队的位置
        int index = 0;

        // 4、开始遍历
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            // 入队
            result[index] = current;
            // 后移一位
            index++;

            // 层处理：邻接顶点的入度值 -1
            for (int i = 0; i < adj.get(current).size(); i++) {
                Integer neighbor = adj.get(current).get(i);
                indegree[neighbor]--;

                // 减到 0 --》 可以入队
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }

            }

        }

        // 5、判断 result 数组 填充到 哪儿了，如果 没填满 ，那就是 有环，没有完成 所有课程 ！
        if (index != n) {
            // 返回 空数组 即可
            return new int[0];
        }

        // 6、否则，全部学完了，返回 路径 即可 ！
        return result;
    }


}
