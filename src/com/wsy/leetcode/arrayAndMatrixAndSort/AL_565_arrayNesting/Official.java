package com.wsy.leetcode.arrayAndMatrixAndSort.AL_565_arrayNesting;

public class Official {

    // TODO: 2020-08-06 14:29:48 数组嵌套
    // TODO: 2020-08-06 14:32:16 看起来 就一个暴力枚举的方法，对于每个元素 都 作为起始点 计算下 其最长数组长度 来比较 最大的，那就是 O(n^2)
    // TODO: 2020-08-06 14:49:16 看了官方的解法，就是 上面 暴力枚举的剪枝 优化 --》因为 通过前面 访问过某个元素，后面 不需要 访问了，因为那是一个循环，获得的结果 是一样的；
    // 主要 就是看，怎么标记 某个元素 有没有 被访问过：方法1：用一个额外数组标记 方法2：直接修改 访问过的元素值 为 Integer.MAX_VALUE 或者 -1 --》 因为 取值范围是 [0 , n-1]
    // TODO: 2020-08-06 15:32:14 整理 完毕！


    /**
     * 求 包含 [ 0 , n-1] 的 长度为 n 的 无序数组中（无重复 元素），最长 嵌套数组的长度，
     * 嵌套规则是：
     * 数组的任一元素 为 起始点，则其作为 坐标，求下一个元素，只要 不在新数组中 重复，就一直取下去，计算 这个数组的长度；
     * 取最大的那个 ！
     * <p>
     * 方法1：暴力枚举
     * 从前往后，对每一个元素 都求 其作为 起始元素的那个数组的 长度（todo 元素 出现 重复时 终止，当然 是和 起始元素 重复），求最大值 !
     * <p>
     * 时间：最差是 O(N^2)
     * 空间：O(1)
     */
    public int arrayNesting1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 遍历 每一个元素
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            // 新数组的 起始元素
            int start = nums[i];
            int num = start;

            do {
                count++;
                num = nums[num];
            } while (num != start);

            max = Math.max(max, count);
        }

        return max;
    }

    /**
     * 方法2：
     * 前面 暴力枚举方法的 剪枝 --》因为 某个元素 被访问过后，就没有必要 在访问了，因为 被访问过的元素，所在的数组 长度是固定的，是一个 死循环，
     * 所以，标记 访问过的元素，做个剪枝 就好了
     * <p>
     * 时间：O(n) 数组中的每个元素，都 仅仅 被访问过一次
     * 空间：O(n) 用到了一个 额外数组
     */
    public int arrayNesting2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;

        // 记录 访问过的元素 index
        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {

            // 加一层 剪枝 --》todo 作为 起始 元素 不能被 访问过
            if (!visited[i]) {

                int count = 0;
                // 新数组的 起始元素
                int start = nums[i];
                int num = start;

                do {
                    count++;
                    num = nums[num]; // 下一个元素

                    // TODO: 2020-08-06 15:10:47 被其他元素 访问过，作为 数组元素 计算过了
                    visited[num] = true;

                } while (num != start);

                max = Math.max(max, count);

            }

        }


        return max;
    }

    /**
     * 方法3：
     * 还是 暴力枚举的 剪枝优化，只不过 方法2 使用了 数组 来标记 某个元素 有没有 被访问过；
     * 这里，不适用 额外空间，直接 访问过的，将该元素 修改为 -1 --》因为 原数组的元素 取值是 [0,n-1] , n是 [1,20000] 的正整数 !
     * <p>
     * 时间：O(n) 还是 所有元素 都 仅访问一次
     * 空间：O(1) 没有 用到 额外 空间！
     */
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;

        // 记录 访问过的元素 index

        for (int i = 0; i < nums.length; i++) {

            // TODO: 2020-08-06 15:20:08 注意，这里的剪枝 稍有区别，因为 访问过的元素值 会被修改 为-1，所以我们这里的 循环判断条件 也就 不需要 用 start 进行标记了。。
            if (nums[i] != -1) {

                int num = nums[i];
                // TODO: 2020-08-06 15:29:56 注意，这里，虽然 不是 do-while 循环，count 起始值 依然 应该是 0 ，而不是 1 ！
                int count = 0;

                while (nums[num] != -1) {
                    count++;
                    int tempIndex = num;
                    num = nums[num]; // 访问下一个
                    nums[tempIndex] = -1; // 把当前的值 修改为 -1
                }

                max = Math.max(max, count);

            }

        }


        return max;
    }


}
