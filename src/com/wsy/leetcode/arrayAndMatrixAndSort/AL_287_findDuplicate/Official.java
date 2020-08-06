package com.wsy.leetcode.arrayAndMatrixAndSort.AL_287_findDuplicate;

public class Official {


    /**
     * 本题：在无序数组中，寻找 重复的数字
     * 且已知：数组元素值 在 1 - n 之间，且数组长度 = n+1，且 这里 固定死了 就一个 数字 重复！
     * <p>
     * todo：注意，本题简化了下，固定了 只有一个数字重复 ---》但是 需要注意，这个数字 重复的次数 没说，其出现的次数 可能是 2，也可能是 3 等等 --》即 1 - n 之间的数字 可能缺失，也可能 没缺失
     * 而且 本题要求:
     * 时间复杂度 < O(N^2) --》双重循环， 暴力检查 每一个数字的算法 就是O(N^2)，即 暴力解法 不行。。。
     * 空间复杂度为 O(1) --》 hash 或者 数组 计数法 就不行了，那是 O(N)
     * 不能 修改 原数组 --》数字出现，就 修改 对应 index的 元素值 为 负数 来标记重复的方法 ，也不行了。。。
     * <p>
     * todo --》那么 就得想 其他 判定 元素 是否重复的办法了 ！
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * <p>
     * 方法1：
     * 该数组 元素的取值 很有特点，能 总结出 一条规律：
     * 对于数组中 出现的 任一个数值 num，计算 数组中 <= num 的 数字个数 count；
     * 如果 count <= num，则 <= num 的数字 都没有重复；
     * 否则 即 count > num 则说明 1 - num 之间 必然 有某个 数值 重复了 --》num 有重复的可能；
     * 而，对于 1 - num 之间的数字的 count 是单调递增的，即 比如 3重复了，那么4的count值 肯定比3的大，而且也符合 count > num 的特性！
     * <p>
     * 所以，我们只要 二分查找 到 最小的、第一个出现 count > num 的元素 即可
     * 由题意知，数组长度 比 num 取值范围 大 1，则 元素范围起始上限 max = length - 1
     * <p>
     * 时间：O(n*logn) ：区间范围是 1 - n-1，则 二分 最多进行 logn次，每次 都要 for循环 遍历 一次数组 是 O(n)，所以整体是 O(n * logn)
     * 空间：O(1)
     */
    public int findDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 二分法查找到 最小的、符合条件 数组中 <= num 的元素个数 count > num 的这个数值 num
        int length = nums.length;
        // 元素的取值 区间
        int left = 1;
        int right = length - 1; // 根据 数组长度 求 元素的最大值

        int dup = -1;

        while (left <= right) {
            // 求 区间 中间值
            int num = left + (right - left) / 2;

            int count = 0;
            // 求其 count
            for (int i = 0; i < length; i++) {
                if (nums[i] <= num) {
                    count++;
                }
            }

            // 该数字 有重复的 可能，看比它小的，有没有 重复的，往左
            if (count > num) {
                dup = num;
                right = num - 1;
            } else {
                // 包括 num 在内，没有 重复元素，往右找
                left = num + 1;
            }
        }

        return dup;
    }

    /**
     * 方法2：
     * 链表就是一种 映射、对应关系，前一个节点 映射到 下一个节点
     * 如果，某个结点，被映射了 两次，则 说明 这个节点 就是 重复节点，也就是 链表的入环点
     * --》根据 快慢指针 + 同步指针 可以 求出！
     * <p>
     * 而我们这里的数组元素，利用 自定义的映射关系 组成 链表，
     * 比如 下标 i，映射到 nums[i] 这个元素， 且将 下标 更新为 nums[i]
     * 下次在从 新的位置，继续用 同样的方式 映射下去；
     * --》若 某个元素值 重复，则 这个元素值 可能由 两个不同的index i 和 j 映射过来
     * --》则 类似于 链表 存在 环！
     * 我们也是 求 入环点 即可：
     * <p>
     * 链表 那边 判断 入环点 是用 node 相等；而这里 就是 数值 相等；
     * 链表 那边 判断 第一次相遇 是用 node 相等， 而这里 也是 数值 相等；
     * <p>
     * todo 链表 那里，走一步，就是靠 next 一步，其实 就是 映射 一步，在这里 就是 根据 index 跳转到 下一个index 求 nums[index] ，也就是一个数组元素（相当于 链表的节点）
     * <p>
     * <p>
     * 时间：O(n) --》这种 快慢指针 算法，慢指针 走了 2a + b 步，类似于 总长度 n了，即 线性 时间复杂度
     * 空间：O(1)
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 快、慢指针 都先从 起点 即 index = 0 开始，往后 走（就是 往后 映射）
        int slow = 0;
        int fast = 0;

        // 求 第一次 环内的相遇点 fast
        // TODO: 2020/8/5 注意，因为 数组元素取值为 1 -》 n，而长度为 n + 1，所以 nums[num] 是 不会 越界的！

        // TODO: 2020/8/5 注意，这里 要先操作一次 ，在进行 条件判断，所以是 do - while 循环，而不是 while 循环
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // slow 从 起点 重新开始，两者 同步走，直到 下一次 相遇，相遇点 就是 入环点，也就是 重复元素
        slow = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }


}
