package com.wsy.nowcoder.huawei.AL_4;

import java.util.Scanner;

public class Main {


    /**
     * 题目描述
     * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * 输入描述:
     * 连续输入字符串(输入2次,每个字符串长度小于100)
     *
     * 输出描述:
     * 输出到长度为8的新字符串数组
     *
     * 示例1
     * 输入
     * 复制
     * abc
     * 123456789
     * 输出
     * 复制
     * abc00000
     * 12345678
     * 90000000
     */

    /**
     * 连续 输入 两个字符串，
     * 每个字符串 拆分成 多个 8长度的字符串 --》不够的 补0 到8
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 明确 说了，有 两次输入
        dealOneString(scanner.nextLine().trim());
        dealOneString(scanner.nextLine().trim());
    }

    private static void dealOneString(String s) {
        int length = s.length();
        if (length == 0) {
            return;
        }

        // 求 补0 个数
        int count = length % 8 == 0 ? 0 : 8 - length % 8;

        if (count != 0) {
            StringBuilder sb = new StringBuilder(s);

            for (int i = 0; i < count; i++) {
                sb.append(0);
            }

            s = sb.toString();
        }

        // s 此时长度 必然是 8 的 整数倍
        count = s.length() / 8;

        for (int i = 0; i < count; i++) {
            System.out.println(s.substring(i * 8, i * 8 + 8));
        }

    }

}
