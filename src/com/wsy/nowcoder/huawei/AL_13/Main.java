package com.wsy.nowcoder.huawei.AL_13;

import java.util.Scanner;

public class Main {


    /**
     * 英文语句 逆序，单词不用颠倒，且单词之间 以 一个空格 隔开
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        // 翻转一个英文语句
        String[] a = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = a.length - 1; i >= 0; i--) {
            sb.append(a[i]);

            if (i != 0) {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }

}
