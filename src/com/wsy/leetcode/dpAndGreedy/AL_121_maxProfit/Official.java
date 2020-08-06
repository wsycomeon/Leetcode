package com.wsy.leetcode.dpAndGreedy.AL_121_maxProfit;

public class Official {


    /**
     * 题目说是 计算最大利润，实际上，这是一个 元素完全已知的数组
     * 本质上就是：
     * 从前往后，取两个数，求 （后面的数 - 前面的数） 的最大值；
     * 方法1：
     * 暴力枚举法
     * <p>
     * 从第一个数开始，依次取后面的值，减去当前的值，和 当前的最大值比较，如果更大，就更新
     * 两层循环即可
     * <p>
     * 时间 O(n^2)
     * 空间 O(1)
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int max = 0;

        for (int i = 0; i < prices.length; i++) {

            // j 必须在 i 的后面！
            for (int j = i + 1; j < prices.length; j++) {

                // 如果 差值 更大，就更新！
                max = Math.max(max, prices[j] - prices[i]);

            }
        }

        return max;
    }


    /**
     * 方法2：官方 解释有误！
     * 应该是说，一次遍历的过程中
     * <p>
     * 一边更新 最低点（如果 发现的话），
     * 另一边计算 最大差值（当前值 和 现在 能找到的最低点的差值  与 之前的最大差值比较）！
     * <p>
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int max = 0;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {

            // 发现更凹的点
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else {
                // 否则，就 计算 当前收益，和最大收益比较
                max = Math.max(max, prices[i] - minValue);
            }

        }

        return max;

    }


}
