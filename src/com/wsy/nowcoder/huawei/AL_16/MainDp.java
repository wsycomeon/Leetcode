package com.wsy.nowcoder.huawei.AL_16;

import java.util.Scanner;

public class MainDp {


    /**
     * https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&&tqId=21239&rp=1&ru=/ta/huawei&qru=/ta/huawei/question-ranking
     *
     * 0-1 背包问题，是经典的 动态规划 问题
     * --》要么 选，要么 不选
     *
     * 1、寻找 状态转移方程
     * dp[i][j] 的定义 是从 前i个物品中，取到 限制值/消耗值 j 时，能拿到的 最大 贡献值
     *
     * 限制值 可以是 重量，贡献值 可以是 金钱；
     * 限制值 还可以是 金钱，贡献值 是 满足度 等
     *
     * dp[前 i 个][限制值/消耗值] = 对应的 最大贡献值
     *
     * 则：dp[i][j] 就和 dp[i-1][x] 产生了关联：
     * （1）当 不选择 第 i 个物品时，最大贡献值 = 旧的最大贡献值 = dp[i-1][j]
     * （2）当 选中 第 i 个物品时，最大贡献值 = 旧的最大贡献值 + 当前的贡献值 = dp[i-1][ j-w[i] ] + v[i]
     *
     * 则 dp[i][j] 应该是 两种选择的最大值，
     * --》dp[i][j] = Math.max( dp[i-1][j] , dp[i-1][ j-w[i] ] + v[i] );
     * 这里的 限制值 / 消耗值 是 物品的重量 ， 贡献值 则是 物品的价值（价格）
     *
     * 而 dp数组 的 大小申请，一般是 [m+1] [maxWeight+1] --> 约定俗成的
     *
     * 且，上述 状态转移方程 要注意，
     * --》不能越界，即 当 j-w[i] >= 0 是 成立的；
     * 否则 不成立，即 当 w[i] > j ，就是 不可能 选择 当前物品的，那么 dp[i][j] = dp[i-1][j];
     *
     * 2、填充 二维 数组
     * 然后 双层 for 循环，填满 二维数组 即可；
     * 外循环：i = 1 ，i < m+1
     * 内循环：j = 1; j < maxWeight+1
     * 根据 上述 状态状态方程 不同条件 ，计算 填充 即可 ！
     *
     *
     * 3、返回 目标值
     * dp[m][maxWeight] ---> 即 在前m个物品中，最多消耗 maxWeight 的前提下，获得的 最大贡献值
     */

    /**
     * 上述的 0-1 背包，对于 每个物品，选择与否 只有 两个情况：--》选 与 不选，2种情况 的 最大值！
     * 实际上 可能还有 更复杂的，就是 本题 这样了
     * <p>
     * 0、初始化，读取 输入条件，封装成 bean
     * <p>
     * 1、寻找 状态转移方程
     * <p>
     * --》这里 将 附件 当做空气，不单独 选择，即 dp[i][j] = dp[i-1][j]
     * <p>
     * 而 物品 如果是 主件的话：最多俩附件的话（2x2=4），就不是 两种情况，而是 1 + 4 = 5种 情况：
     * <p>
     * 不选 --》dp[i-1][j]
     * 只选自己 --》dp[i-1][j - cost[i]] + cost[i]*imp[i]
     * 自己+附件1 --》dp[i-1][j - cost[i] - cost[附件1]] + cost[i]*imp[i] + cost[附件1]*imp[附件1]
     * 自己+附件2 --》dp[i-1][j - cost[i] - cost[附件2]] + cost[i]*imp[i] + cost[附件2]*imp[附件2]
     * 自己+附件1+附件2 --》dp[i-1][j - cost[i] - cost[附件1] - cost[附件2]] + cost[i]*imp[i] + cost[附件1]*imp[附件1] + cost[附件2]*imp[附件2]
     * <p>
     * 而此时的 dp[i][j] 应该是 上述 五种情况的 max值！
     * <p>
     * 此时的 状态转移方程为：
     * i 依旧表示 前 i 个 物品，j 表示 消耗值/限制值/(钱财) , dp 则是 最大贡献值 （所有 物品的价格*物品的重要度 的累加和）
     * <p>
     * dp[i][j] = Math.max(情况1、情况2、情况3、情况4、情况5)
     * <p>
     * --》依然，要注意  j-xxx 要成立，不越界，才能 计算 和 更新 dp
     * <p>
     * <p>
     * 2、依旧是 填充 二维数组 [m+1][momey+1]
     * <p>
     * <p>
     * 3、返回 dp[m][money]
     */

    static class Good {
        // 价格
        int v;
        // 重要度
        int p;
        // 主件的 id ，!= 0 才有效
        int q;

        // 附件1的id
        int a1;
        // 附件2的id
        int a2;

        public Good() {
        }

    }

    public static void main(String[] args) {
        // todo 1、初始化条件
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        String[] a = s.split(" ");

        // 先是，限制值 钱数 n
        int n = Integer.parseInt(a[0]);
        // m 个物品
        int m = Integer.parseInt(a[1]);

        // 异常情况
        if (m <= 0 || n <= 0) {
            System.out.println(0);
            return;
        }

        // 创建good数组，一样是 m+1个，为了方便 后面的读写
        Good[] goods = new Good[m + 1];
        // 先填充，防止 后面 空指针
        for (int i = 1; i < m + 1; i++) {
            goods[i] = new Good();
        }

        // 把数据 依次 读到数组
        for (int i = 1; i < m + 1; i++) {
            String[] a2 = scanner.nextLine().trim().split(" ");
            int v = Integer.parseInt(a2[0]);
            int p = Integer.parseInt(a2[1]);
            int q = Integer.parseInt(a2[2]);
            goods[i].v = v;
            goods[i].p = p;
            goods[i].q = q;

            // 如果 是 附件商品，填充 自身 index 到 所属主件 上去
            if (q != 0) {
                Good host = goods[q];

                if (host.a1 == 0) {
                    host.a1 = i;
                } else {
                    host.a2 = i;
                }
            }

        }

        // todo 2、动态规划 填充 dp数组
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            // 对于 第 i 个 物品的 5种 选择情况 --> 其实是 4种（不选择 当前物品的不用计算）
            int cost0 = 0; // 只 主件 的花费
            int cost1 = 0; // 主件 + 附件1
            int cost2 = 0; // 主件 + 附件2
            int cost3 = 0; // 主件 + 附件1 + 附件2

            int temdp0 = 0; // 只 主件 的 贡献值
            int temdp1 = 0;
            int temdp2 = 0;
            int temdp3 = 0;

            // 计算 这几种情况 用到的 消耗值 和 贡献值
            Good good = goods[i];
            cost0 = good.v;
            temdp0 = good.v * good.p;

            if (good.a1 != 0) {
                Good a1 = goods[good.a1];
                cost1 = cost0 + a1.v;
                temdp1 = temdp0 + a1.v * a1.p;
            }

            if (good.a2 != 0) {
                Good a2 = goods[good.a2];
                cost2 = cost0 + a2.v;
                temdp2 = temdp0 + a2.v * a2.p;
            }

            if (good.a1 != 0 && good.a2 != 0) {
                Good a1 = goods[good.a1];
                Good a2 = goods[good.a2];
                cost3 = cost0 + a1.v + a2.v;
                temdp3 = temdp0 + a1.v * a1.p + a2.v * a2.p;
            }

            for (int j = 1; j < n + 1; j++) {
                if (good.q > 0) {
                    // 当前是 附件，则 不考虑，即 dp值 不变
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 当前是 主件，则考虑 5种情况的最大值
                    // 1、不选 主件
                    int max = dp[i - 1][j];
                    // 2、只选 主件
                    if (cost0 <= j) {
                        max = Math.max(max, dp[i - 1][j - cost0] + temdp0);
                    }
                    // 3、主件 + 附件1
                    if (cost1 <= j) {
                        max = Math.max(max, dp[i - 1][j - cost1] + temdp1);
                    }
                    // 4、主件 + 附件2
                    if (cost2 <= j) {
                        max = Math.max(max, dp[i - 1][j - cost2] + temdp2);
                    }
                    // 5、主件 + 附件1 + 附件2
                    if (cost3 <= j) {
                        max = Math.max(max, dp[i - 1][j - cost3] + temdp3);
                    }

                    // 最终赋值
                    dp[i][j] = max;
                }
            }

        }


        // todo 3、返回 dp[m][n] --> 即为 最后结果
        System.out.println(dp[m][n]);

    }


}
