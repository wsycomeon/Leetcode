package com.wsy.nowcoder.huawei;

public class Test {


    public static void main(String[] args) {


//        charTest();

        toHex();

    }

    private static void toHex() {
        String s = "0x12";
        System.out.println(Integer.valueOf(s.substring(2), 16));
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
