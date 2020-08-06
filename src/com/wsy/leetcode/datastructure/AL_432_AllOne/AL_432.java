package com.wsy.leetcode.datastructure.AL_432_AllOne;

public class AL_432 {


    /**
     * 432. 全 O(1) 的数据结构
     * 请你实现一个数据结构支持以下操作：
     * <p>
     * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
     * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
     * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
     * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
     * <p>
     * <p>
     * 挑战：
     * <p>
     * 你能够以 O(1) 的时间复杂度实现所有操作吗？
     */
    public static void main(String[] args) {

        test1();
        test2();
    }

    private static void test1() {

        long start = System.nanoTime();

        AllOne one = new AllOne();
        one.inc("a");

        one.inc("b");
        one.inc("b");

        one.inc("c");
        one.inc("c");
        one.inc("c");

        one.dec("b");
        one.dec("b");

        System.out.println(one.getMinKey());

        one.dec("a");

        System.out.println(one.getMaxKey());
        System.out.println(one.getMinKey());

        System.out.println((System.nanoTime() - start) + " ns");

    }

    private static void test2() {

        long start = System.nanoTime();

        AllOne2 one = new AllOne2();
        one.inc("a");

        one.inc("b");
        one.inc("b");

        one.inc("c");
        one.inc("c");
        one.inc("c");

        one.dec("b");
        one.dec("b");

        System.out.println(one.getMinKey());

        one.dec("a");

        System.out.println(one.getMaxKey());
        System.out.println(one.getMinKey());

        System.out.println((System.nanoTime() - start) + " ns");

    }

}
