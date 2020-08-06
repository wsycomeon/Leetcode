package com.wsy.leetcode.stackAndQueue.AL_225_MyStack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack2 {


    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    int front = Integer.MIN_VALUE;

    /**
     * 队列 实现 栈；
     * 方法2：
     * 双队列 实现，这里 规定：
     * 1、q1是反向队列，即 stack效果；
     * 每次，push时，先插入临时的q2，然后 把 q1所有的元素，都依次插过来，直到 q1为空；
     * 然后 q1 q2交换 即可！
     * 2、记录 每次 新插入的 对象，为 栈顶
     */
    public MyStack2() {

    }

    /**
     * 时间：O(N)
     * 空间：O(1)
     */
    public void push(int x) {
        q2.add(x);
        front = x;

        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // 注意，交换！
        Queue<Integer> temp = q2;
        q2 = q1;
        q1 = temp;
    }

    public int pop() {
        int top = q1.remove();

        if (!q1.isEmpty()) {
            front = q1.peek();
        }

        return top;
    }


    public int top() {
        return front;
    }


    // 这里，每次操作完毕，都是 只有 q1 保存 有效的、逆向 数据，只看 q1 即可！
    public boolean empty() {
        return q1.isEmpty();
    }

}
