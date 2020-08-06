package com.wsy.leetcode.arrayAndMatrixAndSort.AL_240_searchMatrix;

public class Official {


    /**
     * 在一个 从左到右、从上到下 是递增的 矩阵中 ，查找 某个元素 是否存在
     * <p>
     * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-2/
     * <p>
     * 方法很多，只说典型的吧（最简单的、最优化的）
     * <p>
     * 方法1：
     * 暴力枚举
     * 逐层遍历 所有 元素，查看 是否存在 目标 ----》这样的话，矩阵的 单调递增性质。。。完全浪费了，且 性能低，O( m * n )
     * <p>
     * 方法2：
     * 利用 单调递增的矩阵特性 来快速 剪枝 --》控制 增大、缩小 都只能 固定一个方向
     * <p>
     * 从左下角 和 右上角 两个特殊的顶点 任选一个 作为 开始点：（以 右上角 为例）
     * 1、比较 元素值，相等的话，返回 true
     * 2、矩阵值 较小，i++ , 下移一行，放大；
     * 3、矩阵值 较大，j-- , 左移一列，缩小；
     * 直到 触碰边界 位置（搜索完毕）返回 false
     * <p>
     * 时间复杂度：O(m + n) --> TODO 效率 大大优化 --》因为 while循环内，i 是 0 --> m-1，j 是 n-1 --> 0，每次 只改变其中一个,所以，最多 m + n 步
     * 空间：O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 边界条件
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;

        // 取 起始点 的坐标为 右上角
        int i = 0;
        int j = column - 1;

        while (i < row && j >= 0) {
            // 比较 元素值
            int num = matrix[i][j];

            if (num == target) {
                // 相等，找到了
                return true;
            } else if (num < target) {
                // 目标值 较大，下移
                i++;
            } else {
                // 目标值 较小，左移
                j--;
            }

        }

        // 前面 没返回，就是没找到
        return false;
    }


}
