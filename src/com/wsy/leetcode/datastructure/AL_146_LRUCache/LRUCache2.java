package com.wsy.leetcode.datastructure.AL_146_LRUCache;

import java.util.HashMap;

public class LRUCache2 {

    /**
     * 官方的优化版本：
     * 利用 无效的 head、tail 占位思想 -->
     * 任何一个 有效节点， 都会有 pre 和 next 节点，就不需要考虑 许多边界条件 和 头、尾的更新了
     * <p>
     * 时间：O(1)
     * 空间：O(cap) --》多了cap的哈希表，用来快速 查找。。
     * <p>
     * LinkedHashMap 本身实现了 lruCache 。。。。
     */
    HashMap<Integer, Node> map;
    int cap;
    int size;
    Node head;
    Node tail;

    public LRUCache2(int capacity) {
        map = new HashMap<>();

        cap = capacity;
        size = 0;

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        // 否则的话，提到 链表最前面
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        // 用前面的方法，get的时候，若有node，直接提到 最前面了，下面 更新数值 就可以了
        int i = get(key);

        if (i != -1) {
            head.next.value = value;
            return;
        }

        // 不存在的话，先插入链表头
        Node node = new Node(value, key);
        addToHead(node);
        map.put(key, node);
        size++;

        // 然后 如果超了的话，删除 tail 的
        if (size > cap) {
            removeTail();
            size--;
        }

    }

    /**
     * 因为 假head的存在，这里非常方便了
     */
    private void addToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    /**
     * 删除一个节点很简单，只要 把指向它的 前、后指针 干掉就好了
     * 而且，这里的 head、tail 指针 永远 指向 俩无效节点，不会指向 有效节点，就不用 处理他们
     */
    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void removeTail() {
        // 找到 真实的尾节点 删除即可
        Node node = tail.pre;

        //todo 不要忘记 删 map！！！
        map.remove(node.key);

        removeNode(node);
    }

    static class Node {
        int value;
        int key;
        Node pre;
        Node next;

        // 给 无效 节点 用
        public Node() {

        }

        public Node(int value, int key) {
            this.value = value;
            this.key = key;
        }
    }

}
