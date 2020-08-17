package com.wsy.leetcode.string.AL_128_longestConsecutive;

import java.util.HashSet;

public class Official {

    // TODO: 2020-08-17 09:21:16 未排序数组的 最长连续 序列的长度，时间复杂度是 O(n)
    // TODO: 2020-08-17 09:21:53 如果 没要求 时间复杂度的话（O(nlogn)），那就 先排序，再 求 连续序列 是最简单的
    // TODO: 2020-08-17 09:22:46 但是，要求是 O(n) 的 时间复杂度，基本 就是 多次、非嵌套的 遍历做法 才行了！

    // TODO: 2020-08-17 09:30:26 果然， 用 hashSet记录 所有不重复的数字，然后 找 可以作为起始数字的元素，起始 判断 num+1 依次 是否存在 即可。。

    // TODO: 2020-08-17 09:41:07 搞定！


    /**
     * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     * <p>
     * 方法1：
     * 未排序数组的、最长连续序列 --》要求，时间复杂度是 O(n)
     *
     * 时间：O(n) --》每个元素 都被遍历 2次（组成set一次、访问num一次 --》contains 控制了 只能进一次）
     * 空间：O(n) --》hashSet 的 容量
     *
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1、hashSet 记录 所有 不重复的 元素值
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 2、重新遍历 nums 数组，取 合适的值 作为 序列的起点 计算长度
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            // set 中不存在 num-1 的 num 才能是 起点
            if (set.contains(num - 1)) {
                continue;
            }

            // 求 该序列的 长度
            int currentNum = num;
            int currentLength = 1;

            while (set.contains(currentNum + 1)) {
                currentLength++;
                currentNum++;
            }

            // 尝试 更新 max
            max = Math.max(max, currentLength);
        }

        return max;
    }


}
