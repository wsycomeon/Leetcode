package com.wsy.leetcode.dpAndGreedy.offer.AL_08_climbStairs;

public class Official {


    /**
     * 爬楼梯，一次可以上 1、2、3阶
     * 总计 n阶，有多少种  走法：
     * i>=4的情况下，规律 就是
     * dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
     */
    public int waysToStep(int n) {

        if (n <= 2) {
            return n;
        }
        // 注意，不用0位，所以 承载 n个，要 n+1
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < n + 1; i++) {
            // TODO: 2020/7/9 注意，这里可能越界，需要 先取模 再说
//            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];

            //取模，对两个较大的数之和取模再对整体取模，防止越界（这里也是有讲究的）
            //假如对三个dp[i-n]都 % 1000000007，那么也是会出现越界情况（导致溢出变为负数的问题）
            //因为如果本来三个dp[i-n]都接近 1000000007 那么取模后仍然不变，但三个相加则溢出
            //但对两个较大的dp[i-n]:dp[i-2],dp[i-3]之和mod 1000000007，那么这两个较大的数相加大于 1000000007但又不溢出
            //取模后变成一个很小的数，与dp[i-1]相加也不溢出
            //所以取模操作也需要仔细分析

            dp[i] = (dp[i - 1] + (dp[i - 2] + dp[i - 3]) % 1000000007) % 1000000007;
        }

        return dp[n];
    }


    /**
     * 这个是 两步 楼梯
     * dp[i] = dp[i-1]+dp[i-2];
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];

            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }


}
