package com.wsy.leetcode.tree.AL_538_convertBST;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Official {

    // TODO: 2020-08-11 22:37:36 修改 BST 上 每个 结点的值 = 原结点值 + 大于它的结点值 之和，即 >= 当前结点的 数值 之和

    // TODO: 2020-08-11 22:43:47 观察发现
    // 1、中序遍历一次 得到 升序 数组，然后，计算根节点的转换数值sum，
    // 然后 依据 性质，左子结点的值 sum = root的sum + left的值；右子结点的sum = root的sum - root的值，在遍历一次 即可
    // 注意，传入 当前结点的对应sum值，先去计算 左右子结点的sum，递归，最后 在修改node.val
    // TODO: 2020-08-11 23:15:47 靠，自己想当然了 --》
    // todo 除了根节点之外，其他的 子树根节点的sum = 自身val + 父结点的sum + 自身 右子树的sum。。。

    // TODO: 2020-08-11 23:32:36 算了，只想到 暴力解法：中序遍历 得到顺序数组，然后，遍历 每个结点，在数组中 找到 >= 它的数值累加，是 O(n^2)



    // TODO: 2020-08-11 23:45:48 我曹，我 服气 ---》 中序遍历的 逆序遍历 一遍就行 --》O(n)
    // TODO: 2020-08-12 00:17:07 整理完毕，逆序思想 真的厉害 ，自己 怎么这么笨！
    // 这里 中序的 逆序 实现，模板 用 递归 比 迭代 好，因为 迭代 为了区分 访问到的结点（不是 Integer了）需要 封装一层 bean


    /**
     * 方法1：
     * 1、目标是 修改 树上 所有结点的值 =  >= 当前结点的 所有结点值 之和
     * 2、所以，必然需要 遍历 所有的结点 至少一次，才能 对其 修改！
     * 3、在考虑 >= 当前结点的 所有的值，可以理解为 从 当前结点 一直增加到 最大值，求累加和；
     * 逆向思考下，也就是 最大值 降低 到 当前值 的 累加 结果，对于 最大值 那个结点， 累加值 就是自身；
     * 之后，每累加一次自身，就 赋值当前值 给 自身 就好了
     * todo --》其实 就是 中序 遍历的 逆序，我们 就可以 依次访问到 原树上 从大到小 排列的结点了，
     * <p>
     * sum = 0，从 最后开始，
     * 原 树 上，每个结点 转换后的目标值 = 之前 比当前结点大的数值的 累加值 sum + 自身数值 即可 ！
     * <p>
     * ---》而 中序遍历的逆序 实现 很简单，
     * dfs 可以 递归模板 或者 迭代 模板
     * <p>
     * <p>
     * todo 先写 迭代模板 吧
     * 时间：O(n) --》所有结点 至少 访问一次
     * 空间：O(height) --》stack的容量，最多一颗 子树 的容量，最大 也是 O(n)
     */
    public TreeNode convertBST1(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 迭代模板
        Stack<Object> stack = new Stack<>();
        stack.push(root);

        int sum = 0;

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj == null) {
                continue;
            }

            if (obj instanceof NodeInfo) {
                NodeInfo info = (NodeInfo) obj;
                sum += info.val;
                info.node.val = sum;
            } else {
                TreeNode node = (TreeNode) obj;

                // 要的是 中序的逆序，逆逆 得正，所以 入队 顺序 就是 左、根、右
                stack.push(node.left);
                stack.push(new NodeInfo(node.val, node));
                stack.push(node.right);
            }

        }

        return root;
    }

    // 因为 要区分 访问到的元素，所以 得是 和 TreeNode 不同的类型，又因为 得到 累加值 之后 还需要 修改 原node，所以 需要 node 的引用
    static class NodeInfo {
        int val;
        TreeNode node;

        public NodeInfo(int val, TreeNode node) {
            this.val = val;
            this.node = node;
        }
    }


    /**
     * 方法2：dfs 中序 逆序的 递归模板
     * <p>
     * 这个好一些。。不用像 迭代模板一样，为了 区分 当前 被访问的 节点值，还封装 一个类
     * 时间：O(n) --》所有结点 至少 访问一次
     * 空间：O(height) --》递归调用栈 的深度，最多就是 向右 或者 向左 的一条单链表，那就是 O(n)
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);

        return root;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 中序是 左 根 右，逆序 就是 右 根 左
        dfs(node.right);

        sum += node.val;
        node.val = sum;

        dfs(node.left);
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
 
