package com.wsy.leetcode.datastructure.offer.AL_09_CQueue;

public class AL_09 {

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     * 示例 2：
     * <p>
     * 输入：
     * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
     * [[],[],[5],[2],[],[]]
     * 输出：[null,-1,null,null,5,2]
     * 提示：
     * <p>
     * 1 <= values <= 10000
     * 最多会对 appendTail、deleteHead 进行 10000 次调用
     */
    public static void main(String[] args) {


        test1();

        System.out.println();

        test2();
    }

    private static void test1() {

        CQueue queue = new CQueue();
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());

    }

    private static void test2() {

        CQueue queue = new CQueue();
        System.out.println(queue.deleteHead());
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());

    }


}
