package com.wsy.leetcode.arrayAndMatrixAndSort.AL_60_getPermutation;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Official {

    /**
     * 这个 思路其实也简单，就是 用排列组合个数 来求
     * <p>
     * todo 但是，用代码实现，反而不好搞 ！
     * 比如，我知道 13 - 6x2 = 1 ，即 前两个数的排列组合不够，当前层 得选 第三个数，
     * 但是怎么去用代码 来判断 选的是第三数呢？
     * 实际上 就是 一直  -= 6。。。
     * 毕竟 计算机 不能理解我们的高级的、跳跃的 思想，
     * 需要 用基本的操作指令 来实现。。
     */
    public String getPermutation(int n, int k) {
        // 题目 限定了k的范围了，这点就可以省略；
        if (factorial(n) < k) {
            return null;
        }

        // 生成nums数组
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = i + 1;

        // 记录当前的索引的数字 是否 被使用过
        boolean[] used = new boolean[n];

        return dfs(nums, new ArrayList<Integer>(), used, 0, n, k);
    }

    /**
     * @param nums     源数组
     * @param select   每一层的选择
     * @param used     数组的元素是否被使用过
     * @param depth    深度，也就是 当前使用的元素的索引
     * @param maxLevel 上限值
     * @param k        剩余的 第k个 排列
     * @return 第k个排列
     */
    private String dfs(int[] nums, List<Integer> select, boolean[] used, int depth, int maxLevel, int k) {

        // 1、终止条件：触发 出口条件，开始收集结果集
        if (depth == maxLevel) {
            StringBuilder sb = new StringBuilder();
            for (Integer s : select)
                sb.append(s);
            return sb.toString();
        }

        // 2、处理 当前层

        // 当前的depth 若选定后，后面的数字 能有多少排列
        int count = factorial(maxLevel - depth - 1);

        // 通过 差值计算，找到 当前层 对应的数值，
        for (int i = 0; i < maxLevel; i++) {
            // 看第i个元素 是否合适

            // 当前元素被使用过，做剪枝
            if (used[i])
                continue;

            // 当前的排列组合数小于k，说明 就算这一层排完了，也到不了第k个，剪枝
            if (count < k) {
                k -= count;
                continue;
            }

            // 否则的话，当前层的数值 就是 这第i个数

            // list收的是string类型
            select.add(nums[i]);
            // 当前元素被使用过标记
            used[i] = true;

            // 3、递归
            // 继续寻找 下一层的数字
            return dfs(nums, select, used, depth + 1, maxLevel, k);
        }


        // 没啥用，写着把
        return null;
    }


    // 返回 n的阶乘，如 3! = 3*2*1 = 6
    private int factorial(int n) {
        int res = 1;
        while (n > 0) {
            res *= n--;
        }
        return res;
    }

}
