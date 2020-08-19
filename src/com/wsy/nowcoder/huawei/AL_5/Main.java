package com.wsy.nowcoder.huawei.AL_5;

import java.util.Scanner;

public class Main {


    /**
     * 题目描述
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
     *
     * 输入描述:
     * 输入一个十六进制的数值字符串。
     *
     * 输出描述:
     * 输出该数值的十进制字符串。
     *
     * 示例1
     * 输入
     * 复制
     * 0xA
     * 输出
     * 复制
     * 10
     */

    // https://blog.csdn.net/jdsjlzx/article/details/45536411

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();

            // 去掉前两个字符 0x，开始读 后面的数字 字符串，用 16进制 解析成 10 进制 输出 --》注意 进制范围是 2 - 36
//            Integer.valueOf(s.substring(2),16);

            System.out.println(Integer.parseInt(s.substring(2),16));
        }

    }

}
