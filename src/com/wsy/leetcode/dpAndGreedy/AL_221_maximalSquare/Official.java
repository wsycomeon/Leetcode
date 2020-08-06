package com.wsy.leetcode.dpAndGreedy.AL_221_maximalSquare;

public class Official {


    /**
     * 求矩阵内 最大正方形 面积
     * 方法1:
     * 暴力穷举
     * 1、发现 元素 1之后，就该元素 作为 一个正方形的左上角
     * 2、在此坐标，根据 矩阵的行、列 计算出 正方形 可能的最大边长
     * 3、在这个 边长覆盖的范围内，寻找 只包含元素1的 最大正方形
     * 4、每次 新增一行 以及 新增一列，一旦发现不是1，说明之前的就是最大正方形了
     * 也不浪费性能！
     * <p>
     * <p>
     * 其中 m 和 n 是矩阵的行数和列数。
     * <p>
     * todo 4层 for 循环。。。。
     * 外两层 是 m * n
     * 内部找到 一个点后，对其可能的最大正方形 元素 进行遍历，
     * 正方形 最大边为 min(m,n), 那么 遍历 最多是 (min(m,n))^2
     * 整体的时间复杂度 就是：
     * m * n * min(m,n)^2
     * <p>
     * 时间复杂度：O(m * n * min(m,n)^2) ---》 很差劲了！
     * 空间复杂度：O(1)
     */
    public int maximalSquare(char[][] matrix) {
        // 最大边长
        int maxSide = 0;
        // 边界条件
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;


        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                // 发现一个 元素1 ，作为正方形的左上角
                if (matrix[i][j] == '1') {
                    // 此时 至少有一个 边长为 1 的正方形了，更新下
                    maxSide = Math.max(maxSide, 1);
                    // 计算 最大正方形 边长的上限（包含 当前坐标点）
                    int currentMaxSide = Math.min(rows - i, columns - j);

                    // 从（i，j）点 开始 逐渐 增加边长，判断 新增的一行一列 是否均为 1
                    for (int k = 1; k < currentMaxSide; k++) {

                        boolean flag = true;

                        // 先判断 增量由 k-1 变成 k 的 对边顶点 是否是 1
                        // 如果 不符合，说明 以当前顶点（i,j）为左上角的正方形 的所有情况 已测量完毕
                        // 直接 break
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }

                        // todo 由于 边长 增加 k-1 的正方形 , 之前 已确认，
                        // todo 现在 只需要确认 新增的一排、一列 是不是 都为 1 就可以了（二者的交点 上面验证过了）
                        for (int m = 0; m < k; m++) {
                            // 一次判断俩（排一位、列一位），只要有一个不符合的，也是break
                            // --》 注意，这里 break，只能 break 内循环，所以 flag 要刷新
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }

                        }

                        // 当前 k边长的 正方形 实验成功，可以 继续增长
                        if (flag) {
                            // 更新 最大边长，注意，k是增量，本身 边长 有 1！
                            maxSide = Math.max(maxSide, 1 + k);
                        } else {
                            // 增到k时，已经 不是正方形了，后面 没有必要了，直接 break --》当前的 (i,j)  无用了，换下一个
                            break;
                        }

                    }
                }

            }
        }

        // 返回 正方形 面积，即 边长 平方
        return maxSide * maxSide;
    }


    /**
     * 方法2：
     * 动态规划 登场！
     * 寻找 状态转移方程：
     * <p>
     * dp(i,j) 表示 以(i,j) 这个坐标为右下角元素的、内部值全是 1的最大正方形的边长
     * 如果 能找到 所有的dp，那dp的最大值，就是整体 最大正方形的边长，面积 也就出来了！
     * <p>
     * 所以，关键是 求所有点对应的 dp(i,j)
     * (1) 当前位置为 0 ，dp = 0
     * (2) 当前位置为 1 ，分情况
     * a、i和j 至少有一个为0（即在边界上），那 dp = 1
     * b、否则，转移方程：
     * 左边、相邻的 三个dp的最小值 + 1
     * dp(i,j) = min( dp(i−1,j) , dp(i−1,j−1) , dp(i,j−1) ) + 1
     */
    public int maximalSquare2(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }


        // 行数、列数
        int rows = matrix.length;
        int columns = matrix[0].length;

        // 所有点的 dp 默认为 0
        int[][] dp = new int[rows][columns];

        // 遍历所有的点，根据上面的分析，计算 dp值
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // 1 点
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // dp = 三者最小值 + 1
                        dp[i][j] = minValue(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    }

                    // 访问 每个 1点 后，都要 更新 max
                    maxSide = Math.max(maxSide, dp[i][j]);
                }

                // = 0 的，没必要 更新

            }
        }

        System.out.println("final maxSide = " + maxSide);
        return maxSide * maxSide;
    }

    /**
     * 求三者中的最小值
     */
    private int minValue(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
