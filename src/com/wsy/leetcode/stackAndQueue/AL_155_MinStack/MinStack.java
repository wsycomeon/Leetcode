package com.wsy.leetcode.stackAndQueue.AL_155_MinStack;

import java.util.Stack;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 * <p>
 * ---
 * <p>
 * 常数时间内 获取到 最小值
 * <p>
 * ---
 * <p>
 * 栈本身 可以用  数组、链表实现，
 * 后进、先出
 * 因为插入、删除较多、最好用链表
 * 但是 还要求，不遍历 直接 获取到 最小值！
 * 这 感觉 需要另一个数组结构，时刻 保持 能方便最小值啊
 * 1、小顶堆？但是 pop的时候，删除的元素，小顶堆 不好操作（不好定位 是哪个啊）啊
 * 2、可以用链表 维护 前后顺序，但是 用 小顶堆 维护大小顺序
 * 3、小顶堆的实现，二叉堆
 * <p>
 * todo 哪有这么复杂。。。
 */
public class MinStack {


    /**
     * todo 我曹，傻逼
     * https://leetcode-cn.com/problems/min-stack/solution/zui-xiao-zhan-by-leetcode-solution/
     * <p>
     * md，用一个辅助栈，同步 一直保存 最小值 就好了啊。。。
     * 实现1：
     * 插入一个值，就同步插入 一个当前的最小值 到 辅助栈的栈顶， 保留的是 和 新插入的当前元素 一一对应的 最小元素值
     * 删除的时候，同步删除 栈顶 就好了；
     * <p>
     * 实现2：
     * 插入一个值的时候，发现更小，就 往辅助栈 再插入一个元素；否则 就不插入 辅助栈
     * 删除的时候，发现 删除的数值 和 辅助栈栈顶相同，就删除 辅助栈栈顶元素 即可
     * <p>
     * ---
     * <p>
     * 这里 直接使用了 原生 Stack ；
     * 没有自己实现 stack（链表 实现 即可）
     * <p>
     * 时间复杂度 O(1)
     * 空间复杂度 O(n)
     */

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MinStack() {
        dataStack = new Stack();
        minStack = new Stack();

        // min 一开始 就插入一个最大值，这样只要插入值 就是 比它小
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        dataStack.push(x);
        minStack.push(Math.min(x, minStack.peek()));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


}
