package com.wsy.leetcode.string.AL_14_longestCommonPrefix;

public class AL_14 {

    private static String[] arr = new String[]{"flower", "flow", "flight"};
    private static String[] arr2 = new String[]{"dog", "racecar", "car"};


    public static void main(String[] args) {
        System.out.println(new Official().longestCommonPrefix(arr));
        System.out.println(new Official().longestCommonPrefix(arr2));

        System.out.println(new Official().longestCommonPrefix2(arr));
        System.out.println(new Official().longestCommonPrefix2(arr2));

        System.out.println(new Official().longestCommonPrefix3(arr));
        System.out.println(new Official().longestCommonPrefix3(arr2));

        System.out.println(new Official().longestCommonPrefix4(arr));
        System.out.println(new Official().longestCommonPrefix4(arr2));


    }
}
