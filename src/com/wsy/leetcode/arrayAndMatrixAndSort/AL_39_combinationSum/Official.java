package com.wsy.leetcode.arrayAndMatrixAndSort.AL_39_combinationSum;

import java.util.*;

public class Official {


    /**
     * 回溯算法 ---》
     * <p>
     * 其实 回溯就是做了 暴力遍历，只不过 做了 剪枝 优化
     * <p>
     * <p>
     * 实现1：
     * https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * 求 和（元素 可重复） 为 指定数 的 所有组合(单个元素 可被 多次选择) 的 集合
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (candidates == null) {
            // TODO: 2020/6/24 candidates.length == 0 没加0，因为 说不定 target 就是 0 呢
            return res;
        }

        int len = candidates.length;

        // 排序是为了 后面 剪枝， 能 提前 终止搜索 ，优化性能
        Arrays.sort(candidates);


        count1 = 0;

        // todo 队列 这里 使用的 ArrayDeque，在其最后 比较多的 add、remove 感觉 用 LinkedList 链表类 实现更好！
        dfs(candidates, len, target, 0, new ArrayDeque<>(), res);

        System.out.println("final count1 = " + count1);

        return res;
    }

    static int count1;

    /**
     * @param candidates 输入数组（元素 已 排好序）
     * @param len        输入数组的长度，冗余变量
     * @param residue    剩余 数值
     * @param beginIndex 本轮搜索的起点下标
     * @param onePath    从根结点到任意结点的路径 --》这个list 方便在最后 添加 和 删除 即可 --》deque 接口可以
     * @param results    结果集变量
     */
    private void dfs(int[] candidates, int len, int residue, int beginIndex, Deque<Integer> onePath, List<List<Integer>> results) {
        count1++;

        // 1、终止条件，恰好 走到末尾
        if (residue == 0) {
            // 由于 path 全局只使用一份，到叶子结点的时候 需要做一个拷贝
            results.add(new ArrayList<>(onePath));
            return;
        }

        // 2、处理当前层

        for (int i = beginIndex; i < len; i++) {

            // 在数组有序的前提下，剪枝

            // 当前 i 对应的值 都太大了，后面的 更大的 i 值 ，更不可能，
            // 即到达 当前层，后面 是不可能的了，直接 break 即可 ---》todo 或者 直接 不符合条件，跳过 即可，换个方法写 也许更好看
            if (candidates[i] > residue) {
                break;
            }

            // 以当前结点 为路径上一点，继续 搜索
            onePath.addLast(candidates[i]);

            // 3、递归 --》todo 注意，元素 可复用，所以 起点 还是 i
            // todo 注意，i 每上一层后，candidates 增大了，新加的路径 不能 再回退了，意思是 2 2 3 可以，对吧， 2 3   2（是不允许的 --》至少是 3 ！）
            dfs(candidates, len, residue - candidates[i], i, onePath, results);

            // 4、善后
            // todo 一个 dfs 终止后 --》终止条件是：恰好 找到路径了 或者 这条路不行，往前回溯
            // 即 以 当前 结点为路径上的 尝试 都已结束，移除 当前结点，尝试 下一个，i++
            // TODO: 2020/6/24 这里 用 同一个list 来存 各路径 还有一个想法 就是复用，方便回溯一点，而不是 新new一个，整个的 重新开始
            onePath.removeLast();
        }

    }


    /**
     * 回溯算法 实现2
     * <p>
     * todo 这里 对 元素组 不排序，直接回溯 ，效率会更差吗  ---》 不会
     * todo 所以，上面的 排序 是 无意义的！、
     * <p>
     * <p>
     * 反正我们用 beginIndex 配合 i++ 这样的限制了
     * 候选元素 从数组的头 到 尾，只能走一次，不能回头
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (candidates == null) {
            // TODO: 2020/6/24 candidates.length == 0 没加0，因为 说不定 target 就是 0 呢
            return res;
        }

        count2 = 0;

        dfs2(candidates, target, 0, new Stack<Integer>(), res);

        System.out.println("final count2 = " + count2);

        return res;
    }

    static int count2;

    private void dfs2(int[] candidates, int residue, int beginIndex, Stack<Integer> onePath, List<List<Integer>> results) {
        count2++;

        if (residue == 0) {
            results.add(new ArrayList<>(onePath));
            return;
        }

        for (int i = beginIndex; i < candidates.length; i++) {

            // candidates 更大的，不符合情况，直接 丢弃、剪枝
            if (candidates[i] <= residue) {
                onePath.push(candidates[i]);

                dfs2(candidates, residue - candidates[i], i, onePath, results);

                onePath.pop();
            }

            // 当前 i 尝试完毕，换 下一个元素 尝试
        }

    }

}
