package com.wsy.leetcode.string.AL_151_reverseWords;

public class AL_151 {


    private static String s = "  hello world!  ";
    private static String s1 = "the sky is blue";
    private static String s2 = "a good   example";

    public static void main(String[] args) {

        System.out.println(new Official().reverseWords(s));
        System.out.println(new Official().reverseWords(s1));
        System.out.println(new Official().reverseWords(s2));


        System.out.println(new Official().reverseWords2(s));
        System.out.println(new Official().reverseWords2(s1));
        System.out.println(new Official().reverseWords2(s2));


        System.out.println(new Official().reverseWords3(s));
        System.out.println(new Official().reverseWords3(s1));
        System.out.println(new Official().reverseWords3(s2));


    }

}
