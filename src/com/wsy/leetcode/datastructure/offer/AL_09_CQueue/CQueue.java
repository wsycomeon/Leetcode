package com.wsy.leetcode.datastructure.offer.AL_09_CQueue;

import java.util.Stack;

public class CQueue {


    private Stack<Integer> addStack;
    private Stack<Integer> delStack;

    /**
     * 双栈 实现一个队列的功能；
     * 1、一个栈 当插入栈，另一个 当弹出栈
     * 2、插入的时候，往插入栈 push
     * 3、删除的时候，先看 另一个栈是空的吗，空的话，把栈1的全部 pop 然后 加进去
     * 否则，还是  另一个栈 弹出 就好了。。
     * <p>
     * <p>
     * <p>
     * 时间复杂度：对于插入和删除操作，时间复杂度均为 O(1)
     * 插入不多说，对于删除操作，虽然看起来是 O(n) 的时间复杂度，但是仔细考虑下 每个元素只会「至多被 插入和弹出 stack2 各一次」，
     * 因此 均摊下来每个元素 被删除的时间复杂度 仍为 O(1)
     * <p>
     * 空间复杂度：O(n)O(n)。需要使用两个栈存储已有的元素。
     */
    public CQueue() {

        addStack = new Stack<>();
        delStack = new Stack<>();
    }

    public void appendTail(int value) {
        addStack.push(value);
    }

    public int deleteHead() {
        // del 空的话，尝试 将 add 的 弹出到 del
        if (delStack.isEmpty()) {

            while (!addStack.isEmpty()) {

                delStack.push(addStack.pop());
            }

        }

        // 经过上一轮 补充，还是 null，那就 -1；
        if (delStack.isEmpty()) {
            return -1;
        }

        return delStack.pop();
    }


}
