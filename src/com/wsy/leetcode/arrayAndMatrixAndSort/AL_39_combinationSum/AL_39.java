package com.wsy.leetcode.arrayAndMatrixAndSort.AL_39_combinationSum;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class AL_39 {


    public static void main(String[] args) {

        test1();
        test2();

        System.out.println();


        test1_2();

        test2_2();

    }

    private static void test1() {
        int[] a = {6, 7, 2, 3};
        int target = 7;

        System.out.println(new Official().combinationSum(a, target));

    }

    private static void test1_2() {
        int[] a = {5, 3, 2};
        int target = 8;

        System.out.println(new Official().combinationSum(a, target));

    }

    private static void test2() {
        int[] a = {6, 7, 2, 3};
        int target = 7;

        System.out.println(new Official().combinationSum2(a, target));

    }

    private static void test2_2() {
        int[] a = {5, 3, 2};
        int target = 8;

        System.out.println(new Official().combinationSum2(a, target));

    }

}
