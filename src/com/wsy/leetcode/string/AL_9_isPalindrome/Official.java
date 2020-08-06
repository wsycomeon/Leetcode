package com.wsy.leetcode.string.AL_9_isPalindrome;

public class Official {


    public static void main(String[] args) {
        int x = 1234243;
        int i = reverseX(x);

        System.out.println(x + " reverse to " + i);
    }

    /**
     * 反转一个数字 x
     * 其实就是 循环操作
     * （1）求模 获得最小值，保存到 reverse --》具体看下面 代码
     * （2）x / 10
     * <p>
     * ---
     * 一个数字 x
     * 求 末位数字 就是 % 10
     * 删除 末位数字 就是 / 10
     */
    public static int reverseX(int x) {
        int reverse = 0;

        while (x != 0) {
            // 将 当前x的 最末位 保存到 reverse的末位，原 reverse 要进位
            reverse = reverse * 10 + x % 10;

            // x 删除 最末位！
            x = x / 10;
        }

        return reverse;
    }


    /**
     * 判断一个数 是否是 回文数
     * <p>
     * 题目，已经给了 详细的解释，将几个 不是回文数的情况 列了出来：
     * 1、负数
     * 2、10的倍数
     * <p>
     * <p>
     * 其实，比较简单的办法：
     * 方法0：
     * 就是把 数字转成数组，然后 双指针 从两端 向内，判断 每个数字是否相等即可；
     * <p>
     * 但是 空间复杂度是 O(N)。本题要求 O(1) 的 空间复杂度，换其他思路
     * <p>
     * 方法1：
     * 将一个数字 x 反转成 reverse 来比对 (但是 反转后的数字 可能 溢出，所以 可以 只 反转一半)，
     * 当反转到一半的时候，即 x > reverse 不在 成立时，
     * 对比 二者 是否相等 或者 x = reverse / 10 --> 此时 原数字x的位数 是 奇数。。。
     */
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {
            return false;
        }

        // 以下 是正常数字
        int reverse = 0;

        while (x > reverse) {
            // 1、reverse 拼接 x当前的 最末位
            reverse = reverse * 10 + x % 10;

            // 2、 x 删除 最末位
            x /= 10;
        }

        // 跳出循环后，reverse的位数 可能相同 ，也可能多一位，
        // 所以 若是回文，需要满足如下条件

        return x == reverse || x == reverse / 10;
    }


}
