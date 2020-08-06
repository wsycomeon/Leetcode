package com.wsy.leetcode.arrayAndMatrixAndSort.AL_378_kthSmallest;

import java.util.PriorityQueue;

public class Official {


    /**
     * 对于 一个 m x n ，向右、向下 都是单调自增的矩阵
     * 寻找 第 k 小的元素
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
     * <p>
     * 三个方法
     * <p>
     * 方法1：
     * 不理会 这个矩阵的性质，直接将 原矩阵 排序，然后 取 index = k-1 的元素；
     * 时间：O( MN * log( MN ) ) ，因为 需要对 MN 个元素 排序
     * 空间：O( MN ) , 额外 需要 这么大的一维数组。。
     * <p>
     * <p>
     * <p>
     * 方法2：
     * todo 第 k 小的元素，很容易 联想到 小顶堆  ---》 在java中 那就是 PriorityQueue
     * 这个矩阵，可以看成是 n列 从上至下 单调递增的 数组的 集合；
     * 可以联想到 之前 合并 K个 有序链表，而这里 实际上 是 合并 n个 有序数组，
     * todo 只不过 只需要 合并 K 个元素 就好了（不需要 全部 合并完毕）
     * <p>
     * 当然，合并的过程中，即 弹出一个最小数的时候，还要能知道 当前的元素是 哪一列的、第几排的 元素，好在 这一列 没有全部 弹完的情况下，把 下一个 入队！
     * todo 所以，队列的元素 需要做封装 --》记录 当前元素值、所在排、所在列；还要实现 比较接口；
     * <p>
     * 而且，这里 因为 只用到了 纵向递增 一个性质，没有充分 用到 横向 也是递增的性质，
     * 所以 注定 算法 不是最优的！
     * <p>
     * 时间：O(K logN)
     * 弹出 K个 元素，即 删除堆顶 K次，
     * 每次都要堆化一次，堆化的时间复杂度是 O(logn)
     * 空间：O(N)
     */
    public int kthSmallest1(int[][] matrix, int k) {
        // todo 题目说了，不用判断 matrix 和 k 的有效性；

        int row = matrix.length;
        int col = matrix[0].length;

        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        // 先 n列，每个头顶元素，入队一波，建堆
        for (int i = 0; i < col; i++) {
            queue.offer(new Node(matrix[0][i], 0, i));
        }

        // 先弹出 k-1 个最小值
        for (int i = 0; i < k - 1; i++) {
            // 弹出 当前 最小的那个node todo poll 这个api 可能空指针！
            Node node = queue.remove();

            // 看下 这个 node 所在列 还有没有元素，有的话，入队
            if (node.row != row - 1) {
                queue.offer(new Node(matrix[node.row + 1][node.col], node.row + 1, node.col));
            }

        }

        // 再弹出的，就是 第 k 小的那个数值了
        return queue.remove().val;
    }

    /**
     * 小顶堆 / 优先级 队列用 bean
     */
    static class Node implements Comparable<Node> {

        // 元素值
        int val;
        // 所在排 index
        int row;
        // 所在列 index
        int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        // 小顶堆，从小到大
        @Override
        public int compareTo(Node o) {
            return val - o.val;
        }

    }


    /**
     * 方法3：
     * 前面俩方法，分别 没用到 双向递增的性质，只用到了 纵向递增的性质，所以 性能还不到极致；
     * <p>
     * 本方法，则用到了 双向递增 的性质，对角线递增 --》 二分 ！
     * 确切的说，矩阵的取值区间是 left 是 [0][0] ---》right 是 [m-1][n-1]
     * <p>
     * 1、每次 二分，直接 取其中间值： mid
     * 计算 当前矩阵中 <= mid 的元素 有多少个，假设是 a 个；
     * <p>
     * 两个方法：
     * （1）从左上角开始，逐层遍历，<= mid 的值，++
     * --》时间复杂度 就是 O(M N)
     * todo 有点大，但是容易理解
     * <p>
     * （2）否则，需要画图，才能 比较容易 搞清楚
     * 对于 矩阵中的任意一个 元素，<= 它的元素，是一个 锯齿形 边界
     * 从左下角开始，发现某个元素 <= mid，就将 当前列 符合条件的个数，全部累加上去 --》即 += i+1(当前是 第i行，那这一列 就是 i+1 个元素了)，并且 元素 右移；
     * 否则，元素 上移 寻找合适的 下一个元素，直到 走出矩阵外；
     * todo --》由于 从左下角开始，一次走一步，走到 矩阵外，最多 m + n 步，所以时间复杂度 是 O(M + N) --> 大大优化了
     * <p>
     * 那比较 个数 a 和 k 的大小，如果 k > a，说明 第 k 小的元素值 x > mid，此时 缩小范围，left = mid + 1 ;
     * 否则，right = mid;
     * 2、继续 二分，直到 left >= right , 返回 left 就是 所求数值。。
     * todo 其实 left 一定是 矩阵内的 原始值 吗 ？--》这个需要 证明下 反证法？
     *
     * <p>
     * 时间：O( (M+N) log( high -low ) )
     * <p>
     * 二分区间是 low -> high , 最多进行 log(high - low) 次；
     * 而 每次的 时间复杂度是 M + N ;
     * <p>
     * 空间：O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        // todo 题目说了，不用判断 matrix 和 k 的有效性；

        int row = matrix.length;
        int col = matrix[0].length;

        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];

        // 一直二分，找到 合适的 元素值
        while (left < right) {

            // 矩阵 中间值的 大小
            int mid = left + (right - left) / 2;

            // 获取，当前矩阵中 <= mid值的个数
//            int count = getCount(matrix, row, col, mid);
            int count = getCount2(matrix, row, col, mid);

            // 如果 k值更大，说明 目标值 比 mid 大，缩小区间，继续二分
            if (k > count) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return left;
    }

    // 方法2：从左下角 开始 向右、向上 遍历
    private int getCount2(int[][] matrix, int row, int col, int mid) {
        int i = row - 1;
        int j = 0;

        int count = 0;
        // 只要在 矩阵内 即可
        while (i >= 0 && j < col) {
            if (matrix[i][j] <= mid) {
                // 符合条件的话，纵向上 上面的元素 都符合，累加
                count += (i + 1);

                // 向右探索，是否 还符合
                j++;
            } else {
                // 数值较大的话，要上移，寻找 符合条件的元素
                i--;
            }
        }

        return count;
    }

    // 方法1：从左上角开始，逐层遍历
    private int getCount(int[][] matrix, int row, int col, int mid) {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col && matrix[i][j] <= mid; j++) {
                // 符合条件，就 ++
                count++;
            }
        }
        return count;
    }


}
