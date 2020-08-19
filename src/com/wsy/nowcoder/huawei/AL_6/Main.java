package com.wsy.nowcoder.huawei.AL_6;

import java.util.Scanner;

public class Main {


    /**
     * 题目描述
     * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
     *
     * 最后一个数后面也要有空格
     *
     * 输入描述:
     * 输入一个long型整数
     *
     * 输出描述:
     * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
     *
     * 示例1
     * 输入
     * 复制
     * 180
     * 输出
     * 复制
     * 2 2 3 3 5
     */


    /**
     * 我曹，一个数字的 质因子。。。早就忘了
     * <p>
     * 质数 就是 素数，因数。。。
     * 合数 可以 拆解成 多个 质数，就是 质因子。
     * <p>
     * --》质数 是 只能被 自身 和 1，不能被 其他更小的数 整除的 数字
     * <p>
     * 最小的质数 是 2 --》1 不算
     * <p>
     * 质因子的 可能 取值范围 是 [2 , 自身]
     *
     * <p>
     * 所以，求解一个 合数的 所有 质因子，并从小到大 排列的方法就是：
     * i 从 2 开始 到 包括 自身，发现 当前值 对 i 取模 = 0，说明 i 就是 一个 质因子，
     * 输出，然后 cur /= i ，递归调用
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            getResult(scanner.nextLong());
        }

    }

    // 从小到大 输出 num 所有的 质因子
    private static void getResult(long num) {

        for (int i = 2; i <= num; i++) {

            // 能整除，就是 质因子
            if (num % i == 0) {
                System.out.print(i + " ");
                num = num / i;

                // 注意，num变更了 ，这里的 质因子 i 还是需要从 2 开始
                // 方法 1、又因为 处理一波 后面 就++，所以 i = 1，让下次的 i 还是 从 2 开始
//                i = 1;

                // 方法 2、或者 递归调用，换 新的 num 去调用这个方法，但是 这个 外循环 就要break了，后面 不能再处理饿了
                getResult(num);
                break;

            }

        }

    }


}
