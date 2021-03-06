package com.wsy.leetcode.arrayAndMatrixAndSort.AL_1_twoSum;

import java.util.Arrays;

public class AL_1 {


    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * <p>
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int target = 9;


        System.out.println(Arrays.toString(new Official().twoSum(a, target)));
        System.out.println(Arrays.toString(new Official().twoSum2(a, target)));
        System.out.println(Arrays.toString(new Official().twoSum3(a, target)));

    }
}
