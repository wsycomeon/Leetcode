package com.wsy.leetcode.dpAndGreedy.AL_120_minimumTotal;

import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 * <p>
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * https://leetcode-cn.com/problems/triangle/
 */
public class AL_120 {


    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> a1 = new ArrayList<>();
        a1.add(2);
        List<Integer> a2 = new ArrayList<>();
        a2.add(3);
        a2.add(4);
        List<Integer> a3 = new ArrayList<>();
        a3.add(6);
        a3.add(5);
        a3.add(7);
        List<Integer> a4 = new ArrayList<>();
        a4.add(4);
        a4.add(1);
        a4.add(8);
        a4.add(3);

        lists.add(a1);
        lists.add(a2);
        lists.add(a3);
        lists.add(a4);

        System.out.println(new Official().minimumTotal(lists));

        System.out.println(new Official().minimumTotal3(lists));
        System.out.println(new Official().minimumTotal3_2(lists));

        System.out.println(new Official().minimumTotal4(lists));
        System.out.println(new Official().minimumTotal4_2(lists));
    }

    private static void test2() {
        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> a1 = new ArrayList<>();
        a1.add(-1);
        List<Integer> a2 = new ArrayList<>();
        a2.add(2);
        a2.add(3);
        List<Integer> a3 = new ArrayList<>();
        a3.add(1);
        a3.add(-1);
        a3.add(-3);

        lists.add(a1);
        lists.add(a2);
        lists.add(a3);

//        System.out.println(new Official().minimumTotal(lists));

        System.out.println(new Official().minimumTotal3(lists));
        System.out.println(new Official().minimumTotal3_2(lists));


        System.out.println(new Official().minimumTotal4(lists));
        System.out.println(new Official().minimumTotal4_2(lists));
    }
}
