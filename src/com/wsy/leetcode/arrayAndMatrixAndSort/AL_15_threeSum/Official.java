package com.wsy.leetcode.arrayAndMatrixAndSort.AL_15_threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从一堆数字中，求三数字之和为0的所有 组合
 * 且结果 不能重复
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Official {


    /**
     * 先定 中间数 C位的方式，会很蛋疼，有很多边界条件 不好处理，
     * todo 其实 排序后 从前往后 依次枚举，不定C位 ，反而好做 ！
     * <p>
     * -----
     * <p>
     * 方法 1：
     * 1、先排序，从小到大（自带的是 双基准快排 就是 O(nlogn)）
     * 2、找规律，从前往后 先锁定1，去找2(当然 肯定在1的右边) 和 3：
     * 如果 锁定2 再去循环遍历 找3，那就是 n^3 的 复杂度了。。。
     * <p>
     * 3、所以要 优化（双指针 向内缩进，加快速度！）
     * 因为从小到大排序，所以 2+3 更大（ > sum - 1）的话，那就在2的单次循环内，将3 往左移动 直到 符合条件 或者 指针 重合之类的；
     * 下次 2 变大 右移的时候，此次的目标 3 只会比 之前的 3 更小，继续 向左移动 完全符合条件。
     * 在 2的 n次 遍历过程中（向右 n 次），3 最多 会移动 n 次，所以 平均 才 1次，
     * 则，整体时间复杂度 就是 O(n^2)，而不是 O(n^3) 了
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // TODO: 2020/6/21 边界条件
        if (nums == null || nums.length < 3) {
            return result;
        }

        int n = nums.length;

        // 先 从大到小 排序。时间复杂度是 O(nlogn)
        Arrays.sort(nums);


        // 枚举 a
        for (int first = 0; first < n; ++first) {

            if (nums[first] > 0) {
                // 最小数 都 > 0 ，总和 必然大于0了，break
                break;
            }

            // todo 需要和 上一次枚举的数 不相同，否则 直接跳过 --> 否则的话，已经 排过序 了，比如 first 第一个数的值 都是 -1，有啥区别呢，浪费 性能，所以 去重
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // TODO: 2020/6/21 因为 已经从小到大排序了，如果 a+b+c=0,而 b' > b的话，对应的 c' < c 一定成立，所以 取b的时候 直接让 c 同时 从右往左 移动，双指针 快速定位
            // 能将 分别定位 b和c n^2 的 复杂度，优化到 n
            // c 对应的指针 初始指向 数组的最右端
            int third = n - 1;

            // 即 b+c 之和
            int target = -nums[first];

            // 枚举 b，b肯定在a后面的，从+1 开始
            for (int second = first + 1; second < n; ++second) {

                // todo 需要和上一次枚举的数 不相同，一样 跳过，去重
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                // 需要保证 b 的指针 在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) { // 即 c 还是 太大了，需要 向左移动
                    --third;
                }

                // 前面 跳出循环了，
                // 1、如果 指针重合，本次的b， 没找到 匹配的 c，那么 随着 b 后续的增加
                // 就 绝对不会有 满足 a+b+c=0 ，并且 b<c 的 c 了，可以 退出循环
                if (second == third) {
                    break;
                }

                // 2、找到了，即 = 的 时候
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    // 加入 结果结合
                    result.add(list);
                }

                // 3、当前 second 没找到 匹配的，直接 < 0 了，继续 continue
            }

        }

        return result;
    }


}
