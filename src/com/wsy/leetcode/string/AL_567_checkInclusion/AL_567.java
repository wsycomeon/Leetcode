package com.wsy.leetcode.string.AL_567_checkInclusion;


/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2
 * 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class AL_567 {

    private static String s1 = "ab";

    private static String s2 = "eidbaooo";

    private static String s3 = "eidboaoo";

    public static void main(String[] args) {

        System.out.println(new Official().checkInclusion(s1, s2));

        System.out.println(new Official().checkInclusion(s1, s3));

        System.out.println(new Official().checkInclusion2(s1, s2));

        System.out.println(new Official().checkInclusion2(s1, s3));


        System.out.println(new Official().checkInclusion3(s1, s2));

        System.out.println(new Official().checkInclusion3(s1, s3));
    }
}
