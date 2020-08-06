package com.wsy.leetcode.datastructure.AL_432_AllOne;

import java.util.HashMap;
import java.util.HashSet;

public class AllOne2 {

    HashMap<String, ValueNode> keyMap;
    DoubleLinkedList valueNodeList;

    public AllOne2() {
        keyMap = new HashMap<>();
        valueNodeList = new DoubleLinkedList();
    }

    public void inc(String key) {

        // 1、没有过 这个key，插入 value = 1 的 node
        if (!keyMap.containsKey(key)) {

            // 链表表的 val 是 1,直接加入 即可
            if (valueNodeList.head.next.val == 1) {
                keyMap.put(key, valueNodeList.head.next);

                valueNodeList.head.next.keySet.add(key);
            } else {
                // 否则的话，new 一个 1 的node，插入 表头
                ValueNode newNode = new ValueNode(1, true);
                newNode.keySet.add(key);
                keyMap.put(key, newNode);

                valueNodeList.insertNodeAtNext(valueNodeList.head, newNode);
            }

            return;
        }

        // 2、有这个 key，node 变换成 +1 那个
        ValueNode oldNode = keyMap.get(key);
        int oldValue = oldNode.val;

        // todo 特殊情况：和我 相同 val值的 元素 只有自己，且 下一个node的值 不止 比我大 1
        if (oldNode.keySet.size() == 1 && oldNode.next.val != oldValue + 1) {
            // 只需要 node值+1 即可
            oldNode.val = oldValue + 1;
        } else {

            // 否则的话，从原值的 key 集合中，删除 自身的key
            oldNode.keySet.remove(key);

            // 如果，下一个key的值 就是 num + 1
            if (oldNode.next.val == oldValue + 1) {
                // 当前key的node 就是下个node了
                keyMap.put(key, oldNode.next);

                // 把我的key加到那个集合
                oldNode.next.keySet.add(key);
            } else {
                // 不止 大我一位，中间空缺了，新建一个
                ValueNode newNode = new ValueNode(oldValue + 1, true);
                newNode.keySet.add(key);
                // map也要放进去
                keyMap.put(key, newNode);

                // 将我这个node 插入之前的base之后
                valueNodeList.insertNodeAtNext(oldNode, newNode);
            }

            // todo 最后，如果 当前值的 node 没有key了，删掉即可
            if (oldNode.keySet.isEmpty())
                valueNodeList.deleteNode(oldNode);
        }


    }

    public void dec(String key) {
        // 1、没有 这个key
        if (!keyMap.containsKey(key))
            return;

        // 2、有这个key
        ValueNode oldNode = keyMap.get(key);
        int oldValue = oldNode.val;

        // 3、todo 特殊情况， node 只有自身 这么一个key
        if (oldNode.keySet.size() == 1) {
            // 看 前一个node的值
            oldValue--;

            // todo 特殊情况。-1 还没到 0，且 前面的那个值 不是 -1
            if (oldValue != 0 && oldNode.pre.val != oldValue) {
                // 没有紧邻的前一个node，这里 直接 修改值就好，不用删 这个node，也不需要 新建node
                oldNode.val = oldValue;

            } else {

                if (oldValue == 0) {
                    // 意味着 当前的key 应该被删掉（值 减少到0了）
                    keyMap.remove(key);

                    // 这个node 也没有其他key了，可以删掉了
                    valueNodeList.deleteNode(oldNode);
                } else {
                    // 恰好有 前一个值，加入前一个node
                    oldNode.pre.keySet.add(key);
                    keyMap.put(key, oldNode.pre);

                    // 无用node 还是要删掉
                    valueNodeList.deleteNode(oldNode);
                }

            }

            return;
        }


        // 4、即 当前 value，不止 这么一个key，这个node 删不掉
        oldNode.keySet.remove(key);
        oldValue--;

        if (oldValue == 0) {
            // 说明本身 value=1，减成0，就可以删了
            keyMap.remove(key);

        } else {
            // 否则，找到 前一个值的node 插入
            if (oldNode.pre.val == oldValue) {
                oldNode.pre.keySet.add(key);

                keyMap.put(key, oldNode.pre);
            } else {
                // 没有 前一个node
                ValueNode newNode = new ValueNode(oldValue, true);
                newNode.keySet.add(key);
                keyMap.put(key, newNode);

                valueNodeList.insertNodeAtPre(oldNode, newNode);
            }
        }

    }

    public String getMaxKey() {
        return valueNodeList.tail.pre.keySet == null ? "" :
                valueNodeList.tail.pre.keySet.iterator().next();
    }

    public String getMinKey() {
        return valueNodeList.head.next.keySet == null ? "" :
                valueNodeList.head.next.keySet.iterator().next();
    }

    /**
     * 双端队列
     * 有 head、tail 哨兵
     */
    static class DoubleLinkedList {

        ValueNode head, tail;

        public DoubleLinkedList() {
            head = new ValueNode(0, false);
            tail = new ValueNode(0, false);

            head.next = tail;
            tail.pre = head;
        }

        /**
         * 在base前插入节点
         */
        public void insertNodeAtPre(ValueNode base, ValueNode node) {
            node.pre = base.pre;
            node.next = base;
            base.pre.next = node;
            base.pre = node;
        }

        /**
         * 在base后插入节点
         */
        public void insertNodeAtNext(ValueNode base, ValueNode node) {
            node.next = base.next;
            node.pre = base;
            base.next.pre = node;
            base.next = node;
        }

        public void deleteNode(ValueNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

    }

    static class ValueNode {
        ValueNode pre, next;
        // 这个node 的 数值
        int val;

        // 存放 指定val的那些 key
        // key 多次 inc，dec的时候，value会变，找到合适的node，加进来 就好
        HashSet<String> keySet;

        public ValueNode(int val, boolean isData) {
            this.val = val;

            if (isData)
                this.keySet = new HashSet<>();
        }
    }

}
