package com.wsy.nowcoder.huawei.AL_38;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半; 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
     *
     * 最后的误差判断是小数点6位
     *
     *
     *
     * 输入描述:
     * 输入起始高度，int型
     *
     * 输出描述:
     * 分别输出第5次落地时，共经过多少米第5次反弹多高
     *
     * 示例1
     * 输入
     * 复制
     * 1
     * 输出
     * 复制
     * 2.875
     * 0.03125
     */

    /**
     * 给定 初始高度，
     * 求 落地 5次后，一共经过多少米；之后 弹起 多高；
     * <p>
     * 每次落地，高度为 之前的一半；
     * 误差 为 小数点 6位；
     */
    public static void main(String[] args) {
        fun1();
//        test();
    }

    private static void fun1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            dealOneHeight(scanner.nextInt(), 5);
        }
    }

    // TODO: 2020-08-20 20:57:03 定义为 float 或者 double 等 浮点类型 ！
    private static void dealOneHeight(int height, int num) {
        int count = 0;

        double temp = height;
        double sum = 0;

        while (count < num) {
            count++;

            // 下降的高度
//            sum += temp;
            sum += temp * 2;

            // 高度 降一半
            temp = temp / 2;

            // TODO: 2020-08-20 21:15:11 --> 麻痹！这 精度问题 真坑爹！
            // 一样 正确的逻辑，就是因为 算法不同，精度 就不一样！
            // 我这里，是 初始 弹起高度 不加，精准计算，反而 不对 ！

            // md，它先加上 初始弹起，每次算两倍，最后再减去，反而 对了 ！

//            if (count != num - 1) { // 最后一次，不加 弹起的高度
//                // 弹起的高度
//                sum += temp;
//            }
        }

        sum -= height;

        // TODO: 2020-08-20 20:55:51 小数点后 取6位 输出的方式！
        System.out.printf("%.6f\n", sum);
        System.out.printf("%.6f\n", temp);
    }


    public static void test() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();

            double sum = 0;
            double temp = n;

            for (int i = 0; i < 5; i++) {
                sum += temp * 2;
                temp = temp / 2;
            }

            //第一次按它先弹上来再掉下去算的，要减掉第一次弹上来的路程
            sum -= n;
            System.out.printf("%.6f\n", sum);
            System.out.printf("%.6f\n", temp);
        }
    }


}
