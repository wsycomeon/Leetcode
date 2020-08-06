package com.wsy.leetcode.arrayAndMatrixAndSort.AL_485_findMaxConsecutiveOnes;

public class Official {


    /**
     * 计算 数组中 最大连续1 的个数
     * <p>
     * 方法1：
     * 一个max，一个current (表示，到目标位置 连续1 的个数)
     * <p>
     * 遍历数组，
     * 如果是 0 --》 current 置0；
     * 如果是 1 --》 current ++；
     * 每次 都要 比较 max
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }


        int max = 0;
        int current = 0;

        for (int num : nums) {
            current = num == 0 ? 0 : current + 1;
            max = Math.max(max, current);
        }

        return max;
    }


}
