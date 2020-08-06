package com.wsy.leetcode.hash.AL_594_findLHS;

import java.util.HashMap;
import java.util.Set;

public class Official {


    /**
     * 最长 和谐子序列 的长度（最大值、最小值的 差值为1 的 序列--》序列 可没说 是 连续的 ！）
     * 其实就是求：
     * 相邻元素 出现次数之和的 最大值
     * 比如，i 和 i+1 出现的次数之和；
     * <p>
     * 方法1：暴力枚举：
     * 对于 下标为 i、数值为 a 的元素，每次 都从前往后 找 数值为 a ，a+1的元素个数，
     * 记录其最大值；
     * <p>
     * 方法2：hash表 计算
     * （1）过一遍数组，将 各数值 - 出现的次数 记录到 hash表中
     * （2）遍历 hash表，找 a 和 a+1 出现的次数和，与 最大值比较
     * <p>
     * O(N)
     * O(N)
     */
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 反正 出现次数 要 +1
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // 最长 和谐子序列 长度
        int max = 0;

        for (Integer num : map.keySet()) {
            // 新 和谐子序列 和
            // TODO: 2020/7/23 注意！这里 必须有子序列 才能计算！否则，可能误算，比如 num 长度为100，但是没有 101，那也 不能算是 子序列！
            if (map.containsKey(num + 1)) {

                max = Math.max(max, map.get(num) + map.get(num + 1));
            }

        }

        return max;
    }


    /**
     * 方法3：
     * hash表 优化法；
     * 上面是 先过一遍数组 存到 hash表，在过一遍hash表，计算最大值！
     * todo 实际上 可以过一遍数组 就可以了，边过数组，边求 (a-1 , a)的和，(a，a+1)的和， 之前的最大值 三者的最大值
     * <p>
     * 时间：O(N)
     * 空间：O(N)
     */
    public int findLHS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            // 先存进去
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            // 存在 子序列 才能 计算 max
            if (map.containsKey(nums[i] - 1)) {
                max = Math.max(max, map.get(nums[i]) + map.get(nums[i] - 1));
            }

            if (map.containsKey(nums[i] + 1)) {
                max = Math.max(max, map.get(nums[i]) + map.get(nums[i] + 1));
            }

        }


        return max;
    }


}
