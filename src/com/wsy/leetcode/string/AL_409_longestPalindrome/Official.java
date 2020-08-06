package com.wsy.leetcode.string.AL_409_longestPalindrome;

public class Official {


    /**
     * 最长回文串：
     * <p>
     * <p>
     * 这题的意思是：一个字符串中的 所有字符中，任意 排列组合，可以得到的 最长回文串的长度！
     * <p>
     * 要求的只是长度，没有说 要最终的 字符串，所以，我们 只需要 知道  每个字符 出现的次数 即可用来 计算！
     * <p>
     * <p>
     * 方法1：
     * <p>
     * 注意了！
     * 1、比如 一个字符 a 出现了 奇数次，我们也只取它 最大的偶数个字符 来做回文串的一部分，即 它的 count / 2 *2
     * 2、还有，如果 经过 每个字符的个数 削减成偶数个之后，当前长度 < 原长度，说明，一定 至少有一个 奇数count的字符，把它放在中间 也是回文串，所以 count++
     *
     *
     * 时间：O(N)
     * 空间：O(1) --> 或者 O(M) 字符集 大小
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 本题目，只有 大小写 字母。那就是 52长度的数组即可
        // TODO: 我擦，注意，ASCII 一共有 128 个字符( 0 - 127 )，其中 'A' = 65 , 'a' = 97 , 大小写 字符区间 之间  是 不连接的，中间还隔了6个字符呢。。。直接 使用 128长度的数组就好了，也懒得算 偏移位置了

//        int[] counts = new int[58]; // 换成 52 + 6 = 58 也能用 ！
//        for (char c : s.toCharArray()) {
//            // 注意，A 比 a 数值小 ！
//            counts[c - 'A']++;
//        }

        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            counts[c]++;
        }

        int length = 0;
        // 遍历counts数组, /2*2 处理 --》比如 1 就是0了
        for (int count : counts) {
            length = length + count / 2 * 2;
        }

        // 如果，长度少了，说明 至少有一个 奇数次数的 字符，++
        if (length < s.length()) {
            length++;
        }

        return length;
    }


}
