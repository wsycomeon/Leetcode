package com.wsy.leetcode.arrayAndMatrixAndSort.AL_547_findCircleNum;

import java.util.LinkedList;
import java.util.Queue;

public class Official {

    /**
     * 1、
     * 这个矩阵，可以看做是 图的邻接矩阵
     * 这个图 是 无向图
     * 那么 就可以用 深度优先搜索 或者 广度优先搜索，来找到 不连通的 顶点集合 的个数
     * 都要 依次遍历所有顶点，看其 之前有没有被访问过（连通过）；
     * 没有的话，count++
     */
    public int findCircleNum(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] visited = new int[arr.length];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {

            if (visited[i] == 0) { // 是新的、不连通的顶点集合
                count++;

                dfs(arr, visited, i);
            }
        }

        return count;
    }

    // 访问 其连通的所有顶点，实际上就是 visited 标记
    public void dfs(int[][] arr, int[] visited, int i) {

        for (int j = 0; j < arr.length; j++) {
            if (arr[i][j] == 1 && visited[j] == 0) { // 找到 和 该顶点 连通的、且未访问过的顶点，
                visited[j] = 1;

                // 深度遍历
                dfs(arr, visited, j);
            }

        }
    }


    /**
     * 2、上面的 bfs 系列 实现
     * 需要 Queue 辅助实现
     */
    public int findCircleNum2(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] visited = new int[arr.length];
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();

        // 也要遍历 所有顶点
        for (int i = 0; i < arr.length; i++) {

            // 发现一个新的之前 没被访问过的、即 和 之前的不连通的 顶点集合
            if (visited[i] == 0) {
                // 先加入queue
                queue.add(i);
                count++;

                while (!queue.isEmpty()) {
                    // 此时算作访问了
                    int s = queue.remove();
                    visited[s] = 1;

                    // 找到其 相邻顶点，入队
                    for (int j = 0; j < arr.length; j++) {
                        // 还得 没被访问过 才行
                        if (arr[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
            }

        }

        return count;
    }

    /**
     * 我曹！我曹！我曹！ 错误思想！
     * <p>
     * =================================================================================
     * 3、这个不能用 （沉岛算法 - 695 题）---》下面的思路 是错的！
     * <p>
     * 从前往后，依次遍历，一旦发现 不为0的点，这算是一个新的、连通集合
     * 即 算是一个岛把，就把周围的岛 都修改为0，让其沉没
     * 最后 返回 count 即可
     * --》todo 类似于 上面的深度优先搜索，但是 上面的dfs 使用了 visited 数组，做的剪枝； 这是是 直接沉岛
     * <p>
     * 当然了，这里的矩阵是正方形，和前面的 沉岛 有点区别（那是矩形）
     * =================================================================================
     * <p>
     * 因为，本身 这么写的话，会多计算很多，比如 这么一组数据
     * int[][] arr3 = {
     * {1, 0, 0, 1},
     * {0, 1, 1, 0},
     * {0, 1, 1, 1},
     * {1, 0, 1, 1}};
     *
     * todo 0 和 3 是连通的，但是一开始 0 计算后，
     * todo 3 如果按照这个规则，又被 重复 计算了！！！！！！！
     */
    public int findCircleNum3(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {

                if (arr[i][j] == 1) { // 说明 这是一个 新岛
                    count++;
                    // 沉没连通的岛
                    sinkIsland(arr, i, j);
                }
            }
        }

        return count;
    }

    /**
     * 这里，不计算 连通的岛的个数，不用返回值
     * 直接让他们沉没就行了
     */
    private void sinkIsland(int[][] arr, int i, int j) {
        // 1、 终止条件，越界，或者 不是 岛
        if (i < 0 || i >= arr.length || j < 0 || j >= arr.length || arr[i][j] == 0) {
            return;
        }

        // 2、处理当前层，沉没该岛
        arr[i][j] = 0;

        // 3、递归，当连通的岛都沉没
        sinkIsland(arr, i - 1, j);
        sinkIsland(arr, i + 1, j);
        sinkIsland(arr, i, j - 1);
        sinkIsland(arr, i, j + 1);

        // 4、善后处理
    }


}
