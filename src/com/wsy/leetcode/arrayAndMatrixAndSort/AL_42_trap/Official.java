package com.wsy.leetcode.arrayAndMatrixAndSort.AL_42_trap;

import java.util.Stack;

public class Official {


    /**
     * 这题 规律没问题，能看出来
     * todo 但是，用代码落实还是很困难 ！！！
     *
     * 其实，可以自己 从左往右，想象，一根一根 不同高度的柱子插进去
     * 新的柱子插进去之前，不影响之前的流程。
     * 之前水量的统计就是：
     * 凹完 有升的 趋势，就 先计算 能乘的水，后面 如果再升，那就继续计算（不过 要注意，别重复计算，所以，要用一些手段记忆 凹区间的柱子 ）
     * 这里。就是用 stack 来记忆的！
     *
     * todo 遇到升的元素，就要向前计算， 因为它的加入，能最多 乘多少水。
     * 计算一个，弹出一个 之前凹的元素，直到 这个元素 不能算是升的了，或者 stack空了。
     * 这个元素 就可以入栈了，当一个 凹趋势 的元素。。
     * ---
     *
     * 哎！
     * 总之，就是用 stack 来存 一个凹区间的 下降的index，
     * 1、如果 新元素 还是趋于下降的，那就 右移一位，继续找
     * 2、直到 发现 有高位，就说明有 升高的趋势了；
     * 此时 不在移动，而是先计算出来 当前凹区间的水，累加上去
     * <p>
     * 3、凹区间的水，每次弹出一个，计算一层的水量，然后在弹出一层，计算上一层的水量。。。
     * 水的计算 = 当前凹区间 两端相对矮一些的那个 作为高度（木桶原理）
     * 减去 最低点的高度，就是 一个单位的水量，再乘以 小凹区间的长度（注意！这里是 i - peek - 1。画下图就知道了）
     * 然后！
     */
    public int trap(int[] array) {
        // 边界条件
        if (array == null || array.length < 2)
            return 0;


        Stack<Integer> stack = new Stack<>();
        int water = 0;
        int i = 0;

        while (i < array.length) {

            // 不比 前一个柱子高(即 有下坡)，就入栈，后面用来计算
            if (stack.isEmpty() || array[i] <= array[stack.peek()]) {
                stack.push(i++);
            } else {
                // 否则的话，后面的柱子高，有上坡了，可以计算 雨水了
                // 先弹出 矮柱子
                int pre = stack.pop();

                // 如果 stack 不为 null，表示前面有高一点的；
                if (!stack.isEmpty()) {
                    // 找到 两个高点的较小值；木桶原理，由 短板 决定 乘多少水
                    int minHeight = Math.min(array[stack.peek()], array[i]);

                    // 累加雨水量 =
                    water += (minHeight - array[pre]) * (i - stack.peek() - 1);
                }

                // todo 注意，如果矮的入栈，i 原地不动的！
            }
        }


        return water;
    }
}
