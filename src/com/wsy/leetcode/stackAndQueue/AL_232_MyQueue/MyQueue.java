package com.wsy.leetcode.stackAndQueue.AL_232_MyQueue;


import java.util.Stack;

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
class MyQueue {

    Stack<Integer> add = new Stack<>();
    Stack<Integer> del = new Stack<>();

    //  记录 add 的 栈底 ！
    int front = Integer.MIN_VALUE;

    /**
     * 用 栈 来实现队列的效果；
     * 队列是 先进先出的；而 栈是 后进先出；但是 负负得正 啊!
     * 用 两个栈 就可以 实现 顺序了
     */
    public MyQueue() {

    }


    /**
     * 插入队列尾部，实际 插入 add 栈即可
     */
    public void push(int x) {
        // add 可能 弹空了
        if (add.isEmpty()) {
            // 记录 新的栈底
            front = x;
        }

        add.push(x);
    }


    /**
     * 弹出 队头的 元素
     * <p>
     * 时间：摊还后 实际上 还是 O(1)
     */
    public int pop() {
        // todo 前置操作（可以 抽取出来）：如果 del栈 是空的，就把 add的全部 弹出，压入 del
        if (del.isEmpty()) {
            while (!add.isEmpty()) {
                del.push(add.pop());
            }
            // TODO 这里，实际上 add 弹空了，front 失效了 --> 这一步 也可以 不用写的
            front = Integer.MIN_VALUE;
        }

        return del.pop();
    }


    /**
     * 注意，这个只是 取 队头元素；并不是 需要弹出的
     *
     * todo 当然可以 像 pop 这样做，但是没必要，
     *
     * 有更简单的办法，直接 标记法 --》
     *
     * 1、当 del 非空，那肯定 就是 del的顶了；
     * 2、否则，就是 每次记录的 add的 栈底 ！
     */
    public int peek() {
        if (!del.isEmpty()) {
            return del.peek();
        }

        return front;
    }

    /**
     * 两个 栈 都为空，才是真的空
     */
    public boolean empty() {

        return add.isEmpty() && del.isEmpty();
    }

}
