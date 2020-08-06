package com.wsy.leetcode.stackAndQueue.AL_155_MinStack;

public class AL_155 {


    /**
     * 最小栈
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     * <p>
     * <p>
     * 示例:
     * <p>
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     * <p>
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     * <p>
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     * <p>
     * <p>
     * 提示：
     * <p>
     * pop、top 和 getMin 操作总是在 非空栈 上调用。
     */
    public static void main(String[] args) {

        test1();
        test2();
        test3();
        test4();

    }

    private static void test1() {
        System.out.println("test1");

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());

        minStack.pop();

        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    private static void test2() {
        System.out.println("test2");

        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());

        minStack.pop();

        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    private static void test3() {
        System.out.println("test3");

        MinStack3 minStack = new MinStack3();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());

        minStack.pop();

        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    private static void test4() {
        System.out.println("test4");

        MinStack4 minStack = new MinStack4();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());

        minStack.pop();

        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
