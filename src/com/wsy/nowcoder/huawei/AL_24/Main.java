package com.wsy.nowcoder.huawei.AL_24;

import java.util.Scanner;

public class Main {


    /**
     * 题目描述
     * 计算最少出列多少位同学，使得剩下的同学排成合唱队形
     *
     * 说明：
     *
     * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
     * 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，   则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。
     *
     * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
     *
     *
     * 注意不允许改变队列元素的先后顺序
     * 请注意处理多组输入输出！
     *
     * 输入描述:
     * 整数N
     *
     * 输出描述:
     * 最少需要几位同学出列
     *
     * 示例1
     * 输入
     * 复制
     * 8
     * 186 186 150 200 160 130 197 200
     * 输出
     * 复制
     * 4
     */

    // TODO: 2020-08-20 16:01:06 10分钟 没看出来。求 最少出队人数，就是 组成 合唱队形的 最多保留人数

    // TODO: 2020-08-20 16:18:32 dp 做法

    /**
     * dp:
     * 将 最少 出队人数，转化成 最多 保留人数
     * 即 以 某个 index 对应的人 为 最高点，左边递增的人数 + 右边递减的人数 之和 最大
     * <p>
     * 1、拆分成 两部分 求和，即 遍历 两次 原数组
     * （1）左边：到 index 结束的 最长 递增 序列长度
     * dp[i] = max( dp[k] + 1 ) , dp[i] > dp[k] , k 从 1 到 i-1；
     * <p>
     * （2）右边：从 index 开始的 最长 递减 序列长度
     * dp[i] = max( dp[k] + 1 ) , dp[i] > dp[k] , k 从 最后 到 i+1
     * <p>
     * 2、然后，每个位置的 dp值 求和 再 -1（因为 自身 被加了 两次）；
     * 求 和的 最大值 就是 合唱团 最多保留人数；
     * <p>
     * 3、总人数 - 最多保留人数 --》 最少出队人数
     * <p>
     * <p>
     * 时间：O(n^2) --》每次 求dp数组的时候，双循环
     * 空间：O(n) --》俩 dp 数组，都是 n
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 0、接收 初始化数据

            // TODO: 2020-08-20 17:01:47 曹尼玛，举例子 看 是 分两行的，实际上 ，没说几行，反正 就 读数字 nextInt() 就行了！

            // 总人数
//            int count = Integer.parseInt(scanner.nextLine()); // 这里 不能这么用，草！

            int count = scanner.nextInt();
            if (count < 2) {
                System.out.println(0);
                return;
            }

            // 注意，这里为了 index 对齐方便
            int[] arr = new int[count + 1];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }

            // 1、分别 求两次 dp值 --》最长递增序列 和 最长递减序列
            int[] leftDp = getLongestIncreaseSequence(arr);
            int[] rightDp = getLongestDecreaseSequence(arr);

            // 2、遍历一遍，求 dp 和 -1 的最大值
            int max = 0;
            for (int i = 1; i < leftDp.length; i++) {
                max = Math.max(max, leftDp[i] + rightDp[i] - 1);
            }

            // 3、总人数 - max保留 就是 最后的结果 --》最少出队人数
            System.out.println(count - max);
        }

    }

    // 包括 当前元素 在内的 最长递减序列的长度，其实就是 逆序、递增
    private static int[] getLongestDecreaseSequence(int[] arr) {
        int[] dp = new int[arr.length];

        // 从后往前 填充
        for (int i = dp.length - 1; i >= 1; i--) {
            int max = 1;

            for (int j = dp.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }

            dp[i] = max;
        }


        return dp;
    }

    // 包括 当前元素 在内的 最长 递增序列的长度
    private static int[] getLongestIncreaseSequence(int[] arr) {
        int[] dp = new int[arr.length];

        // 依次 求 每个元素 对应的 lis 值
        for (int i = 1; i < arr.length; i++) {
            // 至少要 包括 自身 一个
            int max = 1;

            // 找到 比他小的数值的 dp值+1 和 max 比较 、更新
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }

            dp[i] = max;
        }

        return dp;
    }


}
