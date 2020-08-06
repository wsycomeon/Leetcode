package com.wsy.leetcode.arrayAndMatrixAndSort.AL_674_findLengthOfLCIS;

public class Official {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }


        int max = 0;
        // 当前 递增子序列的 起始index
        int anchor = 0;

        for (int i = 0; i < nums.length; ++i) {
            // 不递增了，变换坐标
            if (i > 0 && nums[i - 1] >= nums[i])
                anchor = i;

            // 无论怎样，都要 更新 max
            max = Math.max(max, i - anchor + 1);
        }

        return max;
    }


}
