package com.wsy.leetcode.stackAndQueue.AL_739_dailyTemperatures;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Stack;

public class Official {


    /**
     * 计算数组中 每个元素，后面第一个比他大的元素 和 它的距离值
     * <p>
     * https://leetcode-cn.com/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
     * <p>
     * 方法1：
     * 比较精妙吧。
     * 和 接雨水 类似，维护一个单调栈，栈里面的元素，从底向上 是 依次递减的；遇到 大数值，就把 栈中 较小的数值 出栈
     * 当然，每个元素 都要入栈 一次
     * 且 为了计算 距离差值，栈中存的是 index，不是具体的 数值 ！
     * <p>
     * 时间：O(N)
     * 空间：O(N)
     */
    public int[] dailyTemperatures(int[] t) {
        if (t == null) {
            return null;
        }


        int length = t.length;
        // 最终的 结果数组 --》么有比 i 更大的，那就是 0，符合 题意！
        int[] result = new int[length];

        // 存储 尚未找到 比他大的第一个值的 元素 对应的 index ！
        Stack<Integer> stack = new Stack<>();

        // 遍历 数组
        for (int i = 0; i < length; i++) {
            int currentTemp = t[i];

            // 处理 stack 中 所有 比当前温度 低的元素（弹出、并计算）
            // TODO: 注意，stack 存的是 index，不是 温度 ！还要 取一下的！
            while (!stack.isEmpty() && currentTemp > t[stack.peek()]) {

                // 依次弹出，并处理它的距离
                int preIndex = stack.pop();

                result[preIndex] = i - preIndex;
            }

            // 不管 怎么样，最后 当前 index 都要入栈的，好寻找 它的距离
            stack.push(i);
        }

        return result;
    }


}
