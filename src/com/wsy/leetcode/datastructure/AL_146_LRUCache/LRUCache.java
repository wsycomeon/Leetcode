package com.wsy.leetcode.datastructure.AL_146_LRUCache;

import java.util.HashMap;

public class LRUCache {

    /**
     * todo 终于 自己单独 写对了一个。。。
     * 思路都是ok的
     * 但是，两个问题：
     * <p>
     * 1、bug 太多，漏了太多细节，最后 反复调试 才成功；
     * 2、还有 优化空间，比如 head、tail 这俩节点的判空 很麻烦，
     * 可以 直接 利用 哨兵 占位思想（无效元素 占个位置，方便 处理 边界条件）
     * 链表中 也常用！
     * <p>
     * <p>
     * 其实 java中的 LinkedHashMap 就实现了 lruCache。。。
     * 以后开发过程中，可以直接使用，不用 重复 造轮子了！
     */
    private HashMap<Integer, Node> map = new HashMap();
    private int size = 0;
    private int maxSize;

    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        maxSize = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;

        // 如果找的到，就把他移动到 head

        // 先处理 pre
        Node pre = node.pre;
        if (pre != null) {
            // node 不是头结点，需要提到 head，所以 链表 顺序 要改变
            pre.next = node.next;
            node.pre = null;

            Node next = node.next;
            if (next != null) { // 自身 不是尾节点
                next.pre = pre;
            } else {
                // 自身是尾节点，那就重新指向pre
                tail = pre;
            }

            // 在处理自身
            head.pre = node;
            node.next = head;
            head = node;

        } else {
            // 没有 pre，表示 node 就是 head，不需要任何处理

        }

        return node.val;
    }

    public void put(int key, int value) {
        int i = get(key);

        // 找的到，且已经 放到对头了，直接更新它的数值
        if (i != -1) {
            head.val = value;
            return;
        }

        // 没找到的话
        if (size >= maxSize) {
            // 如果 之前满额了，先删除 最旧的 tail
            if (tail != null) {
                // todo map 也要移除呀
                Node node = map.remove(tail.key);
//                System.out.println("node = " + node + " , map size = " + map.size());

                Node pre = tail.pre;

                if (pre != null) {
                    pre.next = null;
                } else {
                    // tail 也是 head，再删就没了。。
                    head = null;
                }

                tail.pre = null;
                tail = pre;
                size--;
            } else {
                // tail 空，没办法处理

            }

        }


        // 插入头结点
        Node node = new Node(value, key, null, head);
        map.put(key, node);
        if (head != null) {
            head.pre = node;
        }
        head = node;
        size++;

        // 只有一个元素的话，那也是tail
        if (size == 1) {
            tail = head;
        }

    }

    static class Node {
        private int val;
        private int key;
        private Node pre;
        private Node next;

        public Node(int val, int key, Node pre, Node next) {
            this.val = val;
            this.key = key;
            this.pre = pre;
            this.next = next;
        }
    }


}
