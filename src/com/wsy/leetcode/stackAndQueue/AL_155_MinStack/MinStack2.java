package com.wsy.leetcode.stackAndQueue.AL_155_MinStack;

import java.util.Stack;

public class MinStack2 {


    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    /**
     * 这个也是用辅助栈的，是 方法2
     * 每次，插入的值，比当前的更小，才插入 辅助栈
     */
    public MinStack2() {
        dataStack = new Stack<>();
        minStack = new Stack<>();

        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        dataStack.push(x);

        // todo 要包括 = 的情况，不然后面 pop 判断等于的时候，直接 删除，可能导致 误删 辅助 栈顶的了
        if (x <= minStack.peek()) {
            minStack.push(x);
        }

    }

    public void pop() {
        Integer pop = dataStack.pop();

        // 发现 刚弹出来的大小 = 辅助栈的栈顶元素，辅助栈 栈顶元素 也得弹一个
        if (pop.equals(minStack.peek())) {
            minStack.pop();
        }

    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
