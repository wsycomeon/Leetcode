package com.wsy.leetcode.string.AL_14_longestCommonPrefix;

public class Official {


    /**
     * 1、横向比较
     * 从前往后 ，依次拿 子串和 之前的最长公共前缀 计算出 新的工具前缀
     */
    public String longestCommonPrefix(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        String prefix = array[0];
        int count = array.length;

        // i 从 1 开始 就好！
        for (int i = 1; i < count; i++) {
            // 将原最长前缀 prefix 和 下一个字符串 比较，更新 prefix
            prefix = longestCommonPrefix(prefix, array[i]);

            // 直到 公共前缀长度 为0
            if (prefix.length() == 0) {
                break;
            }

        }

        return prefix;
    }

    /**
     * 求俩 字符串的 最长公共前缀
     */
    public String longestCommonPrefix(String str1, String str2) {

        int maxLength = Math.min(str1.length(), str2.length());

        // 依次比较每个字符，发现 不相同，就跳出循环
        int index = 0;

        while (index < maxLength && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }

        // 一旦不等，直接 跳出循环，最长就确定了
        return str1.substring(0, index);
    }


    /**
     * 2、纵向比较
     * 同时取 所有字符串的 相同index 字符进行比较，一旦 不同，就结束了
     */
    public String longestCommonPrefix2(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        int count = arr.length;

        // 任选 第一个子串 做基准
        int length = arr[0].length();
        for (int i = 0; i < length; i++) {
            // 取出 基准字符
            char c = arr[0].charAt(i);

            // 从数组 第二个子串 开始比较
            for (int j = 1; j < count; j++) {
                // 若 某个子串 到头了，或者 没到头 但是 其对应位置字符 不相等，就说明 最长就这么多了
                if (i == arr[j].length() || arr[j].charAt(i) != c) {

                    return arr[0].substring(0, i);
                }
            }
        }

        // 前面 都没return，那最长 就是 第一个子串了！
        return arr[0];
    }


    /**
     * 3、分治的思想
     * [start,mid] 的最长 和  [mid,end] 的最长，再取 最长 就是整体的最长了
     */
    public String longestCommonPrefix3(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        return longestCommonPrefix(arr, 0, arr.length - 1);
    }

    // 递归
    public String longestCommonPrefix(String[] arr, int start, int end) {
        // 1、终止条件
        if (start == end) {
            return arr[start];
        }

        // 2、处理当前层
        int mid = (end - start) / 2 + start;

        // 3、递归
        String leftLongest = longestCommonPrefix(arr, start, mid);
        String rightLongest = longestCommonPrefix(arr, mid + 1, end);

        // 4、递归回来后，将两个结果 合并
        return longestCommonPrefix(leftLongest, rightLongest);
    }


    /**
     * 4、二分查找。
     * 求所有子串的最短长度
     * 然后 计算 mid 差分，看mid之前的子串 是不是 公共的；
     * 不是的话，往左找；否则 继续往右找
     */
    public String longestCommonPrefix4(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        for (String str : arr) {
            minLength = Math.min(minLength, str.length());
        }

        int low = 0, high = minLength;

        while (low < high) {
            int mid = (high - low + 1) / 2 + low;

            if (isCommonPrefix(arr, mid)) { // 最长前缀 要 >= mid，那就 向右找
                low = mid;
            } else { // 向左找
                high = mid - 1;
            }

        }

        return arr[0].substring(0, low);
    }

    // 比较 length 长度的子串 是不是 公共前缀，即 每个子串的 前 length 个 都相等！
    public boolean isCommonPrefix(String[] arr, int length) {
        String str0 = arr[0].substring(0, length);

        for (int i = 1; i < arr.length; i++) {
            String str = arr[i];

            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }


        return true;
    }


}
