package com.wsy.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class Test {


    public static void main(String[] args) {


//        charTest();

//        toHex();

//        match();


//        split();

        testScanner();

    }

    private static void testScanner() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入的是 =" + scanner.next());
        // todo 1、scanner 一旦关闭，会把 System.in 一起关闭的
        // --》所以 慎用 close ，会导致 后面的 scanner2 都无效了，程序 直接 退出！
        scanner.close();

        Scanner scanner2 = new Scanner(System.in);



        // scanner2.hasNext() ; // 2、 --> 会维持 一直读取状态
        // 3、next() 读的是 任意类型的，从 第一个 非空、有效字符 开始 到 结束符（比如 空格）等 之间的 字符串
        // --》说不定 这个字符串 可以 转换成 数字 等 ！

        // 4、不是 int 就 把这个 错误缓存 读出来，方便 下一次 读到 int
        while (!scanner2.hasNextInt()) {
            String next = scanner2.next();
            System.out.println("scanner2 -->" + next);
        }

        // 5、hasNextLine() 则是 以 回车为结尾的，读取一行，包括 前面的 各种 空格字符

        boolean b = scanner2.hasNextLine();
        System.out.println("scanner2 =" + b);
    }

    private static void split() {
        String[] split = "19..0.~255.255.255.0".split("~");
        System.out.println(Arrays.toString(split));


//        String[] codeSplit = "19..0.".split("\\.");
//        for (int i = 0; i < codeSplit.length; i++) {
//            System.out.println(codeSplit[i].equals(""));
//        }
//        System.out.println(Arrays.toString(codeSplit));
    }

    private static void match() {
        System.out.println("1x20".matches("(1)+(0)+"));
        System.out.println("110".matches("(1)+(0)+"));
        System.out.println("11".matches("(1)+(0)+"));
        System.out.println("01".matches("(1)+(0)+"));
        System.out.println("100".matches("(1)+(0)+"));
        System.out.println("1100".matches("(1)+(0)+"));

    }

    private static void toHex() {
        String s = "0x12";
        System.out.println(Integer.parseInt(s.substring(2), 16));
    }


    // 字符比较
    private static void charTest() {
        System.out.println(Character.isAlphabetic('c'));
        System.out.println(Character.toUpperCase('c'));
        System.out.println(Character.isAlphabetic('C'));
        System.out.println(Character.isAlphabetic(' '));
        System.out.println(Character.isAlphabetic('1'));
    }
}
