package com.wsy.leetcode.tree.AL_208_Trie;

public class Trie2 {

    static class TrieNode {
        private boolean isEnd = false;
        // 这里的子结点的取值 都是 小写字母，所以 容量 = 26 ，对应的 index = chars - 'a'
        // 存的是，当前 字符后 有的字符
        private TrieNode[] next = new TrieNode[26];
    }

    /**
     * 将 结点 信息 封装成 一个bean，然后 让 Trie 持有 根 root 就可以了！
     */
    private TrieNode root = new TrieNode();

    public Trie2() {

    }

    /**
     * 插入 一个 String 到 trie 树
     */
    public void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.next[index] == null) {
                current.next[index] = new TrieNode();
            }

            current = current.next[index];
        }

        current.isEnd = true;
    }

    /**
     * 检查，之前 Trie 中 是否 恰好有 这个字符串 ！
     */
    public boolean search(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';
            current = current.next[index];
            if (current == null) {
                return false;
            }
        }

        return current.isEnd;
    }

    /**
     * 返回 是否有 prefix 的 字符串 在内
     * 和 search() 一样的处理，
     * 只是 最后 不需要 判断 那个 node 是不是 isEnd，直接 返回 true 即可
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            current = current.next[index];
            if (current == null) {
                return false;
            }
        }

        return true;
    }


}
