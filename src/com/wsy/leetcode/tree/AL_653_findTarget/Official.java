package com.wsy.leetcode.tree.AL_653_findTarget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Official {

    // TODO: 2020-08-12 17:23:41 BST 上 是否存在 两数之和 = target
    // TODO: 2020-08-12 17:27:17 中序遍历 成 一个 升序数组，然后 双指针 从 两端走，看 求和 是否相等 即可，
    // 和 不够，i 右移；和 大了，j 左移； 和 相等  就是 true！
    // TODO: 2020-08-12 17:44:10 写完 自己的了

    // TODO: 2020-08-12 17:55:40 哎，上面 边界 自己 没写好 ！

    // TODO: 2020-08-12 18:01:13 看了 官方题解，除了 我自己写的这个 方法1（while 循环 双指针，比我这 for + while 循环 的 不伦不类的写法 要好 ！）
    // todo 还有一个 方法2： 借助 hashSet 来求 target - val 的方法
    // --》其实就是 求 数组的 两数之和的 常用方法，用一个set记录 访问过的值 ，set中 存在的话，返回true，否则 把自身放进去，进行后续 遍历查找，这里 直接 dfs、bfs 都行

    // TODO: 2020-08-12 18:21:06 整理完毕

    /**
     * 方法1：
     * todo 转换成 升序数组，求 两数之和 即可 --》用 下面的 另一个 while + 双指针 写法！
     * <p>
     * 时间：O(n) --》dfs 访问了所有结点，是 n；后面的 for + while 两个循环 i走了n次，j总计也走了n次，平摊到n次就是1，所以 也是 n
     * 空间：O(n) --》dfs 栈深度是 height，但是 list 是 n
     */
    public boolean findTarget1(TreeNode root, int k) {
        // 1、转换成 升序链表
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 2、然后 从 升序 list 中 查找 是否存在

        // 第二个数 从 末尾 向前移
        int j = list.size() - 1;

        for (int i = 0; i < list.size(); i++) {

            // 求 sum 和 k 比较
            // TODO: 2020-08-12 17:54:46 注意 这里的 边界条件 --》 i < j 才行，要取 左边一个，右边一个！
            while (i < j && list.get(i) + list.get(j) > k) {
                // j 向左移动，看是否 能 降低到 相等
                j--;
            }

            // 跳出 循环后，
            // 重合了，即 没找到
            if (i == j) {
                break;
            }

            // 看 是否是 相等的
            if (list.get(i) + list.get(j) == k) {
                return true;
            }

            // 否则，就是 和 < k ，i 向右移动

        }


        return false;
    }

    // 中序遍历，填充到 list
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    /**
     * 方法1的 优化方法：
     * while 循环 替代 for + while 循环
     * <p>
     * 时、空 复杂度一样，都是 O(n)
     */
    public boolean findTarget(TreeNode root, int k) {
        // 1、转换成 升序链表
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 2、从两端 开始 向内 求和 即可
        int left = 0;
        int right = list.size() - 1;

        // 要是 来个 不同的 结点，所以 不能相等
        while (left < right) {
            int sum = list.get(left) + list.get(right);

            if (sum == k) {
                return true;
            }

            if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }


    /**
     * 方法2：
     * <p>
     * 借助 hashSet 记录，访问过的 结点值
     * 遍历 所有结点，从上往下， dfs 、bfs 都可以
     * 这里，使用 递归 dfs
     * <p>
     * 时间：O(n) --》遍历到 true，即返回，理论上来说 不需要 遍历 所有子结点，最坏情况下 是 遍历所有，即 O(n)
     * 空间：O(n) --》额外空间占用：
     * 递归调用栈 是 height < n；
     * 但是 还有 set 的容量，最多是 存储 n个 结点值，都访问过了。。所以 是 O(n)
     */
    public boolean findTarget2(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs2(root, k, set);
    }

    private boolean dfs2(TreeNode root, int k, HashSet<Integer> set) {
        // 1、终止条件
        if (root == null) {
            return false;
        }

        // 2、处理 当前层

        // 存在 当前数值的 目标差值
        if (set.contains(k - root.val)) {
            return true;
        }

        // 否则，不存在，把 自身存入 set
        set.add(root.val);

        // 3、递归，看下 左、右子树 是否 存在
        return dfs2(root.left, k, set) || dfs2(root.right, k, set);
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
