package com.wsy.leetcode.string.AL_43_multiply;

public class AL_43 {


    static String s1 = "0";
    static String s2 = "";
    static String s3 = "2";
    static String s4 = "3";
    static String s5 = "123";
    static String s6 = "456";
    static String s7 = "996";
    static String s8 = "99";

    public static void main(String[] args) {

        System.out.println("final = " + new Official().multiply(s2, s2));
        System.out.println("final = " + new Official().multiply(s1, s2));

        System.out.println("final = " + new Official().multiply(s3, s4));
        System.out.println("final = " + new Official().multiply(s5, s6));

        System.out.println("final = " + new Official().multiply(s7, s7));
        System.out.println("final = " + new Official().multiply(s8, s8));
    }
}
