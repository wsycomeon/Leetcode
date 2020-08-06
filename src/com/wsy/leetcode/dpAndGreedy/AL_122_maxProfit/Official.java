package com.wsy.leetcode.dpAndGreedy.AL_122_maxProfit;

public class Official {


    /**
     * 这tm竟然是 简单 级别的题目！
     * <p>
     * <p>
     * 方法1：暴力 穷举
     * 递归法，从第0天 开始
     * <p>
     * 因为 每次递归内，当前层 双循环。。n^2
     * 双循环内 套 递归。。递归次数是 n(从 0天，递归到 n-1天)
     * 所以，时间复杂度是 (n^2)^n = n^2n
     * todo 即 O(n^n) 这可怕的 复杂度 ---------------》 直接 超出 时间的 限制了
     * 空间复杂度是 O(n) --> 递归 n 次导致。。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }


        return calculate(prices, 0);
    }

    /**
     * 给定 价格数组
     * <p>
     * 求出 从 index 这天 开始，后面 通过 交易 所能 获取的最大利润；
     * 1、先 选取一个 可以赚钱的交易（i买，j卖）
     * 2、然后 递归 求 j+1 这天 开始的最大值；
     * 3、比较 不同 i、j 对应的最大利润！
     */
    public int calculate(int prices[], int index) {

        // 1、终止条件
        if (index >= prices.length)
            return 0;

        // 2、处理当前层 ---》从 index 这天 开始，选出 i 天 和 更大的 j天，获取第一笔利润，然后从 j+1 递归一样操作，计算 其最大值。
        int max = 0;

        // 从 i 这天 开始 尝试买入
        for (int i = index; i < prices.length; i++) {

            for (int j = i + 1; j < prices.length; j++) {
                // 之后，在 大于 i 天价格的 某个 j天 第一次卖出；获取第一笔利润！
                if (prices[i] < prices[j]) {
                    // i-->j 的利润计算完毕，
                    int current = prices[j] - prices[i];

                    // todo  3、递归
                    // 计算 j+1 开始的 后续交易的最大利润，即当前 i 、j 选择下的 最大利润
                    int profit = current + calculate(prices, j + 1);

                    // 4、善后
                    // 如果 利润更大，更新结果
                    if (profit > max)
                        max = profit;
                }
            }

        }

        return max;
    }


    /**
     * 方法2：
     * 找出连续的谷底 和 峰值，计算 差值 累加即可
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // while 循环里面 用来 右移、获取数值 用的
        int i = 0;

        // 谷底值
        int valley = prices[0];
        // 峰值
        int peak = prices[0];

        // 最大利润，累加起来的
        int maxProfit = 0;

        // 后面用到了 i+1 < length
        // i 里面 一直 ++，所以 只用一轮遍历 就结束了。
        while (i < prices.length - 1) {
            // TODO: 2020/6/23 先求出 谷底

            // 下面 i++，所以这里还要 越界判断。price 价格 呈现下降趋势，右移 找到 谷底
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            // i 即 谷底
            valley = prices[i];

            // TODO: 2020/6/23 再求出 峰值！

            // 当前 price 呈现上升趋势，右移
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            // i 即 封顶
            peak = prices[i];

            // 计算 一次连续的谷底和峰值的差值，即 交易利润，累加上去
            maxProfit += peak - valley;

            // 继续去找 谷底。
        }

        return maxProfit;
    }

    /**
     * 方法3：
     * 也是方法2的思想，不过是 优化
     * 方法2 是说，我要找到 连续的 谷底、峰顶 分别 买入和卖出，实现 利润最大化
     * 但是呢。
     * 我们实际要求的 只是 最终结果 最大就好了
     * 即，我们只要能保证 上述结果 就行，
     * 通过 画图发现。。我在 谷底 买入，一直找到 最近的 峰顶 再卖出，
     * todo 在y轴上的差值 即 利润；实际上 就是 从谷底 到 峰顶，这一段上升过程的 几小段 差值的和，
     * <p>
     * 所以，从结果上来说，可以如下改写：
     * 即 只要是 上升的，我就 把差值 当做 利润，计算进去（当然 仅仅是计算，不能如此操作的！）
     * 降得 当然就是亏的，不做计算！
     * <p>
     * <p>
     * * 时间复杂度：O(n)
     * * 空间复杂度：O(1)
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxprofit = 0;

        // i - 1 >= 0 , 所以 从 1 开始
        for (int i = 1; i < prices.length; i++) {
            // 只要是 升的，就当做是 利润，累加起来 --》当然 实际 买、卖操作 所在的天 不是如此
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }

        return maxprofit;
    }


    /**
     * 方法4：
     * 自己 写的 搓实现，实际就是 方法2 --》找出 连续的 谷底 和 波峰，计算差值，不能漏掉！
     * <p>
     * 多次 买卖股票后的最大利润
     * <p>
     * <p>
     * * 时间复杂度：O(n)
     * * 空间复杂度：O(1)
     * ----
     * <p>
     * 画 线段区间 来考虑，不容易 漏掉 场景！
     */
    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // 当前 遍历到的 先低 后高点
        int min_val = Integer.MAX_VALUE;
        int max_val = 0;
        int sum = 0;

        for (int i = 0; i < prices.length; i++) {

            if (prices[i] < min_val && max_val == 0) {
                // 有更低的点，更新
                min_val = prices[i];
                max_val = 0;

            } else if (prices[i] > max_val) {
                // 自上次 最低点后，一直在升，刷新最高点
                max_val = prices[i];

            } else {
                // 否则说明，自上次最低点后，有高点，且已到最高点，当前开始下坡了，用之前的 数值计算
                sum += max_val - min_val;

                // 此时，之前的最低点废弃，重新刷新
                min_val = prices[i];
                max_val = 0;
            }


            if (i == prices.length - 1) {
                // todo 到末尾了，别 忘记计算
                sum += Math.max(0, max_val - min_val);
            }

        }

        return sum;
    }


}
