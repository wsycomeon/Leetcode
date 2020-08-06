package com.wsy.leetcode.arrayAndMatrixAndSort.AL_1_twoSum;

import java.util.HashMap;

public class Official {


    /**
     * 求数组中 是否存在 两数之和 = 某个数，返回 这俩元素的下标
     * 1、且每次输入 都只有一个答案；
     * 2、而且 每个元素 只能使用一次 ，或者说 没有相等值 ！
     */

    /**
     * 方法1：
     * 暴力 枚举法；
     * 先选择一个数，然后从它后面 再选另一个数，看两数之和 是不是target
     * <p>
     * 时间：O(n^2) 都是两次遍历
     * 空间：O(1)
     */
    public int[] twoSum(int[] nums, int target) {

        if (nums == null) {
            throw new IllegalArgumentException("nums is null !");
        }

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }

            }
        }

        // 前面 没返回，说明 输入 有问题！
        throw new IllegalArgumentException("No two sum solution !");
    }

    /**
     * 方法2：
     * 前面取一个元素，再去 后面找和它匹配的方法，使用的 遍历，这样很低效！
     * 实际上。可以使用 hash表，在 近似恒定为 O(1) 的时间 寻找到 另一个数值
     * <p>
     * 时间：O(n)
     * 空间：O(n) 使用hash表 存了 n个元素 和 他们的index。。。
     */
    public int[] twoSum2(int[] nums, int target) {

        if (nums == null) {
            throw new IllegalArgumentException("nums is null !");
        }

        // 存 数值 和 他们对应的index
        HashMap<Integer, Integer> valueMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueMap.put(nums[i], i);
        }

        // 再来一遍，找hashmap中 有没有合适的另一半就好了
        for (int i = 0; i < nums.length; i++) {
            int minus = target - nums[i];

            // TODO: 2020/6/28 这里，注意，因为 又 从头来了一遍，所以 取到的另一半的index 可能就是自身，要排除掉的！
            if (valueMap.containsKey(minus) && valueMap.get(minus) != i) {
                return new int[]{i, valueMap.get(minus)};
            }

        }


        // 前面 没返回，说明 输入 有问题！
        throw new IllegalArgumentException("No two sum solution !");
    }

    /**
     * 方法3：
     * 方法2的优化版本，没必要： 先来一遍 求hash，再来一遍 查 另一个目标值
     * 可以，一遍过程中，边查 有米有另一半，没有的话，把自己的存入hash，等自己的另一半来取就好了
     * 时间：O(n) 虽然 省了一遍 遍历，但是 终究是 遍历了。。。
     * 空间：O(n) 哈希表 省不了
     */
    public int[] twoSum3(int[] nums, int target) {

        if (nums == null) {
            throw new IllegalArgumentException("nums is null !");
        }

        // 存 数值 和 他们对应的index
        HashMap<Integer, Integer> visited = new HashMap<>();

        // 再来一遍，找hashmap中 有没有合适的另一半就好了
        for (int i = 0; i < nums.length; i++) {
            int minus = target - nums[i];

            // 这里，一遍过，valueMap 中 不可能有自身，所以不用判断 index
            if (visited.containsKey(minus)) {
//                return new int[]{i, valueMap.get(minus)};
                // TODO: 2020/6/28 这里，最好反过来写，因为 如果找到了 另一半已经在map中了，说明 对方才是 index更小的，更先被遍历的。
                return new int[]{visited.get(minus), i};
            }

            // 如果，没有另一半，就把自身存进去
            visited.put(nums[i], i);
        }


        // 前面 没返回，说明 输入 有问题！
        throw new IllegalArgumentException("No two sum solution !");
    }


}
