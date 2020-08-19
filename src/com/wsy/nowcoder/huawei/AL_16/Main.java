package com.wsy.nowcoder.huawei.AL_16;

import java.util.*;

public class Main {

    /**
     * 题目描述
     * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
     * 主件	附件
     * 电脑	打印机，扫描仪
     * 书柜	图书
     * 书桌	台灯，文具
     * 工作椅	无
     * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
     *     设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
     * v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
     *     请你帮助王强设计一个满足要求的购物单。
     *
     *
     *
     *
     * 输入描述:
     * 输入的第 1 行，为两个正整数，用一个空格隔开：N m
     *
     * （其中 N （ <32000 ）表示总钱数， m （ <60 ）为希望购买物品的个数。）
     *
     *
     * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
     *
     *
     * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
     *
     *
     *
     *
     * 输出描述:
     *  输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。
     * 示例1
     * 输入
     * 复制
     * 1000 5
     * 800 2 0
     * 400 5 1
     * 300 5 1
     * 400 3 0
     * 500 2 0
     * 输出
     * 复制
     * 2200
     */

    /**
     * 总钱数 N , 物品个数 m
     * 物品 ： 价格，重要度，所附属的主件的编号（是 主件 还是 附件。。）
     * <p>
     * 求在满足 消耗钱数 < N 的前提下，所买物品的 价格*重要度 之和的最大值
     * <p>
     * 有点 贪心的感觉，即 单位金钱 所产生的重要度 越大越好
     * --》但是，还要求 附件 必须依附于 主件
     * --》即 买附件，必须 买主件，但是 买主件 不一定要买 附件！
     * 感觉，枚举 回溯 会好做一些把。。
     * <p>
     * <p>
     * todo md，不知道 那里错了。。。没看出来
     * 两个小时 没做对，草！
     * <p>
     * todo 正确答案 --》看 MainDp ！
     */

    static int max = Integer.MIN_VALUE;
    static int money;
    static int count;
    static Goods[] goods;
    static Set<Integer> selected;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] a = scanner.nextLine().trim().split(" ");

        money = Integer.parseInt(a[0]);
        count = Integer.parseInt(a[1]);
        goods = new Goods[count];
        for (int i = 0; i < count; i++) {
            String[] a1 = scanner.nextLine().trim().split(" ");
            goods[i] = new Goods(Integer.parseInt(a1[0]), Integer.parseInt(a1[1]), Integer.parseInt(a1[2]));
        }

        // 读取完毕后，回溯处理
        max = Integer.MIN_VALUE;

        // 路径上 已经 选中的 物品的 index --》+1 是 主件id
        selected = new HashSet<>();

        dfs(0, 0, 0);

        System.out.println(max);
    }

    // 深度遍历
    private static void dfs(int index, int cost, int current) {
        // 1、终止条件
        if (index >= count || cost >= money) {
            return;
        }

        // 2、处理当前层

        // todo 2-2：想要 选中 当前物品，再 递归 --》先用 想选择 的好一些，后面 去重 有意义
        buyThis(index, cost, current);

        // todo 2-1：不想 选择 当前物品，直接 递归
        dfs(index + 1, cost, current);
    }

    private static void buyThis(int index, int cost, int current) {
        int[] addIndexes = {-1, -1};

        // 如果 之前 选过了，自己 这个物品 就不用 重复 add 了 --》肯定是 主件
        // 之前 没选中的情况
        if (!selected.contains(index)) {
            // 如果没有 被选过的话，这里 就处理
            Goods good = goods[index];

            // 还要 分情况：
            // 先 add 当前物品
            cost += good.price;
            // TODO: cost 判断 1
            if (cost > money) {
                return;
            }

            current = current + good.price * good.importance;

            // 当前物品 被选中
            selected.add(index);
            addIndexes[0] = index;

            // 1、主件，只 前面 add自己 就可以

            // 2、是 附件，要看 主件，没被add的时候 要 add 主件 一起计算；否则 不用
            if (good.host != 0) {
                int hostIndex = good.host - 1;

                if (!selected.contains(hostIndex)) {
                    // 还要满足 钱不能 超限额
                    Goods host = goods[hostIndex];
                    cost += host.price;

                    // TODO: cost 判断 2
                    if (cost > money) {
                        return;
                    }

                    current = current + host.price * host.importance;

                    selected.add(hostIndex);
                    addIndexes[1] = hostIndex;
                }
            }

            // 比较更新 max 值
//            System.out.println(selected.toString());
//            System.out.println("max = " + max + " , current = " + current);

            max = Math.max(max, current);
        }

        // 3、递归
        dfs(index + 1, cost, current);

        // 4、善后 --> 因为 在 本层 选中 而 增加的，要删掉
        if (addIndexes[0] != -1) {
            selected.remove(addIndexes[0]);
        }

        if (addIndexes[1] != -1) {
            selected.remove(addIndexes[1]);
        }

    }

    static class Goods {
        int price;
        int importance;
        // 对应 主件id --> -1 就是 index
        int host;

        public Goods(int price, int importance, int host) {
            this.price = price;
            this.importance = importance;
            this.host = host;
        }
    }

}
