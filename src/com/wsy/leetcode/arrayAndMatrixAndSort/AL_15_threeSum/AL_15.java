package com.wsy.leetcode.arrayAndMatrixAndSort.AL_15_threeSum;

public class AL_15 {


    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在 三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出 所有 满足条件 且 不重复的 三元组。
     *
     * 注意：答案中 不可以 包含 重复的 三元组。
     *
     *
     *
     * 示例：
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     */
    public static void main(String[] args) {

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};

        System.out.println(new Official().threeSum(nums));
    }


}
