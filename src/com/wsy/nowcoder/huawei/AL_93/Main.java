package com.wsy.nowcoder.huawei.AL_93;

import java.util.Scanner;

public class Main {


    /**
     * 题目描述
     * 编写一个函数，传入一个int型数组，返回该数组能否分成两组，使得两组中各元素加起来的和相等，并且，所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），能满足以上条件，返回true；不满足时返回false。
     * 输入描述:
     * 第一行是数据个数，第二行是输入的数据
     * <p>
     * 输出描述:
     * 返回true或者false
     * <p>
     * 示例1
     * 输入
     * 复制
     * 4
     * 1 5 -5 1
     * 输出
     * 复制
     * true
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // todo while 循环，处理 连续 多组 测试 数据
        while (scanner.hasNext()) {

            // TODO: 2020-08-20 19:49:06 曹尼玛，又是 老是 不输出 结果，还是 因为 多组测试样例 连续输入的原因！
            // 这里，王八蛋，又是 让 nextInt ，而不是 题目说的 “独立 一行”！
            int count = scanner.nextInt();
//            int count = Integer.parseInt(scanner.nextLine());

            int[] a = new int[count];
            for (int i = 0; i < count; i++) {
                a[i] = scanner.nextInt();
            }

            // 读取完毕后，处理
            dealOneArray(a);
        }

    }

    // 判断 该数组 是否 可以分为 两组数据
    // 5的倍数 只能放在 第一组，3的倍数 只能放在 第二组
    // 两组 之和 相等
    static boolean found;
    static int sum;
    static int count;
    static int[] array;

    private static void dealOneArray(int[] a) {
        sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        // 各组 目标和
        sum /= 2;

        array = a;
        count = a.length;

        found = false;

        dfs(0, 0, 0);

        System.out.println(found);
    }

    // 从 当前元素 开始 向下，放入 合适的组，求和，看是否 有 sum1 = sum2 = sum 的
    private static void dfs(int index, int sum1, int sum2) {
        // 1、终止条件
        if (index >= count) {
            // 发现 一例 符合 即可！
            if (sum1 == sum && sum2 == sum) {
                found = true;
            }
            return;
        }

        // 2、处理 当前层
        int num = array[index];

        // 3、递归
        if (num % 5 == 0) {
            // 只能 进入 组1
            dfs(index + 1, sum1 + num, sum2);
        } else if (num % 3 == 0) {
            // 只能 进入 组2
            dfs(index + 1, sum1, sum2 + num);
        } else {
            // 否则，组1、组2 随便选。两条路 都尝试 就好
            dfs(index + 1, sum1 + num, sum2);

            dfs(index + 1, sum1, sum2 + num);
        }

        // 4、善后，--》没有 修改 共享引用

    }


}
