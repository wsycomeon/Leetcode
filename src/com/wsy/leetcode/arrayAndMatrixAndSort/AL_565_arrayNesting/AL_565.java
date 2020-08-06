package com.wsy.leetcode.arrayAndMatrixAndSort.AL_565_arrayNesting;

public class AL_565 {


    /**
     * 565. 数组嵌套
     * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
     * <p>
     * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: A = [5,4,0,3,1,6,2]
     * 输出: 4
     * 解释:
     * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
     * <p>
     * 其中一种最长的 S[K]:
     * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
     * <p>
     * <p>
     * 提示：
     * <p>
     * N是[1, 20,000]之间的整数。
     * A中不含有重复的元素。
     * A中的元素大小在[0, N-1]之间。
     */
    public static void main(String[] args) {

        int[] a = {5, 4, 0, 3, 1, 6, 2};

        System.out.println(new Official().arrayNesting1(a));
        System.out.println(new Official().arrayNesting2(a));
        System.out.println(new Official().arrayNesting(a));

    }


}
