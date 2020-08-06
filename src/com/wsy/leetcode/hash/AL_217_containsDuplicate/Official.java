package com.wsy.leetcode.hash.AL_217_containsDuplicate;

import java.util.Arrays;
import java.util.HashSet;

public class Official {

    /**
     * 注意：
     * 这里，只要 判断 是否存在 重复元素，没说 要找出 重复元素 ！！
     * 方法1：
     * 用 hashSet，天生自带 去重 功能；
     * 比较，全部过一遍后，如果 尺寸少了，那就是 有重复数据 ！
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // TODO: 2020/7/23 这里，提前申请好 容量，防止 经常性的扩容 ！
        HashSet<Integer> set = new HashSet<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        return set.size() < nums.length;
    }


    /**
     * todo 感觉 更好一些！
     */
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // TODO: 2020/7/23 这里，提前申请好 容量，防止 经常性的扩容 ！
        HashSet<Integer> set = new HashSet<>(nums.length);

        for (int i = 0; i < nums.length; i++) {

            // TODO: 2020/7/23 或者 放不进去，add 返回 false 就已经是 有重复了，直接返回 true  --> 如果到最后 都没有返回，那就是 false
            if (!set.add(nums[i])) {
                return true;
            }

        }

        return false;
    }


    /**
     * 方法2：
     * 1、先排序 --》会打乱 原数组 顺序，有风险！
     * 2、在从前往后 走一遍，看 当前元素 和 下一个元素 是否 相等
     */
    public boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        Arrays.sort(nums);


        for (int i = 0; i < nums.length - 1; i++) {
            // 排序后，相邻 是否相等
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        // 否没有，就是 false
        return false;
    }


}
