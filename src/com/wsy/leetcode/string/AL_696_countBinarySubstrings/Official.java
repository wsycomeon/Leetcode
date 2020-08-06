package com.wsy.leetcode.string.AL_696_countBinarySubstrings;

public class Official {


    /**
     * 计算 只包含0和1的字符串中，具有 相同数量0和1的 非空、连续 子字符串的数量，
     * <p>
     * 方法1：
     * 记录 0 或者 1 连续出现的个数，比如 2 3 2
     * 那么从前往后，遍历，两两group之前的 符合条件的子串个数  = Math.min(g1 , g2)
     * 从前往后，累加起来 即可
     * <p>
     * <p>
     * 时间：O(N) 两次 遍历
     * 空间：O(N) 数组
     */
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 1、遍历 记录 连续字符 出现的次数
        int[] counts = new int[s.length()];

        int index = 0;
        counts[index] = 1;

        for (int i = 1; i < s.length(); i++) {

            // 如果 不相等 ，即 出现 新的字符
            if (s.charAt(i - 1) != s.charAt(i)) {
                index++;
                counts[index] = 1;
            } else {
                // 相等，++
                counts[index]++;
            }
        }

        // 遍历 数组 累加，计算min
        int count = 0;

        // TODO: 注意，这里的数组，不都是 有效元素，最多到 index 是有效的，i+1 <= index 即 i+1 < index+1

        for (int i = 0; i < index; i++) {
            count += Math.min(counts[i], counts[i + 1]);
        }

        return count;
    }


    /**
     * 方法2：
     * 在上述方法上的优化，没必要 用数组，且 分两次 遍历 来累加计算
     * 其实，用两个 临时变量，分别记录 pre组，cur组的 元素个数 就行
     * <p>
     * 一样累加，只是需要注意！最后，出循环了，别忘记 还要 再求一次 min，累加！
     * <p>
     * 时间：O(N) 一次遍历
     * 空间：O(1) 俩变量
     */
    public int countBinarySubstrings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;

        int pre = 0;
        int current = 1;

        // 遍历，比较 i 和 i-1 是否相等，不相等 的话，说明 i 到了 新的一组 ，累加计算 之前两组的 min：pre、current
        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) != s.charAt(i - 1)) {
                // 不相等的话，累加计算
                count += Math.min(pre, current);

                // 两变量 后移
                pre = current;
                current = 1;
            } else {
                current++;
            }

        }

        // todo 注意！遍历到最后，还是会 少 最后一次 min比较，这里不要忘记了！
        count += Math.min(pre, current);

        // 返回
        return count;
    }


    /**
     * 方法3：
     * 类似于 回文串的扩散方式 计算：
     * 从前往后 遍历，
     * 1、发现 下一个字符 和 当前字符 相等，就跳过；
     * 2、不相等的话，累加，直到 向两边扩散 成最长的 就好
     * <p>
     * 时间：最优是 O(N) ？
     * 空间：O(1)
     */
    public int countBinarySubstrings3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                continue;
            }
            // 下一个 字符 不相等了，累加 扩散
            count += getSpreadCount(s, i, i + 1);
        }
        return count;
    }

    private int getSpreadCount(String s, int left, int right) {
        int count = 1;
        // 符合 扩散条件
        while (left - 1 >= 0 && right + 1 < s.length()
                && s.charAt(left) == s.charAt(left - 1)
                && s.charAt(right) == s.charAt(right + 1)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

}
