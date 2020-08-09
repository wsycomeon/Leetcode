package com.wsy.leetcode.tree.AL_111_minDepth;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class Official {

    // TODO: 2020-08-09 14:45:30 树的最小深度

    // TODO: 2020-08-09 14:45:58 好吧，差点 搞错了 --》直接比较 所有到达 叶子节点的深度的 最小值；
    // 虽说 遍历的话，dfs bfs 都可以，但是 bfs 一直没有 到达 叶子结点。。。不好比较最小深度 来剪枝； --》todo 屁话！bfs 也可以的，看 方法3 ！
    // 而 dfs 可以做剪枝，如果 到达某个结点 已经大于 之前的min了，这条路 就不用看了，去看其他的 即可 ！
    // TODO: 2020-08-09 15:09:30 坑爹玩意 --》这题的要求是， 一个树，如果 只有一个 子结点，其长度 应该是 2 而不是 1
    // 即 根据题意---》这题是 计算 根结点 -->　叶子结点 的最短值。。。那么 根结点 有一个 子结点，那也就 不能 当成 叶子结点了。。


    // TODO: 2020-08-09 15:41:32 按照 官方的 dfs 写了一遍。。顺道 修改了下 自己的 dfs（毕竟 剪枝了，效率 比官方 高一些）

    // TODO: 2020-08-09 16:23:28 按照 官方的 bfs 写了一遍。。又浪费了 40 分钟，草！

    int min = Integer.MAX_VALUE;

    /**
     * 方法1：
     * 自己 写 的 dfs 遍历
     * <p>
     * 时间：O(n) --》每个结点 最多 被遍历 一次 所以 是 n，因为剪枝，可能 还不到 一次
     * 空间：O(height)  --》 递归调用的深度，最多是 height --> 最后才 遍历到 最短路径，而 height的取值是 [logn , n]
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        min = Integer.MAX_VALUE;

        // root 不为 null ，那就是 有一个结点了
        dfs(root, 1);

        return min;
    }

    /**
     * 到达 某个结点，看下 是否 向下遍历
     */
    private void dfs(TreeNode node, int curDepth) {
        // 1、终止条件 --》到达 某个 叶子结点了
        // todo --》注意，到达 叶子结点的 判断条件是，没有 子结点 ！
        if (node.left == null && node.right == null) {
            min = Math.min(min, curDepth);
            return;
        }

        // 2、处理 当前层 ---> 还 没有到达 叶子结点，即 当前 还有 子结点
        // 中途 发现 深度 已超，就不用 向下了，这条路径 不对，直接 return！

        if (curDepth + 1 > min) {
            return;
        }

        // 3、递归
        if (node.left != null) {
            dfs(node.left, curDepth + 1);
        }

        if (node.right != null) {
            dfs(node.right, curDepth + 1);
        }

        // 4、善后 --》本身 从 当前层 回到 上层的话，要消除 当前层的影响
        // --》但是 因为 传递的 curDepth 是一个值，不是 引用，即 没有影响，这里不需要处理

    }

    /**
     * 方法2：官方的 dfs --》todo 没有做 剪枝优化的，但是 更容易理解的
     * 一棵树的最短路径 =
     * （1）空树，0
     * （2）没有子结点，1
     * （2）至少 有一个 子结点 --》因为 自身 有子结点，不能作为 叶子结点，所以，还得 必须加上一个 叶子结点 才行
     * 即 子树自身 至少有一个 结点 才有意义，
     * todo --》当前 树的最短深度  = 俩子树的 最短深度的 较小值（非空子树 才是 有效的值） + 1
     * <p>
     * 时间：O(n)  --> 没做剪枝，必然 全部 遍历一遍
     * 空间：O(height)  --> 还是上面的 [logn , n]
     */
    public int minDepth(TreeNode root) {
        // 上述的几种情况：
        // 1、
        if (root == null) {
            return 0;
        }

        // 2、
        if (root.left == null && root.right == null) {
            return 1;
        }

        // 3、当前树 至少 有一个子结点

        // 求 左、右子树的 最小深度 的 较小值
        int min = Integer.MAX_VALUE;

        if (root.left != null) {
            min = Math.min(min, minDepth(root.left));
        }

        if (root.right != null) {
            min = Math.min(min, minDepth(root.right));
        }

        // 自身的最小深度 = 1 + 左、右子树最小深度的 那个 较小值
        return 1 + min;
    }


    /**
     * 方法3：
     * bfs 广度 优先搜索 ；
     * <p>
     * <p>
     * 用 queue 按层 来存，一次 遍历 一层，depth++;
     * 其实 这样做 不好 --》 因为 我们 还要 计算 当前层的元素个数 size ，来控制 depth，访问一个，减去 一个。。。很麻烦
     * todo 所以 这里，直接用 Pair，数据对，存：当前结点 和 到达 当前结点的深度值 --》 queue 存的是 Pair
     * <p>
     * 直到 到达 第一个叶子结点，对应的 就是 最小深度 了 ！
     * <p>
     * 时间：O(n) --》 访问到 第一个 叶子结点 就结束了，一般来说 要小于n
     * 最差就是 平衡树，访问到最后一层。。这样的话，就访问了 n/2 个结点，所以 最差是 O(n)
     * <p>
     * 空间：O(n)  --》最差 就是 上面的 平衡树 那样了 --》访问到最后一层的第一个叶子结点时，所有 其他 叶子结点 都入队了，最多是 n/2 个
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        // 最小深度值
        int minDepth = 1;

        // 初始值
        queue.offer(new Pair<>(root, 1));


        while (!queue.isEmpty()) {

            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            Integer depth = pair.getValue();

            // 判断 当前 是否是 叶子结点
            // 是的话，这就是 最小深度
            if (node.left == null && node.right == null) {
                minDepth = depth;
                break;
            }

            // 不是 叶子结点的话，至少 有一个 子结点，子结点 入队 待访问 ！
            if (node.left != null) {
                queue.offer(new Pair<>(node.left, depth + 1));
            }

            if (node.right != null) {
                queue.offer(new Pair<>(node.right, depth + 1));
            }

        }


        return minDepth;
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
