package com.wsy.nowcoder.huawei.AL_15;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
     * <p>
     * 输入描述:
     * 输入一个整数（int类型）
     * <p>
     * 输出描述:
     * 这个数转换成2进制后，输出1的个数
     * <p>
     * 示例1
     * 输入
     * 复制
     * 5
     * 输出
     * 复制
     * 2
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine().trim());

        // 转换成 2 进制，看 位 = 1 的个数
        String s = Integer.toBinaryString(num);

        int count = 0;
        for (int i = 0; i < s.length(); i++) {

            // TODO: 2020-08-19 15:29:53 注意，是 等于 字符 1 --》 '1' !

            if (s.charAt(i) == '1') {
                count++;
            }
        }

        System.out.println(count);
    }


}
