package com.wsy.leetcode.stackAndQueue.AL_155_MinStack;

import java.util.Stack;

public class MinStack3 {

    /**
     * 方法3
     * <p>
     * 只用一个 数据栈，不用 再多一个辅助栈了
     * 但是 常数时间 获取最小值，
     * 意味着 也需要 时时刻刻的 保存 当前元素 入栈后 的最小值；
     * 所以 将 val 封装成一个 node：
     * val + min
     */

    Stack<Node> stack;

    public MinStack3() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new Node(x, x));
        } else {
            stack.push(new Node(x, Math.min(x, stack.peek().min)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }


    static class Node {
        int val;
        int min;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
}
