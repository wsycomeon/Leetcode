package com.wsy.leetcode.dpAndGreedy.AL_53_maxSubArray;

public class Official {


    /**
     * 求数组的 最大连续子序列和
     * <p>
     * <p>
     * 方法1：归并
     * 递归了logn 次，但是 递归的过程 类似于，二叉树的 前序遍历，将整个树遍历一遍
     * 所以
     * 时间复杂度： O(n)
     * 空间复杂度： O(logn) 递归所用
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }


        return get(nums, 0, nums.length - 1).maxSum;

    }

    /**
     * 获取区间 [left , right] 的 子段和 信息
     */
    private RangeInfo get(int[] nums, int left, int right) {
        // 1、终止条件
        if (left == right) {
            return new RangeInfo(nums[left], nums[left], nums[left], nums[left]);
        }

        // 2、处理当前层
        int middle = left + (right - left) / 2;

        // 3、分区 递归
        RangeInfo leftRange = get(nums, left, middle);
        RangeInfo rightRange = get(nums, middle + 1, right);

        // 4、善后 -  合并
        return mergeNewRange(leftRange, rightRange);
    }


    /**
     * 根据 两个 相邻的子区间，求 整个区间的 rangeInfo
     * <p>
     * 分别分析：
     * 1、整个区间的元素之和 iSum = 两个区间 分别元素和 相加
     * 2、整个区间的 左端点最大连续子段和 leftSum = left的对应的leftSum 或者 包含了left整个区间的和 + right的对应的leftSum
     * 3、同理，右端点最大连续子段和
     * 4、整个区间的 最大连续子段和：
     * （1）这个子段 不跨越 俩子区间，那就 可能 是 左、右子区间 各自的 最大连续子段和 maxSum
     * （2）要么，这个子段 跨越了 俩子区间，又要连续，又要最大，那只能是 left的最大right + right的最大left
     * --》三者 取最大值！
     */
    private RangeInfo mergeNewRange(RangeInfo leftRange, RangeInfo rightRange) {

        int iSum = leftRange.iSum + rightRange.iSum;

        int leftSum = Math.max(leftRange.leftSum, leftRange.iSum + rightRange.leftSum);

        int rightSum = Math.max(rightRange.rightSum, rightRange.iSum + leftRange.rightSum);

        int maxSum = Math.max(Math.max(leftRange.maxSum, rightRange.maxSum), leftRange.rightSum + rightRange.leftSum);

        return new RangeInfo(leftSum, rightSum, maxSum, iSum);
    }

    /**
     * 一段区间内，连续线段 相关的 四个信息
     * 假设 区间为 [l , r] 中间点为m
     * 左子区间 为 [l , m]
     * 右子区间 为 [m+1 , r]
     */
    static class RangeInfo {

        // 以区间左端点为起点的 最大连续子段和
        int leftSum;

        // 右端点为起点的 最大连续子段和
        int rightSum;

        // 整个区间内的 最大连续子段和
        int maxSum;

        // 整个区间的 和
        int iSum;

        public RangeInfo(int leftSum, int rightSum, int maxSum, int iSum) {
            this.leftSum = leftSum;
            this.rightSum = rightSum;
            this.maxSum = maxSum;
            this.iSum = iSum;
        }
    }


    /**
     * 方法2：贪心
     * <p>
     * 只是 这里的 代码实现，看起来 和 动态规划 几乎一致
     * 但是 思想 却不一样：todo  不好理解 啊！
     * （1）动态规划 讲究的是 找到 最优解的 状态转移方程；
     * （2）贪心，则是说 只在当前 这一步 来看 最终结果，怎么做 是最优的
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 在当前这个结点，求 连续子段 最大和的话
            // 怎么贪心、利益最大化 怎么来，如果 前面的和 < 0 , 是负值 ，对我来说是累赘，那我 当然不加
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 处理过后，还要更新 最大值
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * 3、动态规划
     * <p>
     * 寻找 状态转移方程
     * 对于当前下标 i，以其作为末尾的 最大连续子段和 记为 f(i)；
     * 这样的话，只要求出 所有下标 对应的 最大的 f(i) 就可；
     * <p>
     * 状态转移方程为：
     * f(i) = max( f(i-1) + nums[i], nums[i] )
     * 即 要么 以 自身元素 作为一个线段，要么 拼接上 f(i-1)的最大子段和
     * <p>
     * 从头到尾遍历，记录之前的 f(i-1) 即可
     */
    public int maxSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 以 当前元素的 前一个元素 为末尾的 最大连续子段和 --》这个是一直更新
        // 所以 最开始 为 0（第一个元素之前的元素 是 null，那 这个和 就是 0）
        int preMax = 0;

        // 整个区间的 最大和
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            // 当前的最大和
            preMax = Math.max(nums[i], nums[i] + preMax);

            // 一直比较为 最大值
            max = Math.max(max, preMax);
        }

        return max;
    }

}

