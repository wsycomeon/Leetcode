package com.wsy.nowcoder.huawei.AL_22;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
     * 输入描述:
     * 输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），表示小张手上的空汽水瓶数。n=0表示输入结束，你的程序不应当处理这一行。
     *
     * 输出描述:
     * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
     *
     * 示例1
     * 输入
     * 复制
     * 3
     * 10
     * 81
     * 0
     * 输出
     * 复制
     * 1
     * 5
     * 40
     */

    /**
     * 给定一个 初始的 空瓶数，求最多 能喝 多少瓶
     * 3换1，当 ==2 的时候，可以 申请 一个 空瓶，换一瓶喝掉 +1，再换一瓶 还给老板（不能喝了，不用加）
     * <p>
     * 依次读取 初始的 瓶数，处理每个数字；
     * == 0，退出
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int num = scanner.nextInt();

            if (num == 0) {
                break;
            }

            // 处理 每个 初始 空瓶数
            dealNum(num);
        }

    }

    /**
     * n个 空瓶，最多喝 多少瓶 汽水
     */
    private static void dealNum(int n) {
        int count = 0;

        while (n >= 3) {
            // 换一瓶
            n -= 3;
            // 喝
            count++;
            // 空瓶 +1
            n += 1;
        }

        if (n == 2) {
            count++;
        }

        System.out.println(count);
    }

}
