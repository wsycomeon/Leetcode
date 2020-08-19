package com.wsy.leetcode.string.AL_564_nearestPalindromic;

public class Official {


    // TODO: 2020-08-17 18:53:38 求一个数字的 最近回文数
    // TODO: 2020-08-17 18:54:05 比如 12345 --》和他最近，那肯定是 修改 更小的位置 代价更小； 改成 12 3 21
    // 那么，n = 偶数个，就是 调整 后面 n/2 和 前面一样；奇数个，那就是 调整 后面 (n-1)/2 和 前面一样 ？

    // TODO: 2020-08-17 19:24:11 果然，我前面的方法 有缺陷 --》考虑的 不够全面 --》头疼，脑子疼，看不进去


    // TODO: 2020-08-17 20:13:45 实在是 头疼，不在状态。。。不看了


    /**
     * https://leetcode-cn.com/problems/find-the-closest-palindrome/solution/xun-zhao-zui-jin-de-hui-wen-shu-by-leetcode/
     * 方法1：
     * <p>
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(l) --》扫描，插入，删除，镜像需要 O(l)，其中 l 是字符串的长度。
     * <p>
     * 空间复杂度：O(l) --》需要 临时变量 用于存储字符串。
     */
    public String nearestPalindromic(String n) {
        if (n.equals("1")) {
            return "0";
        }

        // todo 1、直接 获取 翻转 字符串
        String a = mirroring(n);

        long diff1 = Long.MAX_VALUE;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        // 结果 不能 是自身
        if (diff1 == 0) {
            diff1 = Long.MAX_VALUE;
        }

        // todo 2、中心数字为0，则 0 变 9，将 原数字 减小，在做 翻转
        StringBuilder s = new StringBuilder(n);

        // 即使俩中间数，也取 偏左 的那个
        int i = (s.length() - 1) / 2;

        // 从中间位置开始 向左，将 连续的 0 都 改成 9
        while (i >= 0 && s.charAt(i) == '0') {
            // 把 i 这个位置的 字符，修改为 9 --》俩index [i , i+1)
            s.replace(i, i + 1, "9");
            i--;
        }

        if (i == 0 && s.charAt(i) == '1') {
            // 199xxx之类的，第一个数字 如果是 1，就 删掉，因为 后面 减小了
            s.delete(0, 1);
            // 然后 重新 求 mid 位置，将该位置 修改为 9
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        } else {
            // 否则的话，将 i 位置（前一位） 的 数值 -1 todo --》这里，不做 字符 - 数字 的 映射吗？
            s.replace(i, i + 1, "" + (char) (s.charAt(i) - 1));
        }

        // 对 当前的 字符串 做映射，求出 b，记录 这种策略的差值
        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));


        // todo 3、若 中心数字 为 9，则 9 变 0，将 原数字变大，再做 翻转
        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;

        // 9 --》0 进位
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }

        if (i < 0) {
            // 第一位 也修改了，那就 进 1，加一位
            s.insert(0, "1");
        } else {
            // 否则，只把 i 位置的数值 +1
            s.replace(i, i + 1, "" + (char) (s.charAt(i) + 1));
        }
        // 对当前字符串 做 镜像
        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        // 取 三个 diff 值 最小的，但是，当 diff 值 相同时，取 最小的 翻转数值

        // b 是 先减小，再翻转，数值 肯定 变小；--》优先 选它
        // c 是 先增大，再翻转，数值 肯定 变大；--》最后 选它

        if (diff2 <= diff1 && diff2 <= diff3) {
            return b;
        }

        if (diff1 <= diff3 && diff1 <= diff2) {
            return a;
        }

        return c;
    }


    // 字符串的中间字符的index = length/2，奇数的话，恰好是那个；偶数的话，是 俩中间字符中 偏右 的 那个！
    public String mirroring(String s) {
        String x = s.substring(0, (s.length()) / 2);
        return x
                + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") // 看 是否 拼接上 中间字符
                + new StringBuilder(x).reverse().toString();
    }

}
