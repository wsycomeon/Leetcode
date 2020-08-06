package com.wsy.leetcode.dpAndGreedy.offer.AL_08_climbStairs;

public class AL_08 {


    /**
     * 面试题 08.01. 三步问题
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     * <p>
     * 示例1:
     * <p>
     * 输入：n = 3
     * 输出：4
     * 说明: 有四种走法
     * 示例2:
     * <p>
     * 输入：n = 5
     * 输出：13
     * 提示:
     * <p>
     * n范围在[1, 1000000]之间
     * <p>
     * 比较简单的 动态规划了
     */
    public static void main(String[] args) {

        System.out.println(new Official().waysToStep(100));
        System.out.println(new Official().climbStairs(100));

    }
}
