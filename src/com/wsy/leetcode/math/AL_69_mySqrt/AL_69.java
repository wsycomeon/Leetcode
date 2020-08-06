package com.wsy.leetcode.math.AL_69_mySqrt;

public class AL_69 {


    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     */
    public static void main(String[] args) {

        int a = 4;

//        int a1 = 8;
        int a1 = 2147483647;


//        System.out.println(new Official().mySqrt(a));
//        System.out.println(new Official().mySqrt2(a));
//        System.out.println(new Official().mySqrt3(a));

//        System.out.println();

//        System.out.println(new Official().mySqrt(a1));
//        System.out.println(new Official().mySqrt2(a1));
        System.out.println(new Official().mySqrt3(a1));

        test1();
    }

    private static void test1() {
        int mid = 1073741823;
        int target = 2147483647;

        boolean b1 = mid * mid <= target;
        System.out.println("精度损失时 = " + b1);

        boolean b2 = (long) mid * mid <= target;
        System.out.println("long 型, 不损失 精度时 = " + b2);

    }
}
