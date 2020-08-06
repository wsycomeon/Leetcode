package com.wsy.leetcode.arrayAndMatrixAndSort.AL_39_combinationSum;


import java.util.*;

/**
 * //评测题目: 无
 * # [1,2,0,3,4,-1,3,-3]
 * <p>
 * # [1,2] [0,3] [4,-1,3,-3]
 * # 当且仅当 数组 能够被 切成三份 且三个子数组的和 相等
 *
 * todo 负数、重复数、以及 任意排序的 加入。。太复杂了
 *
 */
public class Another {

    // TODO: 2020/6/24 截止到 中午，没搞定，下面的算法 还是有缺陷。。。md

    public static void main(String[] args) {
        int[] a = {1, 2, 0, 3, 4, -1, 3, -3};
        System.out.println("array can be splitted to proper range combine = " + new Another().spliteArray(a, 3));

    }

    /**
     * 所以 相对于 判定 题目一：“在顺序不变的前提下，能不能 被切分 成 n个和相等的 数组” 难了很多。。
     * 那个 简单多了：
     * 1、求总体和，看除以 n 能否除尽，对 n 取模 即可；除不尽，直接false
     * 2、否则
     * （1）求出 单组和 sum，从前往后遍历累加，求元素和，每到sum，count+1就好了
     * （2）到最后，看 n 和 count 相等于否
     * <p>
     * <p>
     * 因为 没说 顺序不能动，也就是 排列、组合的个数 大大增加，难度就增加了
     * 但是，理论上 暴力回溯 依旧可以解决
     * 1、还是先求和，看 n 能否除尽，求出 除尽后的sum值
     * 2、然后，将元素排序，从前向后，依次取元素，保证不回头，求和为sum的集合
     * 3、然后，看是否 能选取出 其中 能 拼成 一个完整 元素组的集合 --》即 最后的 size 不为0
     */
    public boolean spliteArray(int[] candidates, int n) {
        // 1、边界条件
        if (candidates == null) {
            return false;
        }

        // 2、求总和
        int totalSum = getTotalSum(candidates);

        // 3、总和 除不尽，必然 false
        if (totalSum % n != 0) {
            return false;
        }

//        Arrays.sort(candidates);
//        System.out.println(Arrays.toString(candidates));

        // 4、能除尽的、单个组合的和
        int sum = totalSum / n;
        System.out.println("one range sum = " + sum);

        // 5、求出 和为sum 的所有组合
        List<List<Integer>> results = new ArrayList<>();
        Stack<Integer> onePath = new Stack<>();

        count = 0;
        dfs(candidates, 0, sum, onePath, results);

        System.out.println("count = " + count + " --> " + results);

        // 6、上述组合中，能拼接成 原数组的组合
        List<List<List<Integer>>> finalResult = new ArrayList<>();
        Stack<List<Integer>> oneGroup = new Stack<>();

        count2 = 0;
        dfs2(results, 0, null, getTempList(candidates), oneGroup, finalResult);
        System.out.println("count2 = " + count2);

        printAll(finalResult);

        // 7、返回结果
        return finalResult.size() > 0;

//        return false;
    }

    int count2 = 0;

    private void dfs2(List<List<Integer>> list, int beginIndex, List<Integer> last, List<Integer> target, Stack<List<Integer>> oneGroup, List<List<List<Integer>>> finalResult) {
        count2++;
//        System.out.println("count2 = " + count2 + " , beginIndex = " + beginIndex + " , target = " + target.size());

        // 1、终止条件
        if (target.size() == 0) {
            finalResult.add(new ArrayList<>(oneGroup));
            return;
        }

        // 2、处理当前层
        for (int i = beginIndex; i < list.size(); i++) {
            List<Integer> newOne = list.get(i);

            // 剪枝 --> 二者不能交集
            if (cantainsAll(newOne, last)) {
                continue;
            }

            // 剪枝，去除 不可能的情况
            if (newOne.size() <= target.size()) {
                oneGroup.push(newOne);
                // 剔除处理
                target = decreaseTarget(target, newOne);
                dfs2(list, ++i, newOne, target, oneGroup, finalResult);
                // 恢复
                target = rollBackTarget(target, newOne);
                oneGroup.pop();
            }

        }

    }

    private boolean cantainsAll(List<Integer> newOne, List<Integer> last) {
        if (last == null || last.size() > newOne.size()) {
            return false;
        }

        for (int i = 0; i < last.size(); i++) {
            // 有不等
            if (!newOne.get(i).equals(last.get(i))) {
                return false;
            }
        }

        return true;
    }

    private List<Integer> rollBackTarget(List<Integer> target, List<Integer> var) {
        target.addAll(var);
        return target;
    }

    private List<Integer> decreaseTarget(List<Integer> target, List<Integer> var) {
        for (Integer a : var) {
            target.remove(a);
        }

        return target;
    }

    int count = 0;

    private void dfs(int[] candidates, int beginIndex, int sum, Stack<Integer> onePath, List<List<Integer>> results) {
        count++;

        if (sum == 0) {
            results.add(new ArrayList<>(onePath));

            // todo 1、 因为负数的存在，终止条件 要更严格
            if (beginIndex == candidates.length - 1) {
                return;
            }

        }

        // 2、处理当前层
        for (int i = beginIndex; i < candidates.length; i++) {
            int candidate = candidates[i];

            // 剪枝，不可能的直接剔除 todo 因为负数的存在，不能 这样剪枝了
//            if (candidate <= sum) {
            // i 是可能路径，继续 dfs
            onePath.push(candidate);
            // 3、递归
            dfs(candidates, ++i, sum - candidate, onePath, results);
            // 4、善后，路径 尝试完毕，换下一个
            onePath.pop();
//            }
        }

    }


    private int getTotalSum(int[] candidates) {
        int sum = 0;
        for (int i = 0; i < candidates.length; i++) {
            sum += candidates[i];
        }
        return sum;
    }


    private List<Integer> getTempList(int[] candidates) {
        List<Integer> list = new ArrayList<>(candidates.length);

        for (int i = 0; i < candidates.length; i++) {
            list.add(candidates[i]);
        }

        return list;
    }


    private void printAll(List<List<List<Integer>>> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("===》 " + list.get(i));
        }
    }

}
