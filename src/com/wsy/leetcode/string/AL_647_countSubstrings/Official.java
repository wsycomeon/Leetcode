package com.wsy.leetcode.string.AL_647_countSubstrings;

public class Official {


    /**
     * 计算一个字符串中的 回文子串 的个数：相同字符组成，不同起始位置的算作 不同的子串
     * <p>
     * 回文串：单个字符，或者 偶数个字符 + 单个字符
     * <p>
     * 两个解法：
     * <p>
     * 方法1：
     * 若字符串 长度为n，则 每个字符 都可能 成为 回文中心；
     * 且 每两个字符之间的点 也可能 成为 回文中心；
     * 则 回文中心的个数是 n + n-1 = 2n-1
     * 以 这些可能的回文中心，用 双指针 往 左、右扩散，累加 回文串个数，直到 其最大回文串
     * <p>
     * （1）注意，回文串中心的 left = center / 2 , right = left + center % 2 ---> 这个不太好理解
     * （2）还是换另一个方式说吧，从前往后 遍历 每一个字符；
     * 这个字符 可以作为 正巧的中心点（回文子串长度 为 奇数），也可能 作为 中心点的左边（回文子串长度为 偶数）
     * 同样往两边扩散，是回文的，count++ 即可，直到 不是回文了，换下一个位置的字符
     * <p>
     * 时间：O(N^2)
     * 空间：O(1)
     *
     *
     * <p>
     * 方法2：
     * TODO 马拉车 Manacher 算法：有时间再看，看起来 不简单
     * https://www.cnblogs.com/bitzhuwei/p/Longest-Palindromic-Substring-Part-II.html
     * <p>
     * 时间：O(N)
     * 空间：O(N)
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // i 恰好 为中心点，统计所有 奇数长度 的回文子串
            count += computeCount(s, i, i);

            // i 为 中心点左侧，统计所有 偶数长度 的回文子串
            count += computeCount(s, i, i + 1);

        }

        return count;
    }

    // 统计，字符串，从left、right开始的回文串个数
    private int computeCount(String s, int left, int right) {
        int count = 0;

        // 左、右字符 符合 回文条件
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            // 向两边 扩散
            left--;
            right++;
        }

        return count;
    }


}
