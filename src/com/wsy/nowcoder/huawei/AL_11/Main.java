package com.wsy.nowcoder.huawei.AL_11;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 描述：
     * <p>
     * 输入一个整数，将这个整数以字符串的形式逆序输出
     * <p>
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     * <p>
     * <p>
     * 输入描述:
     * 输入一个int整数
     * <p>
     * 输出描述:
     * 将这个整数以字符串的形式逆序输出
     * <p>
     * 示例1
     * 输入
     * 复制
     * 1516000
     * 输出
     * 复制
     * 0006151
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        getReverseNum(scanner.nextLine());
    }

    // 逆序输出 这个整数的字符串 --》或者 模 除 方法。。
    private static void getReverseNum(String num) {
        StringBuilder sb = new StringBuilder();

        for (int i = num.length() - 1; i >= 0; i--) {
            sb.append(num.charAt(i));
        }

        System.out.println(sb.toString());
    }

}
