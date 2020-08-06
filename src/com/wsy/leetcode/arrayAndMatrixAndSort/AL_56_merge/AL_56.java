package com.wsy.leetcode.arrayAndMatrixAndSort.AL_56_merge;

import java.util.Arrays;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class AL_56 {


    public static void main(String[] args) {

        int[][] a = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] a2 = {{1, 4}, {4, 5}};


        printArray(new Official().merge(a));
        printArray(new Official().merge(a2));

    }

    private static void printArray(int[][] merge) {
        StringBuilder sb = new StringBuilder("[");

        // TODO: 2020/6/21 草！ 数组，没有 toString 方法的默认实现，需要自己搞
        for (int i = 0; i < merge.length; i++) {
            sb.append(Arrays.toString(merge[i]));

            if (i < merge.length - 1) {
                sb.append(",");
            }
        }

        sb.append("]");

        System.out.println(sb.toString());
    }
}
