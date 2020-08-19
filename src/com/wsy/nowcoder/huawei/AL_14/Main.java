package com.wsy.nowcoder.huawei.AL_14;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 给定n个字符串，请对n个字符串按照字典序排列。
     * 输入描述:
     * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
     * 输出描述:
     * 数据输出n行，输出结果为按照字典序排列的字符串。
     * 示例1
     * 输入
     * 复制
     * 9
     * cap
     * to
     * cat
     * card
     * two
     * too
     * up
     * boat
     * boot
     * 输出
     * 复制
     * boat
     * boot
     * cap
     * card
     * cat
     * to
     * too
     * two
     * up
     */

    /**
     * n 行 字符串，每行字符串 都只有 大小写字母
     * 将 这 n行 字符串 按照 字母顺序 排序，依次输出
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // TODO: 2020-08-19 15:23:20 注意，最好都用 nextLine 去读，自己处理 变换
//        int count = scanner.nextInt();
        int count = Integer.parseInt(scanner.nextLine().trim());

        String[] a = new String[count];
        for (int i = 0; i < count; i++) {
            a[i] = scanner.nextLine();
        }

        // 自带 排序
        Arrays.sort(a);

        for (String s : a) {
            System.out.println(s);
        }

    }


}
