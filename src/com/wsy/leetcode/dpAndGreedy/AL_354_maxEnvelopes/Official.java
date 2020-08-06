package com.wsy.leetcode.dpAndGreedy.AL_354_maxEnvelopes;

import java.util.Arrays;
import java.util.Comparator;

public class Official {


    /**
     * https://leetcode-cn.com/problems/russian-doll-envelopes/solution/e-luo-si-tao-wa-xin-feng-wen-ti-by-leetcode/
     * 这是 最长递增子序列的 二维问题。
     * 排序 + 最长递增子序列 即可解决
     * <p>
     * ---
     * 1、先将 信封 数组，按照 w 升序，但是 h 降序 来排列（则 后面 w相同的信封，必然 不可能 出现在 一个递增序列）
     * --》为的是， 在 w 相同的情况下，能让 h 最小的 更接近于 后面 w较大的信封；
     * 或者说 让 h 小的尽量在后面，增长的慢一些
     * 2、抽取 现在的 h数组
     * 3、对 h数组 求 最长递增 子序列 长度即可
     * <p>
     * 时间：O(nlogn)
     * 排序 和 LIS算法，都是 O(nlogn)
     * <p>
     * 空间：O(n)
     * 排序算法、lis算法的d数组、h数组，都是 O(n)
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        // 1、排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    // w相同的话，h 降序
                    return o2[1] - o1[1];
                } else {
                    // w 不同的话，升序
                    return o1[0] - o2[0];
                }
            }
        });


        // 2、抽取出 当前的 h 数组
        int[] h = new int[envelopes.length];

        for (int i = 0; i < envelopes.length; i++) {
            h[i] = envelopes[i][1];
        }

        return lengthOfLIS(h);
    }

    /**
     * 求 h 数组的 最长 递增 子序列的长度 （没要求 是连续的）
     * <p>
     * 这里，直接 使用 方法2吧：
     * 尽量 放慢 子序列的增长速度
     */
    private int lengthOfLIS(int[] h) {
        if (h == null || h.length == 0) {
            return 0;
        }


        int length = h.length;
        int[] d = new int[length + 1];
        d[1] = h[0];
        int len = 1;

        // 从 index = 1 开始

        for (int i = 1; i < length; i++) {

            if (h[i] > d[len]) {
                // 递增的话，直接 插入尾部
                len++;
                d[len] = h[i];
            } else {
                // 否则的话，递增 子序列 长度不变的情况下，替换 d 中 比自己稍大的元素
                // d 是有序的，在 d 中 二分查找 到 比自己稍小的index
                int left = 1;
                int right = len;
                // 默认比自己稍小的是 在无效元素 的位置
                int pos = 0;
                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    // TODO: 2020/6/26 我曹！这是  d[mid] 不是 mid ！！！！
                    if (d[mid] < h[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                // 找到 pos
                d[pos + 1] = h[i];
            }
        }

        return len;
    }


    private int max;
    private int current;

    /**
     * todo 错误算法！！！！
     * <p>
     * 套信封问题
     * <p>
     * 方法1：
     * 理论上来说，我将 所有信封 从小到大 （左、右）双排序
     * 然后 依次取最小的 就是 最多信封了吧？
     */
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 先由宽决定，再由高 决定
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    return o1[1] - o2[1];
                }
            }
        });


        max = Integer.MIN_VALUE;
        current = 0;

        for (int i = 0; i < envelopes.length; i++) {
            dfs(envelopes, i, envelopes.length, new int[]{0, 0});
        }

        return max;
    }


    private void dfs(int[][] envelopes, int start, int size, int[] last) {
        if (start >= size) {
            return;
        }

        for (int i = start; i < size; i++) {
            int[] newOne = envelopes[i];

            if (isBigger(newOne, last)) {
                current++;
                max = Math.max(max, current);

                dfs(envelopes, i + 1, size, newOne);
                current--;
            }

        }

    }

    // 新的 能完全装下 旧的
    private boolean isBigger(int[] newOne, int[] last) {
        return newOne[0] > last[0] && newOne[1] > last[1];
    }

}
