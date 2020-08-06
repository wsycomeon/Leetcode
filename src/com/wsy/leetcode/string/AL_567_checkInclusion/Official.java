package com.wsy.leetcode.string.AL_567_checkInclusion;

import java.util.Arrays;

public class Official {


    /**
     * 两个字符串 如果包含 不重复 字符的个数一致、以及 单个字符 出现的次数也一致；
     * 则 其中一个字符串，就是另一个字符串的 排列 ！
     * <p>
     * (1)他们 按照字符大小排序后，应该是一样的字符串！
     * 这样的话，我们只要 将 短串 s1 进行排序后的结果；
     * 和 s2中 所有 与短串长度一致的子串 排序后 比较即可
     * --》注意，s2 不能排序了，只能从前往后 截取 同样长度的子串 --》题目要求！
     * <p>
     * (2) 或者，不对 俩字符串 排序后 在比较；
     * 而是 直接 对 字符串中的每个字符 进行 哈希运算，然后 保存它的 出现频率；
     * 然后 和 另一个的hash表比较，如果 相同 ，则 一个 就是 另一个的 一种排列！
     */
    public boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        if (length1 > length2) {
            return false;
        }

        // s1 排序后的新串
        s1 = sort(s1);


        // 从前往后 取s2中 所有的 和 s1相同长度的子串
        for (int i = 0; i <= length2 - length1; i++) {
            // 截取出 等长的子串
            String str = s2.substring(i, i + length1);

            // 也对子串进行一样的排序， 若 子串 完全相等，说明 有
            if (s1.equals(sort(str)))
                return true;
        }

        // 如果前面 都没有，那就 是 没有！
        return false;
    }


    /**
     * 将一个字符串中的字符 全部排序，然后 生成 新的字符串！
     */
    public String sort(String s) {
        char[] t = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }


    /**
     * 类似于 hash 算法，储存 字母的频率
     */
    public boolean checkInclusion2(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        // 边界条件
        if (length1 > length2)
            return false;

        int[] s1map = new int[26];

        for (int i = 0; i < length1; i++)
            // 将a中的所有字符，hash 统计
            s1map[s1.charAt(i) - 'a']++;


        // 将s2 所有同等长度的子串，进行同样的hash后，进行比较
        for (int i = 0; i <= length2 - length1; i++) {
            int[] s2map = new int[26];

            // 取 [i , i + length1) 子串中的 每个字符!
            // TODO: 2020/6/20 这里效率提升的地方，就是不用 截取出 子串，直接 取字符 进行计算叠加！

            for (int j = 0; j < length1; j++) {
                // 取出字符
                char c = s2.charAt(i + j);

                s2map[c - 'a']++;
            }


            // 若相同，就 找到了
            if (matches(s1map, s2map))
                return true;
        }


        return false;
    }

    /**
     * 上面 版本的优化，对于s2的子串，不用每次 都重新计算 所有的次数，而是 进行优化
     * 下移一位的时候，将新的字符 count++，旧的字符 count -- 即可！
     */
    public boolean checkInclusion3(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        // 边界条件
        if (length1 > length2)
            return false;

        int[] s1map = new int[26];
        int[] s2map = new int[26];

        for (int i = 0; i < length1; i++) {
            s1map[s1.charAt(i) - 'a']++;

            // s2的子串 也直接初始化好
            s2map[s2.charAt(i) - 'a']++;
        }

        // todo 注意，这里 i 是 < , 不是 <=，因为 如果不匹配，后面 还要 直接后移一位 取值（计算下一个的count值），那个 index 本身是 i + length1，这个 不能越界，即 i + length1 < length2
        for (int i = 0; i < length2 - length1; i++) {
            // count表 进行匹配
            if (matches(s1map, s2map))
                return true;

            // 不匹配的话，后移一位，新加入的字符 即 之前的子串 最后字符的下一位，即 i + length1 这个index
            char newChar = s2.charAt(i + length1);
            s2map[newChar - 'a']++;

            // 旧字符 的统计 要删掉
            s2map[s2.charAt(i) - 'a']--;
        }

        // todo 上面 这种写法，到最后一个子串 就进不去了，没来得及 比较，所以 这里 再比较一次！
        return matches(s1map, s2map);
    }


    /**
     * 字母 最多26个，比较 各字母 出现的个数 即可
     */
    public boolean matches(int[] s1map, int[] s2map) {

        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }

        return true;
    }

}
