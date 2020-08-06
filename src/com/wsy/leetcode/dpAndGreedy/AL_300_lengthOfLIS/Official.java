package com.wsy.leetcode.dpAndGreedy.AL_300_lengthOfLIS;

public class Official {


    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     * <p>
     * 方法1：
     * 动态规划
     * <p>
     * 定义 dp[i] 为 考虑 前i个元素，以第i个元素为末尾的、最长 上升子序列的长度，
     * 注意 nums[i] 是末尾，必须被选取 ！
     * 我们从前往后，从小到大 计算 dp[] 的值，在计算 dp[i] 之前，也就已经计算出了 dp[0...i-1]的值；
     * 则状态转移方程为：
     * dp[i] = max{dp[j]} + 1 ，其中 0 <= j < i，且 nums[j] < nums[i]
     * <p>
     * 然后，遍历 dp 数组(当然 最好 在上面 一次循环中 就直接记录了)，求最大值，就是 整个数组的 最长 上升 子序列长度
     *
     *
     * 时间：O(n^2)
     * 空间：O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int[] dp = new int[length];

        // 第一个元素之前，最大dp为0，那这里就是1了（本身 只有自身 一种可能，也是 1）
        dp[0] = 1;
        int max = 1;

        for (int i = 1; i < length; i++) {
            // 求 dp[i]
            // 先求 之前 比他小的那些元素的dp的最大值
            int preMax = 0;

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    preMax = Math.max(preMax, dp[j]);
                }
            }

            // i 的 +1
            dp[i] = preMax + 1;
            // todo 求完之后，就顺便比较了
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    /**
     * 方法2：贪心算法
     * 配合 二分查找；
     * <p>
     * --
     * 这个思路 挺难 想的；
     * 总之，就是在遍历的过程中，让 递增子序列 尽可能 增长的慢，好让后面 尽可能的拼接到 稍大的数字
     * 维护一个 单调递增的序列 数组 d[]，
     * 0位 不放有效元素，一开始 将nums[0] 放入 d[1],此时 len = 1，
     * 然后
     * （1）遍历到 比末尾大的数，则长度 要增长，新增元素 直接 插入 len 位，len++;
     * （2）否则，将 这个较小的新数字，插入 d[]， 找到 恰好比他小的那个数字 ，替换 它后面的元素
     * --》考虑是这样的；
     * 我新遍历到的数字，发现 比你前面递增序列的最大的数（就是最后面的）要小，
     * 那么 我 确实 不能让递增序列更长，但是 在保证 现有的最长的递增子序列的长度 不变 这个前提下，
     * 我把自身这个小的数值 插入、替换掉一个大的(最好是 能把 末尾的 直接替换掉)，就相当于 让上升序列 增长的更缓慢了！
     * 那么 后面 有稍大的数值，就 更有可能 被拼接到 这个递增序列上了
     * 比如，这个数组
     * [0,8,4,12,2,10,11]
     * -->
     * 这样的话，d数组最终 就是 [0,2,10,11]
     * --> 因为插入的元素，可能导致 d[] 数组 不是 最长递增序列的正确顺序，
     * 但是 长度 确实是对的
     * <p>
     * ----
     * <p>
     * todo 还是不好理解，直接看下面的代码 吧
     *
     * 时间 O(nlogn) --> n * 二分复杂度
     * 空间 O(n)
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        // 想让 末尾数字 更容易取出（让其index 和 实际子序列内元素的长度 一样），那么 第0位 放 无效元素 占位用
        // 且，最多 整个数组 本身就是有序的，所以 一开始 申请一个最大的数组
        int[] d = new int[length + 1];

        // 第0位 占位用，从 index = 1 开始放元素
        d[1] = nums[0];
        // 插入了一个有效元素，所以 len = 1
        int len = 1;

        for (int i = 1; i < length; i++) {

            // 新数字 比 递归子序列 最大的还大，是递增的，直接拼接上去
            if (nums[i] > d[len]) {
                len++;
                d[len] = nums[i];
            } else {
                // 否则，二分法（d是有序的） 查找 nums[i] 在 d 中 应替换的位置
                // 第一位 才是 有效数据 开始的地方
                int left = 1;
                int right = len;
                // 刚好比 nums[i] 小的那个元素的index
                // 如果，d所有的 有效元素 都比 我大，那比我小的那个位置 就是 无效元素的 0 位了
                // 此时 我替换 1 位就好了。
                int pos = 0;

                // 二分法查找
                while (left <= right) {

                    int mid = left + (right - left) / 2;
                    // 中间数字 比他小
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        // 中间元素反而大，往左找
                        right = mid - 1;
                    }
                }

                // 此时pos就是 刚好比 nums[i] 的位置，把它放到下一个位置就好
                d[pos + 1] = nums[i];
            }

        }

        return len;
    }


}
