package com.wsy.nowcoder.huawei.AL_2;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
     *
     * 输入描述:
     * 第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。
     *
     * 输出描述:
     * 输出输入字符串中含有该字符的个数。
     *
     * 示例1
     * 输入
     * 复制
     * ABCDEF
     * A
     * 输出
     * 复制
     * 1
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1、 直接将 字符串 的 字母字符 全部 改成大写
        String s = scanner.nextLine().toUpperCase();

        String s1 = scanner.nextLine();
        char c = s1.charAt(0);
        // 2、如果 c 是字母，直接 转成大写
        if (Character.isAlphabetic(c)) {
            c = Character.toUpperCase(c);
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        System.out.println(count);

    }

}
