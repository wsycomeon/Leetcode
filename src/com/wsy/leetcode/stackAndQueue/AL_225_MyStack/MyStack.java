package com.wsy.leetcode.stackAndQueue.AL_225_MyStack;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
public class MyStack {


    /**
     * todo 还有两个 双队列的实现方式（都是 另一个当空的用；要么 入队 O(N) ，要么 出队 O(N)）
     * https://leetcode-cn.com/problems/implement-stack-using-queues/solution/yong-dui-lie-shi-xian-zhan-by-leetcode/
     */
    Queue<Integer> queue = new LinkedList<>();

    /**
     * 队列 实现 栈；
     * 方法1：
     * 本身 队列 插入元素，是在 尾部的，那想 让他 现在 变成头部，就把他之前的所有元素，一一 出队、再入队（插入队尾）就好了
     */
    public MyStack() {
    }

    public void push(int x) {
        // 1、插入队尾
        queue.add(x);
        int size = queue.size();

        // 2、将前面的 size-1 个 都出队、再入队
        while (size > 1) {
            queue.add(queue.poll());
            size--;
        }

        // 3、 此时 x 就是 队头了。。

    }

    public int pop() {
        // TODO: 2020/7/23 注意！出队api的区别！ remove() 是发现队列空时，抛出异常；而 poll 则不会，是返回null，给外面使用的时候（比如 拆箱）会 空指针！
        return queue.remove();
//        return queue.poll();
    }

    public int top() {
        // TODO: 2020/7/23 和上面的类似，空的话，直接 抛异常，防止 返回 null 拆箱 空指针！
        return queue.element();
//        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }


}
