package com.wsy.nowcoder.huawei.AL_9;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
     *
     * 输入描述:
     * 输入一个int型整数
     *
     * 输出描述:
     * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
     *
     * 示例1
     * 输入
     * 复制
     * 9876673
     * 输出
     * 复制
     * 37689
     */

    /**
     * 将 一个数字 逆序读，去除 重复 数字，返回
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            // 逆序 读 这个num，就是 每次 读最后一位，
            // 1、可以 求模，在除 就好，
            // 2、或者 用 字符串 index 逆序读 就好
            // 去除 重复数字，用set记录
            dealOneNum(num);
        }

    }

    private static void dealOneNum(int num) {
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> set = new HashSet<>();

        while (num != 0) {
            // 求出 最后一位 数字
            int i = num % 10;
            // 减小，进位
            num = num / 10;

            // 判断 当前 最后一位 数字，是否 之前 记录过
            if (!set.contains(i)) {
                // 没记录过，就记录下，拼接到 sb
                set.add(i);
                sb.append(i);
            }

        }

        // 返回 最后结果
        System.out.println(sb.toString());
    }

}
