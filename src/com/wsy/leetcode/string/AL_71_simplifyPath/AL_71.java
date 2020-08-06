package com.wsy.leetcode.string.AL_71_simplifyPath;

public class AL_71 {


    static String test = "/a//b////c/d//././/..";
    static String test2 = "/a/../../b/../c//.//";
    static String test3 = "/home//foo/";

    public static void main(String[] args) {

        System.out.println(new Offcial().simplifyPath(test));
        System.out.println(new Offcial().simplifyPath(test2));
        System.out.println(new Offcial().simplifyPath(test3));
    }
}
