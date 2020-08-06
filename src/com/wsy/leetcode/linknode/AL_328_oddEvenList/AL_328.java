package com.wsy.leetcode.linknode.AL_328_oddEvenList;

public class AL_328 {

    /**
     * 将一个链表（下标从0开始）重新拼接，偶数下标的元素排在前面，奇数下标的元素排在后面
     * 原偶数下标的元素 和 原奇数下标的元素的 相对顺序不变。
     * 要求：O(1)空间复杂度。例如：
     * a->b->c->d->e->f->g->h->i
     * =>
     * a->c->e->g->i->b->d->f->h
     * class LinkedNode {
     * private int _value;
     * private LinkedNode next;
     * public LinkedNode(int value) {
     * _value = value;
     * }
     * }
     * void reconnect(LinkedNode head) {
     * }
     */
    public static void main(String[] args) {

        test();
//        test1();

//        test2();
    }


    private static void test() {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);
        LinkedNode node5 = new LinkedNode(5);
        LinkedNode node6 = new LinkedNode(6);
        LinkedNode node7 = new LinkedNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

//        System.out.println("原始链表 hashcode = " + node1.hashCode() + "\n" + node1);
//        new Official().reconnect(node1);
//        System.out.println("最后外面的node1 hashcode = " + node1.hashCode() + "\n" + node1);


//        new Official().reconnect(null);
        System.out.println(new Official().oddEvenList(null));
    }


    private static void test1() {
        Integer i = 0;

        tt(i);

        System.out.println("i = " + i);
    }

    private static void tt(int i) {

        i = 10;
    }


    /**
     * todo 深刻 认识 指针！！！
     * 传入的指针，在方法体内，只是给你 用它的引用地址
     * 但是，你是 修改不了 外面指针的 指向的！
     */
    private static void test2() {
        Node node = new Node();
        node.value = 10;
        System.out.println(node.hashCode());

        tt2(node);

        System.out.println(node.hashCode());

    }

    private static void tt2(Node node) {
        node = new Node();
        node.value = 9;

        System.out.println("in = " + node.hashCode());
    }


}

class Node {
    int value;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
