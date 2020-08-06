package com.wsy.leetcode.dpAndGreedy.AL_221_maximalSquare;

/**
 * 221. 最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 */
public class AL_221 {

    static char[][] chars = new char[4][5];

    static {
        chars[0][0] = '1';
        chars[0][1] = '0';
        chars[0][2] = '1';
        chars[0][3] = '0';
        chars[0][4] = '0';

        chars[1][0] = '1';
        chars[1][1] = '0';
        chars[1][2] = '1';
        chars[1][3] = '1';
        chars[1][4] = '1';

        chars[2][0] = '1';
        chars[2][1] = '1';
        chars[2][2] = '1';
        chars[2][3] = '1';
        chars[2][4] = '1';

        chars[3][0] = '1';
        chars[3][1] = '0';
        chars[3][2] = '0';
        chars[3][3] = '1';
        chars[3][4] = '0';
    }

    public static void main(String[] args) {

        System.out.println(new Official().maximalSquare(chars));
        System.out.println(new Official().maximalSquare2(chars));

    }


}

