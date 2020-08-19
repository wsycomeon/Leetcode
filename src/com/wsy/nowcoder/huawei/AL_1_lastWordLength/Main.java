package com.wsy.nowcoder.huawei.AL_1_lastWordLength;

import java.util.Scanner;

public class Main {

    // TODO: 2020-08-19 10:25:37 我曹！牛客网 这傻逼

    /**
     * 1、要自己 导入 需的 类
     * 2、要以 Main类 命名，main方法运行
     * 3、不是 leetcode 那样 标准的 fun 输入，输出返回
     * --》以 Scanner 为 标准输入 的读取方式
     * --》sout 打印 输出结果 为 标准输出 方式！
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String[] arr = s.split(" ");
        int length = arr[arr.length - 1].length();
        System.out.println(length);
    }


    public int lastWordLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String[] a = s.split(" ");
        String s1 = a[a.length - 1].trim();
        return s1.length();
    }

}
