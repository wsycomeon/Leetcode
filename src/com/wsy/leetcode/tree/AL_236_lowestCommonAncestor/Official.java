package com.wsy.leetcode.tree.AL_236_lowestCommonAncestor;

import java.util.*;

public class Official {

    // TODO: 2020-08-12 11:20:15 哎，2个月前 做过一次啊 ，又忘记了
    // 总之，最近 公共祖先 题目：
    // 1、普通二叉树的就是 两个方案：

    // (1) 定义一个函数 dfs ，判断 当前子树，是否 至少包含 p、q 中的一个；--》todo 性能 比 方法2  好一些
    // 然后 dfs 遍历，在 遍历的 过程中，
    // todo 先求 左、右子结点 含有的情况，然后 再判断 当前结点  是否 符合 条件，是 最近 公共祖先：
    // 左、右子树 各有一个 或者 自身有一个 且 左、右 其中有一个

    // (2) 记录 p、q的 父结点 链路 ，看 第一个重合的 父结点就好，具体 做法如下，
    // 用一个hashmap，dfs遍历的时候，记录 每个节点 --- 对应的父结点；
    // 然后，将 p 从 自身开始 向上 记录 它的所有 父结点 到一个set --》while 循环
    // 在 然后，q 从 自身开始，向上 访问 它的父结点，并检查 每个父结点 ，是否 也是 p 的（即 包含于 set 中）
    // 是的话，那就是 目标 祖先！


    // 2、BST 这种 特殊树 的 最近公共祖先：
    // 因为 自身 和 左、右子树 的 大小关系，有 特殊性质 可用
    // 发现，todo p q 的公共祖先结点 一定是 存在的，要么是 root 自身，要么 是在 root的两颗子树上 能找到，如下：
    // 自身 val 在 p、q 区间之外，
    // 比如，自己的值 过小，那么 那个 公共祖先节点，必然 只能存在于 node的右子树上了
    // 同理，自身过大，那 只能去 其 左子树上 寻找 p q 的公共祖先了；
    // 否则，自身 就是 p q 的最近公共祖先
    // --> 上述方案，可以 方法 递归 实现，或者 迭代 实现，





    private TreeNode ans;

    /**
     * 最近 公共 祖先，且深度最大
     * <p>
     * 1、递归来做
     * 所有节点的值都是唯一的。
     * p、q 为不同节点 且均存在于 给定的二叉树中。
     * <p>
     * ===
     * <p>
     * 所以，这里 边界条件 没加 ！
     * <p>
     * 时间：O(n)
     * 空间：O(height)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);

        return this.ans;
    }


    /**
     * todo ：判断 当前结点 为 root的 子树 至少含有 p、q 中的一个 ！
     * 题目限制了 p、q 不重复了！
     * <p>
     * 从上 往下 找的，
     * 但是 递归 是 从底部 开始返回的，
     * 所以 最先 找到的 那个就是 --》后面 向上 归 的时候，不可能 再有 满足 的了
     */
    private boolean dfs(TreeNode current, TreeNode p, TreeNode q) {
        // 1、终止条件
        if (current == null)
            return false;

        // 2、处理当前层

        // 3、递归 左、右子树 有没有 其中一个 结点

        boolean leftHadOne = dfs(current.left, p, q);
        boolean rightHadOne = dfs(current.right, p, q);

        // 4、善后，
        // --》 判断 当前结点 是不是 其 最近 祖先结点！！！
        // ---》注意，首次 找到 最近祖先结点后，而 这个 祖先结点 它本身作为 一个孩子（左 或者 右，却 同时拥有了 p、q）
        // 另一个孩子 和 父结点 自身 都没有，则 导致 其 父节点 不满足 最近 祖先结点的 条件了 ！

        // 条件：
        // （1） p、q 是其 子树 分别 所有的；
        // （2） 或者 current 就是 p、q 其中一个结点，并且 某子树 有 另一个结点
        if ((leftHadOne && rightHadOne) ||
                ((current.val == p.val || current.val == q.val) && (leftHadOne || rightHadOne))) {

            // 更新 当前节点 为 最近祖先节点
            ans = current;
        }

        // 返回 当前结点 为root的 子树 是不是 至少有 p、q 中的一个
        return leftHadOne || rightHadOne || (current.val == p.val || current.val == q.val);
    }


    /**
     * 方法2：
     * <p>
     * 1、先记录 所有结点的父节点 到 hashMap中
     * 2、从 p 结点，开始向上 不断 访问其父节点，并标记 访问过的父节点，这里是 加到 set 中
     * 3、再从 q 结点，开始向上 访问，如果 遇到 某个父节点 被访问过，那么 这就是 二者的 最近公共祖先
     * <p>
     * 时间：O(n) --》最耗时间的 还是 第一步 求hashMap的
     * 空间：O(n) --》额外空间占用有：递归调用 是 height，map 是 n，set 最多是 n，所以 就是 O(n)
     */

    // 所有结点（其值）和 其 对应的 父节点 的 map
    Map<Integer, TreeNode> parentMap = new HashMap<Integer, TreeNode>();

    // p的父节点们
    Set<Integer> visited = new HashSet<Integer>();

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        // 1、记录 所有结点的父结点
        dfs2(root);

        while (p != null) {
            // todo 自身 也可以作为 自己的父节点的，所以 自己 也要加进去。
            visited.add(p.val);

            // 向上 找父节点
            p = parentMap.get(p.val);
        }

        // 然后 再从 另一个 q 结点 开始 向上，访问 它的 父结点（包括 自身）
        while (q != null) {

            // 发现 q 的父节点 也是 p 的父节点，那就是 了！
            if (visited.contains(q.val)) {
                return q;
            }

            // 找不到 就向上找
            q = parentMap.get(q.val);
        }

        // 如果 前面没找到 并提前返回，那就是没有，返回 full
        return null;
    }

    /**
     * 记录 所有结点的父节点
     */
    public void dfs2(TreeNode root) {

        if (root.left != null) {
            // key是 子节点的值
            parentMap.put(root.left.val, root);
            // 递归
            dfs2(root.left);
        }

        if (root.right != null) {
            parentMap.put(root.right.val, root);

            dfs2(root.right);
        }
    }


    /**
     * todo 草草草！！！---------------------------------------------------------------------------------------------------------------------------------------
     * 这个规律 不对。。通用性 不够强！
     * 这个题目 没有规定 这是 完全二叉树。。。日日日
     * 下面 这个实际上 用到了 完全二叉树 的性质
     * todo 中间 有几个null 还能坚持。null太多（主要是 他们不会自动补充所有的 null，当发现有null后，不会给null 加 null 而是直接跳过了。。） 而且没有规律 就不行了 ！！！
     * <p>
     * todo 比如：下面的用例 就不行！！！
     * <p>
     * 26 / 31 个通过测试用例
     * 状态：解答错误
     * 提交时间：0 分钟之前
     * 输入：
     * [37,-34,-48,null,-100,-101,48,null,null,null,null,-54,null,-71,-22,null,null,null,8]
     * -71
     * 8
     * 输出：
     * 37
     * 预期：
     * -54
     * <p>
     * -----
     * 方法3：
     * 和方法2 基本一样的思想，也是 从 下面的 p、q 向上找。
     * 但是 利用二叉树 转换成 数组存储时的 index，
     * 然后 index + 1 直接 / 2 向上寻找父节点
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // 1、存储到 list 中，并且记录 p、q的index
        breadthScan(root, p, q);

        // 2、两者 交替向上 遍历父节点，看有没有相交，求相交的index
        // index + 1 后的，才有规律
        int pStart = pIndex + 1;
        int qStart = qIndex + 1;

        while (pStart != qStart) {
            if (pStart < qStart) { // index 大的、落后的 先上探
                qStart /= 2;
            } else {
                pStart /= 2;
            }
        }

        // 修正为 index，防越界
        pStart = pStart > 0 ? pStart - 1 : pStart;

//        System.out.println("lowestCommonAncestor index = " + pStart);

        return list.get(pStart);
    }

    List<TreeNode> list = new ArrayList<>();
    LinkedList<TreeNode> linkedList = new LinkedList<>();

    // 当前链表元素的index（包含 中间的 null 子结点）
    int index = -1;
    int pIndex = -1;
    int qIndex = -1;

    private void breadthScan(TreeNode root, TreeNode p, TreeNode q) {
        linkedList.add(root);

        TreeNode current = null;

        while (!linkedList.isEmpty()) {
            current = linkedList.poll();
            // 只要 poll出来，就要 add 到 list --》最终会因为 非空的、叶子节点 多出 叶子结点数 x2 的空结点，
            // 但是还好，不影响计算结果 ！
            list.add(current);
            index++;

            // 记录 p、q的index
            if (current == p) {
                pIndex = index;
            }
            if (current == q) {
                qIndex = index;
            }

            // 如果 当前结点 不为null，不管子结点 怎样，都加进去
            if (current != null) {
                linkedList.add(current.left);
                linkedList.add(current.right);
            }
        }

//        System.out.println("final index = " + index + " , p index = " + pIndex + " , q index = " + qIndex);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
