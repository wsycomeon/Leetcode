package com.wsy.leetcode.arrayAndMatrixAndSort.AL_215_findKthLargest;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的 第 k 个最大的元素，而 不是 第 k 个 不同的元素 !
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Official {


    /**
     * 方法1：排序后，n个数，第k大，实际上就是 逆序 第k个元素
     * 或者说 正序 第 n + 1 - k，但是 index 就是 n - k
     * 时间复杂度 最多是 O(nlogn)
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }


        Arrays.sort(nums);

        return nums[nums.length - k];
    }


    /**
     * 2、上面 时间复杂度 略高，用其他方式优化 --> 空间 换时间
     * 比如，小顶堆，容量是 k个元素，比堆顶大的 就放进去；
     * 最终 堆顶 就是 第k大的元素
     * <p>
     * O(nlogk)
     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return -1;
        }

        // todo 直接用 现成的堆实现 --- 优先级队列（本质是 无界的、小顶堆） ---》 当然了，这里 肯定是 自己写堆 性能更好的；
        //  因为下面 每次add后，还要判断 是否删除一个，实际上这一步 可以在入堆时，一次搞定的。。。而这里分了两次。。哎
        // 这里，多申请一个，防止扩容
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);

        // todo 干！注意了，构造函数的那个是 初始容量！ 这玩意儿是 无界的、自容扩容的！ 不会帮你 自动控制容量的，需要手动控制！
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);

            // 发现，超目标容量了，要 控制容量为k，主动 把堆顶删除！
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 取堆顶
        return heap.poll();
    }


    /**
     * 方法 3
     * 快排思想 --》本身 快排 是 取基准，然后 划分区间，分别排序的，时间复杂度是 O(nlogn)
     * --》特点是，基准点 最后所在的位置 i 表示，它前面有 i 个数据 比他小，则 他就是 第 i+1 小了
     *
     * 这里，我们 选取基准 快速分区间后，只需要 k 应该在的 那一半区间 就好了（不需要 俩区间 都继续 快排！）
     * --》用 n-k 和 i 比较 即可，缩小区间 来 二分（不对等的 二分 ！）
     * 所以，理论上来说，时间复杂度 会 优化很多
     * todo --》最后下降为 O(n) ？？？
     * <p>
     * 注意，第 k 大，就是 第 n+1-k 小，但是其 index 就是 n-k
     */
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return -1;
        }

        this.nums = nums;
        int size = nums.length;

        return quickselect(0, size - 1, size - k);
    }

    // 全局变量 存一下
    int[] nums;

    public int quickselect(int left, int right, int k_smallest) {
        if (left == right)
            return this.nums[left];

        // todo 先选定一个基准，其实也 可以直接取 最后一位的；后面 找位置的 开始处，都不用 交换操作了
        Random random_num = new Random();
        int pivotIndex = left + random_num.nextInt(right - left);

        // todo 主要 就是 找到 这个分区点的位置（这个点 左边数值 小，右边数值 大，和 目标index比较）
        pivotIndex = partition(left, right, pivotIndex);

        // 和 锚点比较。二分查找
        if (k_smallest == pivotIndex)
            return this.nums[k_smallest];

        else if (k_smallest < pivotIndex) // 锚点大了，往左找
            return quickselect(left, pivotIndex - 1, k_smallest);

        // 锚点小了
        return quickselect(pivotIndex + 1, right, k_smallest);  // go right side
    }


    public int partition(int left, int right, int pivot_index) {
        int pivotVal = this.nums[pivot_index];

        // 1、直接 先把基准数值 交换到 最后
        swap(pivot_index, right);

        // todo 记录 pivot 最终存放的 合适位置，最开始 假设在 区间起点
        int properIndex = left;

        // 2. 将区间内 所有小于pivot的 都移动到 左边
        for (int i = left; i <= right; i++) {

            // 发现 比我锚点数据 小的，多了一个符合条件的，则 properIndex 要和 i 交换数据，properIndex 要右移一位
            if (this.nums[i] < pivotVal) {
                swap(properIndex, i);
                properIndex++;
            }
        }

        // 3. 最后 交换一次，将 pivot值 设置到 合适位置 properIndex
        swap(properIndex, right);

        return properIndex;
    }


    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }


}
