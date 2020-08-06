package com.wsy.leetcode.string.AL_40_restoreIpAddresses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * 其实可以理解成，将 三个 点 依次放置到 合适的位置！
 */
public class Official {
    /**
     * 原字符串
     */
    private String src;

    /**
     * 原字符串长度
     */
    private int length;

    /**
     * 最终 符合要求的 字符串 列表
     */
    private ArrayList<String> output = new ArrayList<String>();

    /**
     * 临时存储 每个点 对应的 字符串片段的 列表
     */
    private LinkedList<String> segments = new LinkedList<String>();


    public List<String> restoreIpAddresses(String s) {
        length = s.length();

        // 边界条件！
        if (length > 12 || length < 4) {
            return output;
        }


        src = s;

        // dfs
        backtrack(-1, 3);

        return output;
    }

    /**
     * prev_pos : the position of the previously placed dot
     * 上一个点 对应的位置：这个位置 是指 在 原字符串中的 index，只是标记出 在哪个位置
     * dots : number of dots to place
     * 现在还剩下 几个点 没有摆放，最开始 是 3个
     */
    public void backtrack(int prev_pos, int dotsCount) {

        int startIndex = prev_pos + 1;

        // 点的index 只能是 [prev_pos + 1 , prev_pos + 4)
        // 点 最多 是在 原字符串的最后一位
        int max_pos = Math.min(prev_pos + 4, length - 1);

        // 每次 后移一位，寻找 点 放置的位置 curr_pos
        for (int curr_pos = startIndex; curr_pos < max_pos; curr_pos++) {

            // 每次 新添加一个字符（截取的起始位置不变，主要是 末尾位置 后移一位），形成 新的segment
            // 都要判断 是否是有效的
            String segment = src.substring(startIndex, curr_pos + 1);

            if (valid(segment)) {
                // 找到一个符合的segment
                // 说明可以 按照现有位置 消耗一个 点了，继续 递归排列 剩下的点，dfs，实验所有的结果
                segments.add(segment);

                if (dotsCount - 1 == 0)
                    // 说明 本身 这就是最后一个点了，说明 摆放完毕了，检测结果是否 ok
                    update_output(curr_pos);
                else
                    // 说明 还有剩下的点，继续递归处理，此时 还剩下  dots - 1 个点
                    backtrack(curr_pos, dotsCount - 1);

                // 递归完毕，说明 这个点 在当前位置的 后续实验，已经结束，寻找 下一个 合适的位置 来放置当前的点
                // 所以要把 这个点此时位置 对应的片段 删掉，继续查找！
                segments.removeLast();
            }

        }
    }

    /**
     * Check if the current segment is valid :
     * 检测 当前字符串片段 是否有效，首先 长度 不能 大于 3
     * 1. less or equal to 255       要么 <=255
     * 2. the first character could be '0' only if the segment is equal to '0' 要么 只能是 0 这个字符串
     */
    public boolean valid(String segment) {
        int m = segment.length();
        if (m > 3)
            return false;

        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    /**
     * Append the current list of segments to the list of solutions
     */
    public void update_output(int curr_pos) {
        // 前面已经有 3个截取片段了，看下 最后剩下的片段 这一个 符合要求不，
        String segment = src.substring(curr_pos + 1, length);

        // 如果符合，说明 本次这样拆分，是符合要求的
        if (valid(segment)) {
            segments.add(segment);

            // 给segments列表中的 每两个元素之间 加一个 . 生成一个字符串，添加到最终 列表 output
            output.add(String.join(".", segments));

            // 移除最后一个？ 不应该是清掉吗？
            segments.removeLast();
        }

        // 否则 说明 这一轮深度搜索的结果 匹配失败
    }


}
