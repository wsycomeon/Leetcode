package com.wsy.leetcode.datastructure.AL_146_LRUCache;


public class AL_146 {

    /**
     * 146. LRU缓存机制
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
     * <p>
     * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
     * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * <p>
     * <p>
     * <p>
     * 进阶:
     * <p>
     * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
     */
    public static void main(String[] args) {

        test1();

        System.out.println();
        test1_2();

        System.out.println();
        test2();

        System.out.println();
        test2_2();

    }

    private static void test1() {

        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));     // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));   // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废

        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

    private static void test2() {

        LRUCache cache = new LRUCache(1 /* 缓存容量 */);

        cache.put(2, 1);
        System.out.println(cache.get(2));     // 返回  1
        cache.put(3, 2);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));   // 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回 2
    }


    private static void test1_2() {

        LRUCache2 cache = new LRUCache2(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));     // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));   // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废

        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

    private static void test2_2() {

        LRUCache2 cache = new LRUCache2(1 /* 缓存容量 */);

        cache.put(2, 1);
        System.out.println(cache.get(2));     // 返回  1
        cache.put(3, 2);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));   // 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回 2
    }
}
