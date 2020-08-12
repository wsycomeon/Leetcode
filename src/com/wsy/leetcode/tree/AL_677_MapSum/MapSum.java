package com.wsy.leetcode.tree.AL_677_MapSum;

import com.wsy.leetcode.tree.AL_208_Trie.Trie;

public class MapSum {

    /**
     * Your MapSum object will be instantiated and called as such:
     * MapSum obj = new MapSum();
     * obj.insert(key,val);
     * int param_2 = obj.sum(prefix);
     */

    // TODO: 2020-08-12 23:35:46 构建 Trie 树 即 前缀树，并且 node 作为 某个 字符串 结尾时，带有一个 数值val，表示 这个 字符串 的 值
    // 感觉 就是 TrieNode isEnd 时，多了一个 val

    // TODO: 2020-08-12 23:46:41 em，这个题，如果 不要求 必须是 Trie 树 实现的话，内部 用 hashMap 也很简单 !
    // key - val --》看 MapSum2

    // TODO: 2020-08-12 23:56:43 看了 官方的另一个 Trie 树 解法
    // --》看 MapSum3 ，这里的 node 的 字段，删除了 isEnd 字段，加了一个 sum 字段，表示 经过 该字符 结点的 字符串的 累加和
    // --》尼玛 还得用 hashMap，脱裤子 放屁。。。。

    // TODO: 2020-08-13 00:32:21 --》 根据 自己 最初的 思路，写了一个 纯粹的 Trie树 实现，如下 MapSum

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];

        // isEnd = true 时，才有 意义！
        boolean isEnd = false;
        int val;
    }

    private TrieNode root = new TrieNode();

    /**
     * 方法1：不用 HashMap，纯粹 用 Trie树
     */
    public MapSum() {

    }

    /**
     * insert ，就是 插入 Trie 树
     * 这个 字符串 和 对应的值，
     * <p>
     * 时间：O(n) ---》key 的长度
     * 空间：O(n) ---》最多 创建 n 个 新结点！
     */
    public void insert(String key, int val) {
        TrieNode node = root;

        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';

            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }

            node = node.next[index];
        }

        // 标记为 end 结点
        node.isEnd = true;
        // 值 要 刷新！
        node.val = val;
    }

    /**
     * 求出 所有的 包含 这个 prefix 的 字符串，以及它们的值，
     * 求 和 返回
     * <p>
     * sum
     * --》1、匹配 prefix 每个字符，到达 最后的 node 时
     * --》2、从 node 开始 dfs 遍历，非空 、到达 isEnd 时，sum += 即可
     * <p>
     * <p>
     * 时间：--》没办法 估计。。。看 Trie 树 现有情况。。
     * 空间：--》没办法 估计。。。看 Trie 树 现有情况。。
     */
    int sum = 0;

    public int sum(String prefix) {
        TrieNode node = root;

        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';

            // 没有 这个前缀
            if (node.next[index] == null) {
                return 0;
            }

            node = node.next[index];
        }

        // 当前 node 就是这个 prefix 最后的结点了
        // dfs 求和
        sum = 0;

        dfs(node);

        return sum;
    }

    private void dfs(TrieNode node) {
        // 1、终止条件
        if (node == null) {
            return;
        }

        // 2、处理当前层
        if (node.isEnd) {
            sum += node.val;
        }

        // 3、递归
        for (int i = 0; i < node.next.length; i++) {
            dfs(node.next[i]);
        }

        // 4、善后
    }


}
