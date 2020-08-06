package com.wsy.leetcode.dpAndGreedy.AL_354_maxEnvelopes;


/**
 * 354. 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class AL_354 {


    public static void main(String[] args) {


        test1();

        System.out.println();

//        test2();

    }

    private static void test1() {
        int[][] a = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(new Official().maxEnvelopes(a));


        int[][] a2 = {{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}};
        System.out.println(new Official().maxEnvelopes(a2));

        int[][] a3 = {{1,2},{2,3},{3,4},{3,5},{4,5},{5,5},{5,6},{6,7},{7,8}};

        System.out.println(new Official().maxEnvelopes(a3));
    }

    private static void test2() {
        int[][] a = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(new Official().maxEnvelopes2(a));

        int[][] a2 = {{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}};
        System.out.println(new Official().maxEnvelopes2(a2));
    }


}
