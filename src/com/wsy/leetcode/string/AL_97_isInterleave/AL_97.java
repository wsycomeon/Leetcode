package com.wsy.leetcode.string.AL_97_isInterleave;

public class AL_97 {


    /**
     * 97. 交错字符串
     * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     */
    public static void main(String[] args) {

        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        System.out.println(new Official().isInterleave(s1, s2, s3));
        System.out.println(new Official().isInterleave2(s1, s2, s3));
        System.out.println(new Official().isInterleave3(s1, s2, s3));


    }


}
