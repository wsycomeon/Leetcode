package com.wsy.leetcode.tree.AL_208_Trie;

public class Trie {


    /**
     * Your Trie object will be instantiated and called as such:
     * <p>
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    // TODO: 2020-08-12 22:01:43 Trie 树，一个 多叉、前缀树，也叫 字典树，单词 查找树
    // Trie树，是一个 特殊结构 的 多叉树
    // (1) 普通的多叉树，内部 一个 val 值，一个 list<node> 子结点 链表
    // (2) 而 Trie 树，内部一个 isEnd 判断 当前 字符 结点 是否是一个单词的末尾；还一个 固定大小的字符集 数组，比如 纯小写字母 就是 Trie[26] 的数组
    // 然后，这个 Trie树 结点，没有 存自身的 val 或者说 对应的字符，而是 由  父结点的数组 来定位 过来的！
    // Trie 树 一旦建成，就可以 很方便的查找了，但是 基本 不做 删除。
    // Trie 树 的 最顶部，没有 父 Trie 来 指向它


    // TODO: 2020-08-12 22:54:02 --》或者 要是 觉得 每层 都是 Trie 不好理解的话
    // todo 就 将这些 字段 信息 封装到 一个 TrieNode 里面去，这个 Trie 持有 根 node 的引用 就可以了！
    // --》 看下 Trie2 这个 实现！

    // TODO: 2020-08-12 23:13:26 官方的 另一个实现 --》看下 Trie3
    // https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/


    // TODO: 2020-08-12 23:28:31 整理完毕，最好理解 的是 Trie2 ！


    /**
     * 看了下 别人的 初级 写法：
     * https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/
     * <p>
     * 总结:
     * 我们可以总结出 Trie 的几点性质：
     * <p>
     * 1、Trie 的 形状 和 单词的插入 或 删除 顺序 无关
     * 也就是说 对于 任意给定的一组单词，Trie 的形状都是唯一的。
     * <p>
     * 2、查找或插入一个长度为 L 的单词，访问 next 数组的 次数 最多为 L+1，
     * 和 Trie 中 已包含 多少个单词 无关。
     * <p>
     * 3、Trie 的每个结点中 都 保留着一个字母表，这是很耗费空间的。
     * 如果 Trie 的高度为 n，字母表的大小为 m，最坏的情况（最最极端的） 是 Trie 中 还不存在 前缀相同的单词（每一结点 的 子结点 都有 所有的字符）
     * 那空间复杂度就为 O(m^n)。
     * <p>
     * 4、最后，关于 Trie 的应用场景，希望你能记住 8 个字：
     * 一次建树，多次查询。(慢慢领悟叭~~)
     */

    private boolean isEnd = false;

    // 这里的子结点的取值 都是 小写字母，所以 容量 = 26 ，对应的 index = chars - 'a'
    // 存的是，当前 字符后 有的字符
    private Trie[] next = new Trie[26];

    public Trie() {

    }

    /**
     * 插入 一个 String 到 trie 树
     * <p>
     * 时间：O(n) --》字符串长度，遍历 所有 字符
     * 空间：O(n) --》最差是 需要 新 创建 所有 字符
     */
    public void insert(String word) {
        // 从 当前 根结点开始，寻找 一层层的 子结点 来 匹配 每个字符
        // 如果 哪一层 没有匹配的，就新建一个 Trie 存入 对应位置。。
        Trie current = this;

        char[] chars = word.toCharArray();
        // 确保 每个 字符 都插入了
        for (char c : chars) {
            // 当前 字符 应在的 index
            int index = c - 'a';
            if (current.next[index] == null) {
                // new 出来一个结点，放到 对应 index 就可以了，不需要 给 新new的 Trie 赋值 之类的
                current.next[index] = new Trie();
            }

            // 当前 字符 匹配完了，换下一层
            current = current.next[index];
        }

        // 最后的 current 就是 最后一个字符 对应的 结点
        current.isEnd = true;
    }

    /**
     * 检查，之前 Trie 中 是否 恰好有 这个字符串 ！
     * <p>
     * 时间：O(n) --》最差是 匹配 每一个 字符
     * 空间：O(1) --》em，其实 char[]  可以 优化为 word.charAt(i) --》 这里 简写了
     */
    public boolean search(String word) {
        // 还是 一 一 匹配 每个字符
        Trie current = this;

        char[] chars = word.toCharArray();

        for (char c : chars) {
            int index = c - 'a';
            // c 应该存放的、对应的 结点
            current = current.next[index];
            if (current == null) {
                return false;
            }
        }

        // 如果，字符 都匹配上了，还要 判断，current 是不是 曾经 某个字符串的 最后一个字符
        return current.isEnd;
    }

    /**
     * 返回 是否有 prefix 的 字符串 在内
     * 和 search() 一样的处理，
     * 只是 最后 不需要 判断 那个 node 是不是 isEnd，直接 返回 true 即可
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    public boolean startsWith(String prefix) {
        Trie current = this;

        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            int index = c - 'a';
            current = current.next[index];
            if (current == null) {
                return false;
            }
        }

        // 前面 每个 char 都匹配上 就可以了
        return true;
    }


}
