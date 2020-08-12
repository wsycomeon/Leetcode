package com.wsy.leetcode.tree.AL_530_getMinimumDifference;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Official {


    // TODO: 2020-08-12 19:57:37 求一个 都是 非负数的 BST上，任意 两结点的 差的绝对值 的 最小值
    // TODO: 2020-08-12 19:59:27 中序遍历，排序后，从前往后，依次 求差值的绝对值，取 最小值 即可

    // TODO: 2020-08-12 20:07:13 写完，测试 通过

    // TODO: 2020-08-12 20:09:32 em  ---》其实，中序遍历的时候，就是 后面的数值 比前面的大了，此时 可以求 差值的绝对值 和 min 比较了。。
    // 2020-08-12 20:18:15 好吧，一次 中序遍历 过程中，min 就搞定了。。。后面的 方法 2 3 都可以！

    /**
     * 方法1：
     * <p>
     * 中序遍历，求 相邻差值的最小值
     * <p>
     * 时间：O(n) --》升序 一次 n，遍历求解 n
     * 空间：O(n) --》list 占用，递归 调用栈 占用
     */
    public int getMinimumDifference1(TreeNode root) {
        // 题目说了，最少有俩结点了，不做 异常判断了

        // 1、中序遍历 得到 升序 数组
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 2、依次 求差值、绝对值的最小值

        // TODO: 2020-08-12 20:07:31 注意, min 取值 MAX !!!
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < list.size(); i++) {
            // 后面的大于前面的，所以，不用 求 绝对值了
            min = Math.min(min, list.get(i) - list.get(i - 1));
        }

        return min;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        dfs(root.left, list);

        list.add(root.val);

        dfs(root.right, list);
    }

    /**
     * 方法2：
     * <p>
     * 中序遍历的时候，直接 更新 min
     * <p>
     * 时间：O(n) --》一次 中序遍历
     * 空间：O(height) --》就 几个 指针 和 调用栈
     */

    // TODO: 2020-08-12 20:29:01 好吧，既然 int 不好取值 来代表 pre 结点 ，就用 node 对象 啊 --》 TreeNode preNode = null
    int pre;
    int min;

    public int getMinimumDifference(TreeNode root) {
        pre = -1;
        min = Integer.MAX_VALUE;

        dfs2(root);

        return min;
    }

    private void dfs2(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs2(root.left);

        // 第一个 结点时，pre 还没 赋值，不用计算 差值
        if (pre != -1) {
            min = Math.min(min, root.val - pre);
        }

        pre = root.val;

        dfs2(root.right);

    }

    /**
     * 方法3：
     * <p>
     * 一次 中序遍历的过程中，迭代实现
     * <p>
     * 时间：O(n)
     * 空间：O(height)
     */
    public int getMinimumDifference3(TreeNode root) {
        int min = Integer.MAX_VALUE;
        int pre = -1;

        Stack<Object> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj == null) {
                continue;
            }

            if (obj instanceof Integer) {
                if (pre == -1) {
                    pre = (int) obj;
                } else {
                    min = Math.min(min, (Integer) obj - pre);
                    pre = (int) obj;
                }
            } else {
                // 中序遍历，左 根 右，逆序 入栈 --》右 根 左
                TreeNode node = (TreeNode) obj;
                stack.push(node.right);
                stack.push(node.val);
                stack.push(node.left);

            }
        }

        return min;
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