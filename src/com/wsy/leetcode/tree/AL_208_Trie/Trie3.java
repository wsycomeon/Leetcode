package com.wsy.leetcode.tree.AL_208_Trie;

public class Trie3 {


    static class TrieNode {

        private TrieNode[] next = new TrieNode[26];
        private boolean isEnd = false;

        public boolean containsKey(char ch) {
            return next[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return next[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            next[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    private TrieNode root = new TrieNode();

    public Trie3() {

    }

    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }

        node.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);

        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);

        return node != null;
    }

    /**
     * 返回 恰能 匹配 word的 那个 最后结点
     */
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                // 不完全匹配，就是 null !
                return null;
            }
        }

        // 返回 完全匹配的 结点
        return node;
    }


}


