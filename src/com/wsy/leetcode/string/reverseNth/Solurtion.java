package com.wsy.leetcode.string.reverseNth;

public class Solurtion {


    public static void main(String[] args) {


//        System.out.println(reverseKth("abcd123", 3));
//        System.out.println(reverseKth("abcd123", 10));
//        System.out.println(reverseKth("abcd123", 7));
//        System.out.println(reverseKth(null, 7));
        System.out.println(reverseKth("", 7));

    }


    /**
     * 将 原字符串 向右移动 k位
     * <p>
     * s = "abcd123" k = 3
     * Return "123abcd"
     * <p>
     * <p>
     * 1、实际上 类似于 链表 反转拼接
     * 将 每个字符 串联成 一个链表，然后 找到 目标节点，断开，拼接到 前面
     * todo 因为 此时是字符串，整体长度 O(1)可得，想要找到 目标节点 是很方便的 --》用链表 就很鸡肋了 ：要 双指针 遍历一遍，而且 空间复杂度 也高
     * <p>
     * 2、或者 一样要找到 目标节点
     * <p>
     * 然后 前、后区间 分别反转，然后 整个区间 进行反转。。。
     * 写一个string 的 区间 反转函数 就好了
     */
    public static String reverseKth(String src, int k) {
        // 特殊情况：不用处理
        if (src == null || src.length() == 0 || k % src.length() == 0) {
            return src;
        }

        // TODO: 2020/7/23 注意这里， k 可能比 length 还大，先求模
        int secondeStart = src.length() - k % src.length();

        // TODO: 2020/7/23 字符串 内部的 char[] 是不可变的，所以需要 用 sb 来搞

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            sb.append(src.charAt(i));
        }


        // 先 俩区间 局部 反转
        reverseRange(sb, 0, secondeStart - 1);
        reverseRange(sb, secondeStart, src.length() - 1);

        // 在 整个区间，一起反转
        reverseRange(sb, 0, src.length() - 1);

        return sb.toString();
    }

    private static void reverseRange(StringBuilder sb, int left, int right) {

        while (left < right) {
            // 两两对调
            char leftChar = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, leftChar);

            // 各进一步
            left++;
            right--;
        }

    }


}
