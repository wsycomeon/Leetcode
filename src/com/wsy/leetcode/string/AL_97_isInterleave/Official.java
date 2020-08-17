package com.wsy.leetcode.string.AL_97_isInterleave;

public class Official {


    // TODO: 2020-08-17 12:25:34 判断 一个字符串 是不是 由  前俩 字符串 交错 组成

    // TODO: 2020-08-17 15:14:49 双指针，依次比较  s1 s2 当前字符 有没有 和 s3 相等的
    // --》 这个做法 有缺陷，当 s1 s2 当前 都有目标字符的时候，没有 好的 判定方法，决定 应该 选择哪个
    // TODO: 2020-08-17 15:16:15 实际的方法，是 动态规划 --》找 状态转移方程
    // 还有一个 就是 回溯 算法 --》遇到 相同的字符，两条路 都走


    // TODO: 2020-08-17 17:21:23 第二个方法：dfs，其实就是 回溯 方法
    // 但是 不好判断 复杂度 --》因为 做了剪枝，所以 理论上 效率会更高  --》确实 效率 更高，比 dp 还高 ！


    /**
     * https://leetcode-cn.com/problems/interleaving-string/solution/jiao-cuo-zi-fu-chuan-by-leetcode-solution/
     * <p>
     * 方法1：动态规划
     * <p>
     * 假设 f[i][j] 定义为 s1的前i个字符，和 s2的前j个字符  能否 交错组成 s3的前 i+j 个字符 --》是一个 boolean 值
     * <p>
     * 则，可知，它 既依赖于  s1、s2、s3 的当前字符；又依赖于 s1 s2 s3 之前的 字符的匹配关系，即：
     * <p>
     * 两种情况下，f[i][j] = true --》
     * （1）f[i-1][j] = true，且 s1[i-1] = s3[i+j-1]
     * --》s1的前i-1个 和 s2的前j个 元素，可以 交错组成 s3的前 i+j-1 个字符
     * 且 当前字符相等： s1的第i个元素（index = i-1）与 s3的第i+j个元素（index = i+j-1） 相等
     * <p>
     * （2）f[i][j-1] = true，且 s2[j-1] = s3[i+j-1]
     * 同理
     * <p>
     * 所以，状态转移方程为：
     * f[i][j] = f[i-1][j] && s1[i-1] == s3[i+j-1] || f[i][j-1] && s2[j-1] == s3[i+j-1]
     * <p>
     * 初始化条件为:
     * f[0][0] = true --> 0 + 0 = 0 , 当然为 true
     * <p>
     * --》注意，这里的 二维矩阵 都取 +1 的长度，方便让 i，j的值 对应的 就是 其 index
     * <p>
     * <p>
     * 时间：O(mn) --》内、外双循环
     * 空间：O(mn) --》二维矩阵
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // 初始条件，长度和 得相等
        int m = s1.length();
        int n = s2.length();
        int t = s3.length();

        if (m + n != t) {
            return false;
        }

        // TODO: 2020-08-17 16:26:21 注意，这是 为了让 i 匹配的不是 i-1，多申请一个
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        // 计算、填充 二维矩阵 f
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                int k = i + j - 1;

                // 防止越界
                if (i > 0) {
                    // f[i][j] 已经 = true，就 不用 后面的判断了
                    // 注意，第 i 个 字符，其 index = i-1
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k));
                }

                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k));
                }

            }

        }

        // 最后 返回 f[m][n] 就是 目标所求 --》s1的m个字符 和 s2的n个字符，是否 能够 交错组成 s3 的 前 m+n个字符
        return f[m][n];
    }

    /**
     * 方法2：
     * 上述 dp 的 空间复杂度是 O(mn)
     * --》实际上 很多 mn 的 空间复杂度 都可以 降维成 1维
     * --》叫 滚动数组 优化，一般符合 这么一个条件：
     * --》当前的dp值，只和 前一个dp 或者 前两个dp值 有关
     * <p>
     * 比如，本题原二维：
     * f[i][j] = f[i-1][j] && s1[i-1] == s3[i+j-1] || f[i][j-1] && s2[j-1] == s3[i+j-1]
     * <p>
     * <p>
     * todo --》这个 dp 降维的思路 确实 没看懂
     * <p>
     * 时间：O(mn)
     * 空间：O(n) --》第二个 字符串的长度
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[] f = new boolean[m + 1];
        f[0] = true;

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;

                if (i > 0) {
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }

                if (j > 0) {
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[m];
    }

    /**
     * 方法3：
     * dfs 深度优先搜索 实现方法 --》其实 和 dp条件类似
     * <p>
     * 时间：--》不好算啊。。。
     * 空间：O(mn) --》额外空间占用 有，三个一维数组 m，n，m+n；一个二维数组 mn；递归 调用栈 最多 m+n
     * --》所以是 O(mn)
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();

        boolean[][] visited = new boolean[m + 1][n + 1];

        return dfs(0, 0, 0, c1, c2, c3, visited);
    }

    /**
     * c1从i，c2从j开始，能否 交错组成 c3 从k 开始的子串
     */
    private boolean dfs(int i, int j, int k, char[] c1, char[] c2, char[] c3, boolean[][] visited) {
        // 1、终止条件 --》俩 都成功 匹配完了，那就是 true
        if (i == c1.length && j == c2.length) {
            return true;
        }

        // 2、处理 当前层

        // 剪枝，之前 从 这俩 结点，向下搜索过
        if (visited[i][j]) {
            return false;
        }

        // 3、递归 --》符合条件的，就 进入下一个字符匹配
        // 如果，i可以匹配，走 i+1 下去，都完全匹配，那就是 true
        if (i < c1.length && c1[i] == c3[k] && dfs(i + 1, j, k + 1, c1, c2, c3, visited)) {
            return true;
        }

        if (j < c2.length && c2[j] == c3[k] && dfs(i, j + 1, k + 1, c1, c2, c3, visited)) {
            return true;
        }

        // 4、善后 --》如果 前面 递归下去 不成功 或者 没有 不符合条件的话，当前 肯定 也就是 false了，
        // 但 还是要 标记下 剪枝
        visited[i][j] = true;

        return false;
    }


    /**
     * todo =========================================== 错误 思路 答案！============================
     * 其实，还可以换个思路：
     * 要是能组成 s3的 k个交错的字符串，那么：s3的 k-1个 一定 要能 交错组成 才行！
     * 并且，此时的 s1[i]==s3[k] 或者 s2[j]==s3[k]
     */
    public boolean isInterleaveX(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int t = s3.length();

        if (m + n != t) {
            return false;
        }

        boolean[] f = new boolean[m + n + 1];
        f[0] = true;

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                int k = i + j - 1;

                if (i > 0) {
                    f[k] = f[k] || (f[k - 1] && s1.charAt(i - 1) == s3.charAt(k));
                }

                if (j > 0) {
                    f[k] = f[k] || (f[k - 1] && s2.charAt(j - 1) == s3.charAt(k));
                }

            }

        }

        return f[m + n];
    }


}

