package com.wsy.leetcode.arrayAndMatrixAndSort.AL_566_matrixReshape;

import java.util.LinkedList;
import java.util.Queue;

public class Official {


    /**
     * 将 矩阵 转化为、重塑为 另一个 宽、高的矩阵
     * 且，已经说明了，
     * 1、要按照 行遍历 顺序，转化
     * 2、如果 矩阵的 容量大小不同，不用转化，范围 原矩阵
     * <p>
     * 方法1：
     * 1、判断 可重塑；
     * 2、逐行遍历 原矩阵，将矩阵元素 存储到一个队列中，
     * 3、然后，再 逐行遍历 新矩阵，将 元素出队，一个一个的放进去
     * <p>
     * 时间：O(M * N) 遍历 两次 矩阵
     * 空间：O(M * N) 多了一个 中间队列。
     */
    public int[][] matrixReshape(int[][] nums, int row, int column) {
        // 不能 重塑 的情形
        if (nums.length == 0 || nums[0].length == 0 || nums.length * nums[0].length != row * column) {
            return nums;
        }

        // 1、填充队列
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                queue.add(nums[i][j]);
            }
        }

        int[][] res = new int[row][column];

        // 2、出队，填充 新矩阵
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                res[i][j] = queue.remove();
            }
        }

        return res;
    }


    /**
     * 方法2：
     * 1、判断 可重塑
     * 2、用一个从0开始的 计算器 表示，再原矩阵中 逐行遍历的元素的 index
     * 3、逐行遍历新矩阵，填充元素，元素的定位 就用到了 index --》
     * 原矩阵的 [index / n][index % n]
     * <p>
     * todo 时、空 都更为优秀
     * 时间：O(N)  遍历 一次 矩阵，直接 数学计算 定位 原数据
     * 空间：O(1)
     */
    public int[][] matrixReshape2(int[][] nums, int row, int column) {
        // 不能 重塑 的情形
        if (nums.length == 0 || nums[0].length == 0 || nums.length * nums[0].length != row * column) {
            return nums;
        }

        int[][] res = new int[row][column];

        int index = 0;

        // 列数
        int length = nums[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // 逐行遍历 原矩阵 的元素，依次 填充到 新矩阵
                res[i][j] = nums[index / length][index % length];

                index++;
            }
        }


        return res;
    }


}
