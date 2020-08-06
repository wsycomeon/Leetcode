package com.wsy.leetcode.arrayAndMatrixAndSort.AL_33_search;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,8,9,0,1,2], target = 3
 * 输出: -1
 * <p>
 */
public class Official {

    /**
     * O(logn) 想要找到的话，只能是二分查找了
     * 本身就是 两部分 分别有序，所以 可以使用 二分的思想
     */
    public int search(int[] arr, int target) {
        // 边界条件
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 二分查找
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;

        while (start <= end) {
            mid = start + (end - start) / 2;

            // 1、恰好是 中间点，返回
            if (arr[mid] == target) {
                return mid;
            }

            // 2、不是中间点的话，想要 快速查找，肯定要先考虑 对折后 两部分中有序的那一部分 去查找
            // todo（因为 只有区间 是有序的，才能 通过 头、尾 来快速判定 target 在不在 该区间内。。如果是 无序区间，只能遍历了。。
            // 即 二分判断了
            if (arr[start] <= arr[mid]) { // 说面 前半部分有序；
                if (target >= arr[start] && target < arr[mid]) { // 是否在前半部分
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            } else {// 否则 后半部分有序
                if (target > arr[mid] && target <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }

        // 没找到，就返回 -1
        return -1;
    }

}
