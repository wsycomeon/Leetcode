package com.wsy.leetcode.string.AL_242_isAnagram;

public class Official {


    /**
     * 字母异位词
     * 其实就是 判断 两个字符串中 出现的 字符 以及 个数 是否完全一致
     * 1、典型的 hash表 功能 ---》字符 + 出现次数
     * <p>
     * <p>
     * 2、todo 但是，本题 ，限定了 只有 26个小写字母，那么 直接使用 大小为 26的数组，来存 对应字符 出现的次数即可； --> 而 字符范围很大时，比如 unicode 就得用 hash表 了！
     * 遍历 第一个s 填充它 ++；遍历 第二个字符 t 消除它 --；
     * 最后，遍历 数组，只要 有一个 != 0 ，那就是 false；
     * 否则，就是 true
     * <p>
     * <p>
     * 时间：O(N)
     * 空间：O(1) --> 固定长度 26 的数组
     */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null)
            return false;

        if (t == null)
            return false;

        if (s.length() != t.length())
            return false;

        // 否则，才是 后面的 正常逻辑
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < count.length; i++) {

            if (count[i] != 0) {
                return false;
            }
        }

        return true;
    }


}
