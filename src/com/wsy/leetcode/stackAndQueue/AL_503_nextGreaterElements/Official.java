package com.wsy.leetcode.stackAndQueue.AL_503_nextGreaterElements;

import java.util.Arrays;
import java.util.Stack;

public class Official {

    /**
     * 循环求 比 当前数组 每个元素 大的 下一个元素 --》很类似
     * todo 注意 ！和 739 更高温度 相比
     * 1、这里 求的并不是 距离，而是 更大的、那个元素的值
     * 2、这里 可以循环来求，最多循环一次，所以 直接 x2；注意 防止越界
     * 3、没有 更大的值，默认值 是 -1，不再是 0
     * <p>
     * <p>
     * 时间：O(N)
     * 空间：O(N)
     */
    public int[] nextGreaterElements(int[] nums) {
        // TODO: 2020/7/23 注意，0长度 数组 应该 返回 0数组，而不是 null
        if (nums == null) {
            return null;
        }

        int length = nums.length;
        int[] result = new int[length];

        // 注意，默认是 -1；
        Arrays.fill(result, -1);

        // 还是存 index，尚未处理、没找到 更大数值的 元素的index
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * length; i++) {
            // TODO: 我曹！这里的 越界处理 要小心啊！
            // todo 不能 直接 模算 i 啊，这样 i 直接 变小了，会导致 死循环 出不去的 ！
//           i = i % length; //  todo 典型的  错误做法！

            int current = nums[i % length];

            // 处理 之前的小数值，弹出、记录
            while (!stack.isEmpty() && current > nums[stack.peek()]) {
                int preIndex = stack.pop();

                result[preIndex] = current;
            }

            // 注意，所有元素 只要 入栈 一次 就好了 --》当然，这里 不做 限制，后面 peek 取模也行。。
            if (i < length) {
                stack.push(i);
            }

        }

        return result;
    }


}
