package com.wsy.nowcoder.huawei.AL_3;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    /**
     * 题目描述
     * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
     *
     *
     *
     * Input Param
     *
     * n               输入随机数的个数
     *
     * inputArray      n个随机整数组成的数组
     *
     *
     * Return Value
     *
     * OutputArray    输出处理后的随机整数
     *
     *
     *
     * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
     *
     * 样例输入解释：
     * 样例有两组测试
     * 第一组是3个数字，分别是：2，2，1。
     * 第二组是11个数字，分别是：10，20，40，32，67，40，20，89，300，400，15。
     *
     * 输入描述:
     * 输入多行，先输入随机整数的个数，再输入相应个数的整数
     *
     * 输出描述:
     * 返回多行，处理后的结果
     *
     * 示例1
     * 输入
     * 复制
     * 3
     * 2
     * 2
     * 1
     * 11
     * 10
     * 20
     * 40
     * 32
     * 67
     * 40
     * 20
     * 89
     * 300
     * 400
     * 15
     * 输出
     * 复制
     * 1
     * 2
     * 10
     * 15
     * 20
     * 32
     * 40
     * 67
     * 89
     * 300
     * 400
     */

    /**
     * 输入 有多组数据 --》需要 自己 计数 处理 --》读完一组数 就处理，然后 一行一行的打印结果 相当于返回
     * 然后 处理 下一组 数据，直到结束
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        while (scanner.hasNext()) {
            // 本组 数据个数
            count = Integer.parseInt(scanner.nextLine().trim());

            // 这里 不用 list，直接用 array 就好了
//            List<Integer> list = new ArrayList<>(count);
            int[] a = new int[count];

            // 读入 list
            for (int i = 0; i < count; i++) {
                a[i] = Integer.parseInt(scanner.nextLine().trim());
//                a[i] = scanner.nextInt();
            }

            // 统一处理
            dealOneList(a);
        }


    }

    // 去重、排序后 打印
    private static void dealOneList(int[] array) {
        Arrays.sort(array);

        System.out.println(array[0]);

        if (array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                continue;
            }

            System.out.println(array[i]);
        }

    }

}
