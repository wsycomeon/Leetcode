package com.wsy.leetcode.stackAndQueue.AL_155_MinStack;

public class MinStack4 {

    /**
     * TODO 最优秀 做法！
     * 完全 自己实现一个栈，用 链表 就好了
     * 并且 栈内的元素node，还一直 保存着 其插入后的最小值
     */

    // 链表 需要 一个 头指针，指向 头结点，才能 找到后面的整个链表！
    private Node head;

    public MinStack4() {

    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        // 删除 头结点 即可
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    static class Node {

        int val;
        int min;
        Node next;

        public Node(int val, int min) {
            this(val, min, null);
        }

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

}
