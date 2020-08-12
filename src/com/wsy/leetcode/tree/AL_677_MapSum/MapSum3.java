package com.wsy.leetcode.tree.AL_677_MapSum;

import com.wsy.leetcode.tree.AL_208_Trie.Trie;

import java.util.HashMap;

public class MapSum3 {

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];

        // todo sum 表示，经过 这个 结点( 前缀 ) 的 所有字符串的 值 的累加和 ！
        // 还需要 记录 这个 字符串 --》hashMap
        // 更新的话，其 前缀上 每个 结点 要 减去 旧数值，然后 加上 新数值。。。

        // TODO: 2020-08-13 00:00:45 但是！ 都用了 HashMap 记录对应的 val 了，还用毛 Trie 树，脱裤子放屁，多此一举。。
        // 凑合 先写完 吧。。
        int sum = 0;
    }

    private TrieNode root = new TrieNode();
    private HashMap<String, Integer> map = new HashMap<>();

    public MapSum3() {

    }

    public void insert(String key, int val) {
        TrieNode node = root;

        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';

            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }

            // 如果 之前 包含了，这些个 node 都要 删除 之前的值
            node.next[index].sum -= map.getOrDefault(key, 0);
            // 新值 要 加上 它的路径上的 每个结点 上去
            node.next[index].sum += val;

            node = node.next[index];
        }

        // 注意，最后，存到map
        map.put(key, val);

    }

    public int sum(String prefix) {
        TrieNode node = root;

        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';

            // 没 能匹配 某些 字符，即 不存在 这个 字符串 前缀，返回 0 即可
            if (node.next[index] == null) {
                return 0;
            }

            // 继续 匹配下去
            node = node.next[index];
        }

        // 返回 完全匹配的 那个结点的sum值 就是了
        return node.sum;
    }

}
