package com.wsy.nowcoder.huawei.AL_108;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
     *
     * 输入描述:
     * 输入两个正整数A和B。
     *
     * 输出描述:
     * 输出A和B的最小公倍数。
     *
     * 示例1
     * 输入
     * 复制
     * 5 7
     * 输出
     * 复制
     * 35
     */

    /**
     * 求 两个数字的最小公倍数
     * <p>
     * a * b = 最小公倍数 * 最大公约数
     *
     * <p>
     * 1、最小公倍数 = 大数字 * 小数字 / 最大公约数
     * <p>
     * 2、最大公约数 的 求法 有两个：
     * todo ---> 都是 递归传入 小数字 和 相减/取模 后的数值
     * <p>
     * （1）更相减损法 --》传入 俩数字，求 最大公约数 就是
     * 大的数 - 小的数 = 差值；
     * 然后 递归 小的数，差值；
     * 直到 两数字 相等，就是 最大公约数
     * <p>
     * <p>
     * （2）辗转相除法
     * 就是 传入 a b，确保 a < b 时 ，要交换
     * 然后 求 c = a % b
     * c = 0 , b 就是
     * 否则，就是 递归 传入 b , c
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            // TODO: 2020-08-20 20:22:57 俩方法 都 ok ！
            System.out.println(a * b / dfs(a, b));
//            System.out.println(a * b / dfs2(a, b));
        }

    }

    // 2、辗转相除法
    private static int dfs2(int a, int b) {
        // 先调整位置
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 1、终止条件
        int c = a % b;
        if (c == 0) {
            return b;
        }

        // 2、处理当前层
        // 3、递归
        return dfs2(b, c);
    }

    // 1、求 a 和 b的最大公约数 --> 更相减损法
    private static int dfs(int a, int b) {
        // 1、终止条件
        if (a == b) {
            return a;
        }

        // 2、处理 当前层
        if (a > b) {
            // 3、递归
            return dfs(b, a - b);
        } else {
            return dfs(a, b - a);
        }

    }

}
