package com.wsy.leetcode.arrayAndMatrixAndSort.AL_645_findErrorNums;

import java.util.Arrays;
import java.util.HashMap;

public class Official {


    /**
     * todo ‘重复’的 英文 是 duplicate  即 dup ！
     * 不是 dump ， 这是抓取的意思！
     * <p>
     * 注意，这题目 是说 1-n 的 n个数字中，丢失了 某个数字，重复了 某个数字，
     * 返回 数组 int[] {dup，missing}
     * <p>
     * 比如 1 2 3 4 5 6 8 9 9 --》注意啊，重复了 9，不代表 缺失的是10，还可能是其他的，比如 7
     * <p>
     * 方法很多：
     * https://leetcode-cn.com/problems/set-mismatch/solution/cuo-wu-de-ji-he-by-leetcode/
     * <p>
     * 方法1：暴力枚举：
     * 对于 1 - n 的 每个数字，都遍历一遍 当前数组，用一个 count 统计 它出现的次数；
     * 如果 它出现的次数是 2 ，那就是dup的数字，
     * 如果 count = 0，那就是 缺失的数字；
     * <p>
     * --> 稍微优化下，就是 dup 和 missing 都 找到后，就 break 外循环。。
     * <p>
     * 时间：O(N^2)
     * 空间：O(1)
     * <p>
     * <p>
     * <p>
     * <p>
     * 方法2：先排序，后查找
     * 排序后，重复的数字 是 连在一起的 --》可以找到 dup
     * 缺失的数字，相邻俩数字 差值 > 1 --》 可以找到 missing
     * <p>
     * ---> TODO 这个算法的 边界条件 很麻烦、容易漏掉 ，比如 missing 的是 1(默认) 和 n 的时候 ！
     * <p>
     * 时间：O(nlogn) 排序 需要
     * 空间：O(logn) 排序 需要
     */
    public int[] findErrorNums1(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return new int[]{0, 0};
        }

        // 1、先排序
        Arrays.sort(nums);

        // 2、遍历nums数组，根据规则，找出 目标值
        int dup = -1; // todo 一般初始值 = -1 ，因为 这里的元素都是 正数，所以 0 也行

        // TODO: 2020/7/26 注意！如果 缺失的是 数字 1，后面的算法 会漏掉！ 比如 [2 , 2] --》所以，只是 这个算法来说， missing 默认 为 1 ！
        int missing = 1;

        // 排序后，数字 a，应该出现在 a-1 的 位置上
        for (int i = 1; i < nums.length; i++) {

            // TODO: 2020/7/26 注意，遍历 某个元素，只能 找出 两个目标值的一个；所以，可能出现 俩值 通过 循环 找不全 的情况

            // 元素重复
            if (nums[i] == nums[i - 1]) {
                dup = nums[i];
            } else if (nums[i] - nums[i - 1] > 1) {

                missing = nums[i - 1] + 1;
            }
        }

        // TODO: 2020/7/26 所以 还要考虑 边界条件，比如 缺失的是 最后一位，那么，上面的循环 就找不到 缺失的 missing 了
        // 比如 长度为8的数组，最后一位 是不是 8 --> 如果不等的话，表示 缺失的数值 是这个，重新赋值，否则 不变；
        missing = nums[nums.length - 1] == nums.length ? missing : nums.length;

        return new int[]{dup, missing};
    }


    /**
     * 方法3：
     * 过一遍 原数组，用 哈希表 统计 每个数字 出现的次数，
     * 然后 在过一遍 1-》n 的所有数字 ，看 其在 哈希表 中 出现的次数，
     * 是 0 就是missing ，2 就是 dup
     * <p>
     * 时间：O(n) 两次遍历
     * 空间：O(n) hash表 空间
     */
    public int[] findErrorNums2(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return new int[]{0, 0};
        }

        // 1、统计次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        int dup = -1;
        int missing = -1;

        // 过一遍 目标数字 1 - nums.length()
        for (int i = 1; i <= nums.length; i++) {
            Integer count = map.getOrDefault(i, 0);
            if (count == 2) {
                dup = i;
            } else if (count == 0) {
                missing = i;
            }
        }


        return new int[]{dup, missing};
    }

    /**
     * 方法4：
     * 上述 哈希表的 空间优化
     * <p>
     * 因为 hash表 存一个数字 int 和 它出现的次数，用了 两个位置；所以，是 2n 的空间；
     * 实际上，用一个 一维数组 就可以 实现这个效果
     * --》数字 i 出现的次数 存在 下标 i 的位置 ---》这个额外 数组长度 为 n+1 的空间 即可
     * <p>
     * 然后，过一遍 这个数组 1 ---》 nums.length 就好了
     * <p>
     * <p>
     * 时间：O(n) 两次遍历
     * 空间：O(n) 数组空间
     */
    public int[] findErrorNums3(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return new int[]{0, 0};
        }

        int[] counts = new int[nums.length + 1];
        // 统计次数
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]]++;
        }

        int dup = -1;
        int missing = -1;

        // 过一遍 新数组
        for (int i = 1; i < counts.length; i++) {

            if (counts[i] == 2) {
                dup = i;
            } else if (counts[i] == 0) {
                missing = i;
            }

        }

        return new int[]{dup, missing};
    }

    /**
     * 方法5：
     * <p>
     * 负数 标记法：
     * 1、已知 原数组 元素 都是 正数
     * 2、当 遍历到 数字 a 时，查看 它排序后 应在的位置 a-1 的数值；
     * （1）如果是 正数，就将 那个数 取反 成负数；
     * （2）如果 已经是 负数，说明 这个数字 i 就是 dup
     * 3、上述 遍历 结束后，找到了 dup；
     * <p>
     * --》 还需要 找 missing
     * 4、missing 的数字，重新 遍历一遍数组，
     * --》如果发现 下标 为 i 的位置，元素 仍为 正数，说明 没有 i+1 这个大小的 元素
     * --》反证法：如果存在 i+1 这个数值，i 这个位置的 元素值 已经 取反 为 负数了 ！
     *
     * <p>
     * <p>
     * 时间：O(n) 两次遍历
     * 空间：O(1)
     * <p>
     * todo 虽然是 最优的算法，但是 要注意，改变了 原数组的数值（负数），取出来 用的时候，别忘记 加 abs 转成 正数！
     */
    public int[] findErrorNums(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return new int[]{0, 0};
        }

        int dup = -1;
        int missing = -1;

        for (int i = 0; i < nums.length; i++) {
            // 当前数值 排序后 应在的 目标位置 --》todo 注意，我们后面 可能会改变 数组元素的值 为 负数，所以 取用 nums[i] 要注意用 abs 绝对值 ！
            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                // 目标位置 已经是 负数，说明 当前元素 重复了
                dup = Math.abs(nums[i]);
            } else { // 否则，那个位置的值 取负！
                nums[index] *= -1;
            }

        }

        // 寻找 missing
        for (int i = 0; i < nums.length; i++) {

            // 如果 i 位置的元素 仍 > 0，说明 缺失了 i+1 这个大小的 数值
            if (nums[i] > 0) {
                missing = i + 1;
            }

        }

        return new int[]{dup, missing};
    }


}
