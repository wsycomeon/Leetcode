package com.wsy.nowcoder.huawei;

import java.util.Arrays;

public class Test {


    public static void main(String[] args) {


//        charTest();

//        toHex();

//        match();


        split();
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
