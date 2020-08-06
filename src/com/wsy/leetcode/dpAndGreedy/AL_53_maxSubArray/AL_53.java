package com.wsy.leetcode.dpAndGreedy.AL_53_maxSubArray;

public class AL_53 {


    static int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

    public static void main(String[] args) {

        System.out.println(new Official().maxSubArray(a));
        System.out.println(new Official().maxSubArray2(a));
        System.out.println(new Official().maxSubArray3(a));

    }
}
