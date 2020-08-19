package com.wsy.nowcoder.huawei.AL_12;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
     * <p>
     * 输入描述:
     * 输入N个字符
     * <p>
     * 输出描述:
     * 输出该字符串反转后的字符串
     * <p>
     * 示例1
     * 输入
     * 复制
     * abcd
     * 输出
     * 复制
     * dcba
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        System.out.println(sb.toString());
    }

}
