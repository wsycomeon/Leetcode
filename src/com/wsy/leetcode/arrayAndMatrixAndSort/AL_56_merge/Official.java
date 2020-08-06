package com.wsy.leetcode.arrayAndMatrixAndSort.AL_56_merge;


import java.util.*;

public class Official {

    /**
     * 1、注意，一开始的区间集合 是无顺序的，即 并不是按照 区间的左端点进行排序的
     * 所以，一开始 要对其排序
     * 2、然后，将第一个区间 加入最后结果中，然后 和 后面的区间 一一比较，
     * （1）有重合，就合并后替换
     * （2）否则，直接 加入 集合
     * <p>
     * 3、注意，这里的区间，其实 就是 长度为2的数组，那就好办了
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 根据 起始元素 ，对 区间 集合 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        /*
         * 返回： 将 起始数据 按照 从小到大的顺序排序后的 区间 集合，进行合并后的 区间集合
         */
        List<int[]> list = getFinalInterval(intervals);
//        List<int[]> list = getFinalInterval2(intervals);

        // merge完毕，返回结果
        int size = list.size();
        System.out.println("最终区间个数 = " + size);

        // TODO: 2020/6/21 第一个 [] 表示 里面 元素是 数组的 个数
        int[][] result = new int[size][2];

        for (int i = 0; i < size; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    // 2、栈的方式
    private List<int[]> getFinalInterval2(int[][] intervals) {

        Stack<int[]> stack = new Stack<>();
        int[] temp = intervals[0];
        stack.push(temp);

        for (int i = 1; i < intervals.length; i++) {

            if (arrayIsCoincide(temp, intervals[i])) {
                // 有重合，弹出上次的
                stack.pop();
                temp = mergeArray(temp, intervals[i]);
            } else {
                // 没重合，直接加入即可
                temp = intervals[i];
            }

            stack.push(temp);
        }

        return stack;
    }


    // 1、 这里 不用 stack，直接用list 配合 while循环 也可以，找到不能在合并的区间，在放进去，不用 push 之后的 pop了。
    private List<int[]> getFinalInterval(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        int[] temp = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (arrayIsCoincide(temp, intervals[i])) {
                // 有重合，合并
                temp = mergeArray(temp, intervals[i]);
            } else {
                // 没重合，直接加入即可
                list.add(temp);
                // 更新 为新的
                temp = intervals[i];
            }
        }

        // 这种方式，还要把最后的temp加进去的
        list.add(temp);

        return list;
    }


    private int[] mergeArray(int[] a1, int[] a2) {
        int max = Math.max(a1[1], a2[1]);
        return new int[]{a1[0], max};
    }

    /**
     * 判定 两区间 是否重合，本身 a1[0] <= a2[0] 成立
     * 所以，只要判定，a1[1] < a2[0] 他们就完全错开，不重合了！
     */
    private boolean arrayIsCoincide(int[] a1, int[] a2) {
        return !(a1[1] < a2[0]);
    }


}
