package com.wsy.leetcode.string.AL_205_isIsomorphic;

import java.util.HashMap;

public class Official {


    /**
     * 判断 两个 字符串 是否 同构 ！
     * https://leetcode-cn.com/problems/isomorphic-strings/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-42/
     * <p>
     * -----》todo 就是一种 映射思想，一个字符 可以 映射 首次出现的位置，也可以 映射 某个字符！ 保持 某个字符的 映射值 唯一 即可！
     *
     * <p>
     * 同构的意思是：
     * 1、字符串内 每个字符 出现的次序 以及 个数 要完全一致；
     * 2、或者说，另一个表达，一个字符串的 a 唯一映射了 b，且 另一个字符串的 c 唯一映射了 d 等
     * <p>
     * 所以，解法 就有 两大类：
     * 1、根据 出现的顺序 为 index，分别映射，然后从前往后遍历，每个index所在的字符，第一次出现的顺序 是否相等；
     * （1） 或者 使用 hash表 记录 某字符 出现的index，定位到数组，累计 它出现的次数，最后 两个字符串的 数组两两比较；
     * <p>
     * 2、判断，两个字符串 是不是 都符合 对向、唯一映射 --》hash映射
     * <p>
     * <p>
     * <p>
     * 方法1：不使用 hash表 映射，俩字符串 分别维护，其内部 依次出现 字符 首次出现的位置，进行匹配；
     * 一旦某个字符 不匹配，就 false
     * <p>
     * 时间：O(N)
     * 空间：O(1)
     */
    public boolean isIsomorphic(String s, String t) {
        // 1、字符范围 128 即可
        int[] index1 = new int[128];
        int[] index2 = new int[128];

        // todo 2、注意，因为上面的数组，默认数值 为 0，且这里的 i 也是 从 0开始，所以，记录 某字符 首次出现的位置，不能 直接用i，这里使用 i+1
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // 俩字符串，当前位置的字符（并不关心 到底是哪个，一不一样） todo 首次出现的位置（也是一种 映射值，映射成 同一个 第三方），如果 不相等，那就是 不匹配
            if (index1[c1] != index2[c2]) {
                return false;
            }

            // 否则，就是一样的
            // 3、但是 还要注意，如果 都为 0 即 都是 首次出现，要 记录下 位置
            if (index1[c1] == 0) {

                index1[c1] = i + 1;
                index2[c2] = i + 1;
            }

        }

        return true;
    }

    /**
     * 方法2：判断 俩字符串 都符合 单向唯一映射 才行
     * hash表
     * <p>
     * 时间：O(N) --》两次 循环 遍历
     * 空间：O(N) --》hash表
     */
    public boolean isIsomorphic2(String s, String t) {

        return isIsomorphicHelper(s, t)
                && isIsomorphicHelper(t, s);
    }

    /**
     * 判断 src --》dst
     * 俩字符串 是不是 每个字符 都是 唯一映射的
     */
    private boolean isIsomorphicHelper(String src, String dst) {
        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < src.length(); i++) {
            char c1 = src.charAt(i);
            char c2 = dst.charAt(i);

            if (map.containsKey(c1)) {
                // 如果 之前有 字符映射关系，但是 和 本次不同，那就是 映射不唯一 ，false！
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }

        }

        return true;
    }


    // ========================================================================================


    /**
     * todo 错误 想法！！！ 忽略！！！
     * 方法3：
     * 我想的笨拙方法，也是 hash表
     * 对于每个字符串 hash表记录 字符出现位置，数组上的对应位置 记录次数，
     * 然后 比较俩数组，是否完全一致。。。
     * <p>
     * todo --》这个方法 是错误的 ！!!
     * 只记录了 首次出现的位置 和 出现的总次数 是不行的，因为 位置 还是可以 不对应的！
     * 举例：abcabc 和 bcdbdc !
     */
    public boolean isIsomorphic3(String s, String t) {
        int[] a1 = getIndexAndCount(s);
        int[] a2 = getIndexAndCount(s);

        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }

        return true;
    }

    private int[] getIndexAndCount(String s) {
        return new int[0];
    }


}
