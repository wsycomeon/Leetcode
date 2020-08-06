package com.wsy.leetcode.dpAndGreedy.AL_120_minimumTotal;

import java.util.List;

public class Official {

    /**
     * 方法3：动态规划
     * https://leetcode-cn.com/problems/triangle/solution/javadong-tai-gui-hua-si-lu-yi-ji-dai-ma-shi-xian-b/
     * 由题意 可知，
     * 这是一个等边三角形，排数 和 最大列数 相等；
     * 将其 矩形化 就更为具象了，一行的最后一个元素的 下标是 (i , i)
     * <p>
     * 关键是：找到 状态转移方程：定义一个 和 坐标 相关联的 函数，
     * <p>
     * 比如，这里 明显是 f(i,j) 第i行、第j列，但是 这东西代表什么呢？
     * 我们要求得是什么？ --》从第一排到最后一排、经过路径上的值 之和 最小；
     * <p>
     * ---> 且 路径的走法 是有要求的，只能从 (i-1,j) (i-1,j-1) 走到 (i,j)
     * <p>
     * 可以说，我们要求的是，
     * f(最后一排，j) 中的最小值；
     * <p>
     * 那么，我们可以这么认为: 从上往下 走
     * f(i,j) 表示，从 （0,0）到 (i,j)点的 最短路径和
     * <p>
     * 则，常规情况下：
     * f(i,j) = min(f(i-1,j-1), f(i-1,j)) + a[i,j]
     * 特殊情况，比如
     * （1）第一列(j == 0)： f(i,j) = f(i-1,j) + a[i,j]
     * （2）最后一列(j == i)： f(i,j) = f(i-1,j-1) + a[i,j]
     * <p>
     * 这样的话，全部计算完之后，我们只用 求 最后一排的 f(i,j) 的最小值 就好了
     * <p>
     * <p>
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2) --> 用了一个dp 二维数组 来存放所有点的dp
     */
    public int minimumTotal3(List<List<Integer>> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }

        // 排数，和 最大列数
        int rows = lists.size();
        int columns = rows;
        // 用来存放 所有点 的 f值，即 dp值
        int[][] dps = new int[rows][columns];
        dps[0][0] = lists.get(0).get(0);

        // 从第一排开始 就好了
        for (int i = 1; i < rows; i++) {
            // 每一排的元素个数，就是 i+1
            for (int j = 0; j < i + 1; j++) {

                if (j == 0) {
                    dps[i][j] = dps[i - 1][j] + lists.get(i).get(j);
                } else if (j == i) {
                    dps[i][j] = dps[i - 1][j - 1] + lists.get(i).get(j);
                } else {

                    dps[i][j] = Math.min(dps[i - 1][j - 1], dps[i - 1][j]) + lists.get(i).get(j);
                }
            }
        }

        // TODO: 2020/6/26 干！你要是想 两两比较 求最小值，一开始 就该设置一个 最大值！

        int min = Integer.MAX_VALUE;

        // 计算最后一行的最小值
        for (int j = 0; j < columns; j++) {
            min = Math.min(min, dps[rows - 1][j]);
        }

        return min;
    }

    /**
     * 上述，自顶而下的 动态规划的、空间优化版本
     * <p>
     * --》最后一行的dp值，是 需要保存
     * 但是 计算过程中 dp[i][j] 只需要 上面的两个变量 就够了。
     * dp[i-1][j-1] dp[i-1][j]，所以我们用俩 临时变量 保存他们即可；
     * <p>
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n)
     */
    public int minimumTotal3_2(List<List<Integer>> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }

        // 排数，和 最大列数
        int rows = lists.size();
        int columns = rows;

        // 用来 临时存放 上一行 所有点 的 f值，即 dp值 --》length 取最大
        int[] dps = new int[columns];

        dps[0] = lists.get(0).get(0);

        // 正上面的点的dp
        int topDp = 0;
        // 左上角 的点的dp
        int leftTopDp = 0;

        // 从第一排开始 就好了
        for (int i = 1; i < rows; i++) {
            // 每一排的元素个数，就是 i+1
            for (int j = 0; j < i + 1; j++) {
                // 取出 正上方的 dp值
                topDp = dps[j];

                // 更新j位置的
                if (j == 0) {
                    dps[j] = topDp + lists.get(i).get(j);
                } else if (j == i) {
                    dps[j] = leftTopDp + lists.get(i).get(j);
                } else {
                    dps[j] = Math.min(leftTopDp, topDp) + lists.get(i).get(j);
                }

                // 下一个 j++，那么 之前的j的上方 就是 其左上了
                leftTopDp = topDp;
            }
        }

        int min = Integer.MAX_VALUE;

        // 计算最后一行的最小值
        for (int j = 0; j < columns; j++) {
            min = Math.min(min, dps[j]);
        }

        return min;
    }


    /**
     * 方法4：
     * 依然是动态规划，而且思路 和 上面基本一致
     * 不同点，在于，上面的思路是 从上至下；
     * <p>
     * 而 本方法，则是 从下至上；即 从最后一排的所有点 --》走到 第一排(只有一个点)
     * <p>
     * 此时的 dp[i][j] 则表示了，从 最后一排的下面，走到 当前 [i,j] 点的最短路径；
     * 那么，dp[0][0] 就是我们所求的值；
     * <p>
     * [i,j] 只能由下一排的 [i+1,j] [i+1,j+1] 过来，
     * 所以，常规情况下：
     * dp[i,j] = min( dp[i+1,j] , dp[i+1][j+1] ) + a[i][j] ;
     * <p>
     * 没有特殊情况 ！！！
     * 但是 为了 最后一排的计算方便：
     * 我们 申请的dp数组的 多 一排、一列，这样的话，最后一排的计算 就很好处理了
     * <p>
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2)
     */
    public int minimumTotal4(List<List<Integer>> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }

        int rows = lists.size();
        int columns = rows;

        int[][] dps = new int[rows + 1][columns + 1];

        // 从最后一排开始遍历 即可
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                dps[i][j] = Math.min(dps[i + 1][j], dps[i + 1][j + 1]) + lists.get(i).get(j);
            }
        }

        return dps[0][0];
    }

    /**
     * 上面的空间优化版本
     * 从小往上遍历的时候，实际上 只需要 知道 下一行 点的 dp值，就够了
     * 不像 从上到下的时候，保存的时候，会 先覆盖掉 上一层记录，需要 俩 额外的变量 记录 正上方 和 左上方的 dp
     * 当然了，一维数组 存dp的话，为了 最后一行的特殊处理
     * 多申请 一列 ！
     * <p>
     * <p>
     * <p>
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n)
     */
    public int minimumTotal4_2(List<List<Integer>> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }

        int rows = lists.size();
        int[] dps = new int[rows + 1];

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                // 因为 我们用到的是 正下方 和 右下方的dp值，本次覆盖 不会影响 下一个 j
                dps[j] = Math.min(dps[j], dps[j + 1]) + lists.get(i).get(j);
            }
        }

        return dps[0];
    }


    private int minPath;
    private int currentPath;

    /**
     * 方法1：
     * 直接 dfs 递归 来做
     * 空间复杂度 可能略高，需要 后面 用 迭代优化
     * todo ---> 好吧。。。。运行时间 直接超了。。。坑爹。。不能这么搞
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        minPath = Integer.MAX_VALUE;
        currentPath = 0;

        dfs(triangle, 0, triangle.size(), 0);

        return minPath;
    }

    private void dfs(List<List<Integer>> lists, int floor, int maxFloor, int startIndex) {
        System.out.println("floor = " + floor + " , maxFloor = " + maxFloor + " , startIndex = "
                + startIndex + " , currentPath = " + currentPath + " , minPath = " + minPath);

        // 1、终止条件
        if (floor >= maxFloor) {
            return;
        }

        // 2、处理当前层
        List<Integer> array = lists.get(floor);

        boolean lastFloor = floor == maxFloor - 1;

        for (int i = startIndex; i < Math.min(array.size(), startIndex + 2); i++) {
            Integer path = array.get(i);
            // 若以当前为路径点
            currentPath += path;

            // 3、递归 找 下面的路径 todo ，下一层的 startIndex 应该是 i
            dfs(lists, floor + 1, maxFloor, i);

            // 4、善后
            if (lastFloor) {

                System.out.println("最后一层了---》此时的 currentPath = " + currentPath + " , minPath = " + minPath);

                minPath = Math.min(minPath, currentPath);
            }

            // 去除 当前路径点，尝试下一个
            currentPath -= path;
        }


    }

    /**
     * 方法2：
     * 递归 迭代化
     * <p>
     * todo 不会写，我曹
     */
    public int minimumTotal2(List<List<Integer>> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }
        return 0;
    }


}
