package com.wsy.leetcode.arrayAndMatrixAndSort.AL_769_maxChunksToSorted;

public class Official {


    // TODO: 2020-08-06 16:04:20
    // TODO: 2020-08-06 16:09:44 有思路了：从前往后遍历 arr，发现规律： 后一个数字 比 前一个数组的 起始数字 start大，那就是新的一个数组的起点，数组count++；一直到最后 即可
    // TODO: 2020-08-06 16:27:59 哎，代码写完之后，验证用例，发现 又错了 --》又想当然了。。新的元素 比 之前起始元素大，不能说 就是新的数组了。。可能 还是同一个数组。。
    // 比如：{1, 2, 0, 3} 前面 三个元素组成 一个数组 才对。。。
    // TODO: 2020-08-06 16:30:07 还是要看 官方题解 去：
    // TODO: 2020-08-06 16:39:48 正确思路 和我 上面的 正好不同 --》我上面是想 判断 一个新数组的开始；而官方解法 则是判断 一个 旧数组的终结 ！
    // 判断的条件是，遍历到 第i个元素时，从 第一个元素开始的区间  最大值 是不是 恰好等于 下标 i，如果是的话，这个旧数组 就终结了，count++
    // 为啥呢，因为包括第 i 个元素在内， 一共 i+1个 元素，最大的还是 i，说明 从 [0,i]的所有元素，恰好都有，这个数组排序 可以是 符合条件的一块，所以 count++ ！
    // TODO: 2020-08-06 16:53:55 整理完毕！

    /**
     * 官方解法：有点 晦涩！
     * https://leetcode-cn.com/problems/max-chunks-to-make-sorted/solution/zui-duo-neng-wan-cheng-pai-xu-de-kuai-i-by-leetcod/
     * <p>
     * 规律是：判断 区间 0 - i 的 最大元素值 是不是 恰好为 i，如果是的话，count++；
     * <p>
     * 时间：O(n) 遍历了一遍数组
     * 空间：O(1)
     */
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int count = 0;

        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            // 求加入 新元素后的 区间最大值
            max = Math.max(max, arr[i]);

            // 最大值 恰好 = i，说明 [0,i] 的所有元素，此时 都在区间内了，此时 加上 这个元素的区间 可以是一个 符合条件的块 ！
            if (max == i) {
                count++;
            }

        }

        return count;
    }


    /**
     * 题目：
     * 长度为 n 的数组 arr 是 [0,n-1]的元素，有序数组 src 打乱后的一种排序；
     * 现在 要将 arr 分块，然后 单个块内的 进行排序，然后 一次拼接，要求 拼接后的数组，是 有序的src
     * 求 arr 最多能 分成 多少块！
     * <p>
     * 思路如上：
     * 方法1：
     * 从前往后遍历数组，依次找 可以独立成 新数组的 起始元素，若 发现 新元素 比上一个 数组的起始元素大，那么 他就是 新的起始元素，count++；
     * 直到 最后即可
     * <p>
     * todo --》错误解法！！！
     */
    public int maxChunksToSorted1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int startVal = arr[0];
        int count = 1;

        for (int i = 0; i < arr.length; i++) {
            // 发现 新数组 的 起点
            if (arr[i] > startVal) {
                count++;
                startVal = arr[i];
            }

        }

        return count;
    }


}
