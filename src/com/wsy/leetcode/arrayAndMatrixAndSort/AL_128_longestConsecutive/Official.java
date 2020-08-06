package com.wsy.leetcode.arrayAndMatrixAndSort.AL_128_longestConsecutive;

import java.util.HashSet;
import java.util.Set;

public class Official {

    /**
     * 要求 O(n)的时间复杂度。。。
     * <p>
     * todo --> 可以理解成 不能有 嵌套 循环、遍历的 --》 但是 可以有 多个并排的、遍历操作！
     * <p>
     * 所以 单纯的排序算法（桶排序 这样的，硬性要求是：数据是 不能太离散的。。空间要求高） --》 肯定是不行了
     *
     * <p>
     * 那就 另辟蹊径：
     * todo 遍历到一个数字时，看 数组中 有没有 和他相连的数，这个查询 得超级快 --》而且 这个数 得是 序列起点，即 没有 nums-1 这个数 才行！
     * 所以 用hash表吧
     * <p>
     * <p>
     * 时间：O(N) --> 第二个for循环的 内循环 进入 是有条件的，if 那里，判断有 num-1 就不能进去；所以，所有元素 最多 只能进去 一次，平摊为 O(1)了。
     * 空间：O(N)
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // hash表，过一遍，存下 不重复数据 即可
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }

        // 最长连续序列的长度
        int maxCount = 0;

        // 遍历 set
        for (int num : set) {

            // todo 看 set中 有比 当前数字小1的吗 --》此时 当前元素 就不能 当做 连续序列的起点了，需要跳过 ！
            if (!set.contains(num - 1)) {

                // 如果 没有比他小1的数，可以作为起点，看下后面 有几个连续的
                int currentNum = num;
                int count = 1;

                // 内层的循环
                while (set.contains(currentNum + 1)) {
                    count++;

                    currentNum++;
                }

                // 更新 最大count
                maxCount = Math.max(maxCount, count);
            }

        }

        return maxCount;
    }


}
