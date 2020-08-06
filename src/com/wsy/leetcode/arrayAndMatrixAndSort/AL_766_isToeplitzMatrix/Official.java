package com.wsy.leetcode.arrayAndMatrixAndSort.AL_766_isToeplitzMatrix;

public class Official {

    // TODO: 2020-08-06 12:24:22 托普利茨矩阵
    // TODO: 2020-08-06 12:26:38 有思路了，遍历最后一行（或者 第一行）元素，任取一个元素， matrix[i][j] 比较 和 matrix[i-1][j-1] 是否相等，相等 继续重复操作 直到 越界，不等 就是false
    // TODO: 2020-08-06 12:32:33 官方 两个方法，更优秀的第二个方法 和我的 思路一致，看完代码了
    // TODO: 2020-08-06 12:58:54 写完了。
    // TODO: 2020-08-06 13:02:55 我曹！写错了！ --》条件 考虑少了，想当然的 以为 只遍历 最后一行 就可以了 实际上，那个只符合 m <= n 的情况！但是 实际上 可能 m > n !!!

    /**
     * https://leetcode-cn.com/problems/toeplitz-matrix/solution/tuo-pu-li-ci-ju-zhen-by-leetcode/
     * <p>
     * 方法1：
     * 遍历 最后一排元素，递归比较左上角 元素，不相等 就是 false，否则 符合条件 就递归 直到 边界
     * <p>
     * 空间：O(n * min(m,n)) --> for 循环 n 次，内部while循环 次数是 min（m,n）
     * 时间：O(1)
     * <p>
     * todo 日，错误解法 --》太想当然了!！！！！！！！！！！！！！！！！！！！！！！！！！！
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        // 1、条件已经做了 约束了，这个 必定是 有效数组，特殊情况 就不予考虑了！

        // 2、遍历 最后一排元素
        int m = matrix.length;
        int n = matrix[0].length;

        for (int j = 0; j < n; j++) {
            // 对于任意的j，i 初始值 都是 m-1
            int i = m - 1;

            // TODO: 2020-08-06 12:53:53 注意啊！这里 不能直接 j--，而是 要用 另一个临时变量（初始值 为 j）标识 纵坐标 来往 左上角 移动。。。 否则 就 死循环 了！
            int tempJ = j;

            // 递归判断, a[i][j] 和 a[i-1][j-1] 是否相等
            while (i >= 1 && tempJ >= 1) {
                // 对角线元素不等，直接 false
                if (matrix[i][tempJ] != matrix[i - 1][tempJ - 1]) {
                    return false;
                } else {
                    // 相等，继续 左上角 比对
                    i--;
                    tempJ--;
                }
            }

        }

        // 3、前面都没返回，那就是 true
        return true;
    }


    // TODO: 2020-08-06 13:06:16 md，实际上，更全面的解法是，取 矩阵中的 每一个元素，如果 存在 其 左上角元素的话，若 和左上角 元素 不等，那就是false；全部遍历完，没有不等的 ， 那就是true！

    /**
     * 方法2：
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        // 1、条件已经做了 约束了，这个 必定是 有效数组，特殊情况 就不予考虑了！

        // 2、遍历 矩阵元素，看 其 左上角 元素 是否 满足性质！
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                // 存在 某元素 != 其左上角元素的 情况，就是false
                if (i > 0 && j > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }

            }
        }

        return true;
    }


}
