package com.wsy.leetcode.tree.AL_501_findMode;

import java.util.Stack;

public class Official {


    // TODO: 2020-08-12 20:33:51 返回 BST 中 出现次数最多的数 的 数组（可能 出现次数 相同）
    // TODO: 2020-08-12 20:38:03 感觉 和 BST 特性 没啥 关系啊
    // 普通的树，这样做 --》普通的 dfs 遍历一次树，存下 所有结点值 和 出现的次数 到 map --》然后，求出现次数 val 最大的 key list 即可。
    // 但是，上面的显然 没有用到 BST 的 特性 --》而且 时间、空间 都很差

    // TODO: 2020-08-12 20:46:06 看了下 官方题解，可以的，两次 中序遍历(相等的数值 连在一起，好计算 count) 即可

    // TODO: 2020-08-12 21:20:23 搞定， 写起来 有点 别扭啊 ！

    /**
     * 方法1：
     * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/solution/zhen-zheng-fu-he-ti-mu-yao-qiu-de-o1kong-jian-fu-z/
     * <p>
     * 要求是 不适用 额外空间 --》那么 中间使用的 list、map 等 都不行 ！
     * 最后 返回的 要求是 int[] --》所以 我们需要 先知道 数组 到底多大
     * <p>
     * 1、第一次 中序遍历，确定 最大值 出现的次数，即 数组 的大小
     * 2、第二次 中序遍历，向 数组 填充 数据 就好了
     * <p>
     * 时间：O(n) --》遍历了 两次树，所有 结点。。。
     * 空间：O(1) --》因为 这里，本题说了，不计入 递归 的 额外占用空间。。。
     */

    // 最终数组
    int[] result;

    TreeNode pre;
    // 最大值
    int maxCount;
    int currentCount;

    // 两个作用，第一次遍历 当做 数组大小，第二次 复用 成 数组元素的index
    int returnCount;

    public int[] findMode(TreeNode root) {
        // 1、第一遍，计算出 size --》returnCount
        dfs(root);

        // 2、new 出数组
        result = new int[returnCount];

        // 除了 maxCount 都 初始化 ，这次 returnCount 作为 index 用了
        returnCount = 0;
        pre = null;
        currentCount = 0;

        // 3、第二遍 填充
        dfs(root);

        return result;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        // 中序遍历

        dfs(root.left);

        // 再 处理自身

        if (pre != null && pre.val == root.val) {
            currentCount++;
        } else {
            // 初始值 或者 不等，都是 新值，即 1
            currentCount = 1;
        }

        // 比 最大值 大
        if (currentCount > maxCount) {
            maxCount = currentCount;
            // 那 容量 就得 置 1
            returnCount = 1;
        } else if (currentCount == maxCount) {

            // todo 注意，在第二次 中序遍历时，maxCount 之前 已经固定了
            // 走到 这里来，意味着 是 目标元素，returnCount 就当做index 来用了，先赋值，在++
            if (result != null) {
                result[returnCount] = root.val;
            }

            returnCount++;
        }

        // 作为 pre 了
        pre = root;

        dfs(root.right);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
