package com.wsy.nowcoder.huawei.AL_61;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 题目描述
     *
     * 把M个同样的苹果放在N个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？（用K表示）5，1，1和1，5，1 是同一种分法。
     *
     *
     * 输入
     *
     * 每个用例包含二个整数M和N。0<=m<=10，1<=n<=10。
     *
     *
     * 样例输入
     *
     * 7 3
     *
     *
     * 样例输出
     *
     * 8
     *
     *
     输入描述:
     输入两个int整数

     输出描述:
     输出结果，int型

     示例1
     输入
     复制
     7 3
     输出
     复制
     8
     */

    /**
     * 求 m个苹果，放到 n个盘子的 放置方法数
     * <p>
     * --》
     * dp[i][j] --> 表示，将 i个苹果，放入了 j个盘子 的 放置方法数
     * <p>
     * <p>
     * todo 思路1：错误！
     * 那到 第i个 苹果时，
     * 要么 放入 已有的盘子，
     * 要么，放入一个 新的盘子
     * 求二者的最大值
     * 即 dp[i][j] = Math.max( dp[i-1][j] , dp[i-1][j-1])
     * <p>
     * <p>
     * todo 思路2：正确！
     * 1、当 i < j 时，说明 苹果少
     * 必然 至少有 j-i 个 空盘，且其 方法数 和 i,i 是一样的，增加 空盘，不会增加 方法数
     * dp[i][j] = dp[i][i]
     * <p>
     * 2、当 i >= j 时，说明 苹果多；
     * 两大类 放置 方法：
     * （1）没有空盘，即每个盘子 都有苹果，那就和 i-j , j 是一个效果了
     * （2）有空盘，至少 有一个盘子 确定为空，即最多 j-1个盘子，放置苹果，和 i,j-1 是一个效果 --》这里的方法，肯定 还有 其他空盘！
     * <p>
     * dp[i][j] = dp[i-j][j] + dp[i][j-1]
     * <p>
     * 边界值
     * i=1 , j=1, i=0 时 dp = 1;
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int apple = scanner.nextInt();
            int disk = scanner.nextInt();
            getMaxMethod(apple, disk);
        }

    }

    private static void getMaxMethod(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        // TODO: 2020-08-20 18:38:35 边界条件 不能丢！
        // 0个苹果，方法恒为1
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 1;
        }

        // 0个盘子，不可能，方法数 为 0，不用赋值

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {

                if (i < j) {
                    // 苹果少
                    dp[i][j] = dp[i][i];

                } else {
                    // 苹果多

                    // 1、无空盘的方法数
                    int sum = dp[i - j][j];

                    // 2、有空盘的方法数
                    // --> 至少有一个盘子 是 无用的，加这一个 空盘子 对 方法数 没有影响，那就是 和 [i,j-1] 一样的数值了
                    // TODO: 2020-08-20 18:44:15 这里，不容易 转过弯 来 --》好好理解！

                    sum += dp[i][j - 1];

                    dp[i][j] = sum;
                }

            }
        }

        System.out.println(dp[m][n]);
    }

}
