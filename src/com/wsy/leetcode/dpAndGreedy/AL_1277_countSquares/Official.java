package com.wsy.leetcode.dpAndGreedy.AL_1277_countSquares;

public class Official {


    /**
     * 计算 矩阵内 ---- 内部元素全为1的 正方形的个数
     * <p>
     * 类似于 题目：221 ---- 求矩形内 全部元素 全为1的 最大正方形的面积
     * 本题 一样用到了 动态规划。。
     * <p>
     * 并且！
     * 如果我们 定义 dp(i,j) 是 以 点 (i,j) 为右下角的 元素全为1的最大正方形的边长；
     * <p>
     * 那么，此时，有一个隐形的结论：
     * 想象一下！
     * todo dp(i,j) 同样是 以 (i,j)为右下角元素的正方形的个数
     * 比如 dp是5，最大边长是5，那么以这个点为右下角元素的正方形有几个？边长为 1、2、3、4、5 的5个正方形
     * 且，以各点为右下角元素的 正方形 也绝不会重复，
     * 那么，矩阵内 dp的 总和，也就是 正方形的个数。。。
     * <p>
     * 至于：
     * dp(i,j) = min( dp(i-1,j) , dp(i,j-1) , dp(i-1,j-1) ) + 1 的推论、状态转移方程，
     * 看下面的 画图、文字证明
     * https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-2/
     */
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // 行、列数
        int m = matrix.length;
        int n = matrix[0].length;
        // dp计算
        int[][] dp = new int[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // 元素 1
                if (matrix[i][j] == 1) {

                    // 非靠边元素
                    if (i != 0 && j != 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    } else {
                        dp[i][j] = 1;
                    }

                    // 加上 新的正方形的 个数
                    count += dp[i][j];
                }

            }
        }

        return count;
    }


}
