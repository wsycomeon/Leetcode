package com.wsy.leetcode.arrayAndMatrixAndSort.AL_283_moveZeroes;

public class Official {


    /**
     * 将原数组的 0位 都移动到 数组的末尾
     * 其实 0位，可以类似于 我们之前做 标记删除的 废弃位。
     * <p>
     * 两种方式：其实 都差不多，用 index 标记 还可以 用于交换的 第一个 0位 下标
     * 方法1：
     * 每次遇到 非0 的时候，直接将 后者交换到 前面的index位，并且 将后者的数字变为0 即可；
     * <p>
     * 方法2：
     * 遇到 非0 的时候，虽然将 后者交换到了前面，但是 后者位置 不置为0，
     * 到后面 从index 开始。统一 置为 0
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 前面可用的 第一个 0位 index
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[index] = nums[i];

                // 当前位，得是和 index不等，才可以 置0
                if (i != index) {
                    nums[i] = 0;
                }

                // 理论上来说，下一个可用的0位
                index++;
            }

        }

    }

    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 前面可用的 第一个 0位 index
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[index] = nums[i];

                // 理论上 来说，下一个可用的0位
                index++;
            }

        }

        // index 开始，统一 置0
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

    }


}
