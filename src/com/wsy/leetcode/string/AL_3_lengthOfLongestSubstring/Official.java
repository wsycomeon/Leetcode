package com.wsy.leetcode.string.AL_3_lengthOfLongestSubstring;

import java.util.HashMap;

public class Official {


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;


        // 保存 字符 和 其对应的 最新下标(todo 这里没说 字符 只是 小写字母，所以 用数组index 存其在字符串中的方式 不可用)
        HashMap<Character, Integer> map = new HashMap();

        // 最长长度 --》todo 这里没说要 最长的 第一个子串，所以只记录 长度 即可
        int max = 0;

        // 字符不重复的子序列的 起始index
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            // 读到 当前字符
            char c = s.charAt(i);

            // 新字符 之前有没有
            if (map.containsKey(c)) {
                // 有的话，要更新下 start，
                start = Math.max(start, map.get(c) + 1);
            }

            // 记录下 字符 和 index
            map.put(c, i);

            // 更新下 max
            max = Math.max(max, i - start + 1);
        }

        return max;

    }

}
