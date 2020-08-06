package com.wsy.leetcode.string.AL_3_lengthOfLongestSubstring;

public class AL_3 {


    private static String s = "abcabcbb";
    private static String s2 = "bbbbb";
    private static String s3 = "pwwkew";

    public static void main(String[] args) {

        System.out.println(new Official().lengthOfLongestSubstring(s));
        System.out.println(new Official().lengthOfLongestSubstring(s2));
        System.out.println(new Official().lengthOfLongestSubstring(s3));

    }


}
