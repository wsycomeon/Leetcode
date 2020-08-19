package com.wsy.nowcoder.huawei.AL_10;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
     * 输入
     * abaca
     * 输出
     * 3
     * 输入描述:
     * 输入N个字符，字符在ACSII码范围内。
     * <p>
     * 输出描述:
     * 输出范围在(0~127)字符的个数。
     * <p>
     * 示例1
     * 输入
     * 复制
     * abc
     * 输出
     * 复制
     * 3
     */

    // 统计 0-127 AcsII表内 不同字符的个数 --> 其实 每个字符的大小 就是 一个 int 值，在 0-127 之间 那就是 acsii 码中的
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String s = scanner.nextLine();

//            dealOneStringWithBitSet(s);
            dealOneStringNormal(s);
        }

    }

    /**
     * 方法2：
     * 常规方法 记录 0-127 之间的 char 的个数
     */
    private static void dealOneStringNormal(String s) {
        HashSet<Integer> set = new HashSet();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 0 && c <= 127) {
                set.add((int) c);
            }
        }

        System.out.println(set.size());
    }

    /**
     * 方法1：取值范围 有限 的 去重问题，可以考虑 位图 BitSet
     * 这里，取值 是 0-127 取一个 128 大小的即可
     */
    private static void dealOneStringWithBitSet(String s) {
        BitSet set = new BitSet(128);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 之前 不存在 的话 false，那就 记录 对应 index 存在 --》为 true
            if (!set.get(c)) {
                set.set(c);
            }
        }

        // 输出 set 中 被设置为 true 的 位 的 个数 即可！
        System.out.println(set.cardinality());
    }


}
