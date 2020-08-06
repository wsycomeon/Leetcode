package com.wsy.leetcode.arrayAndMatrixAndSort.AL_697_findShortestSubArray;

import java.util.*;

public class Official {

    // TODO: 2020-08-06 10:29:57  开始
    // TODO: 2020-08-06 10:34:06 有思路了
    // TODO: 2020-08-06 10:44:10 看完 官方题解 ，和我的 思路一致
    // TODO: 2020-08-06 11:03:47 自己的代码 写完了
    // TODO: 2020-08-06 11:24:54 提交验证

    /**
     * 求和原数组的度 相同的，原数组的 最小 连续子数组 的 长度！
     * 所谓 数组的度，即 数组元素 出现次数 最多 的那个count值
     * <p>
     * <p>
     * 自己的思路：
     * 1、遍历一边数组，自定义bean 封装 某个数值 出现的次数，和 所在的 index 数组，用map存 value+bean
     * 2、遍历map，求count最大的那些 bean
     * 3、遍历这些bean，比较 这些bean的 子数组（出现的 index 计算 max-min+1）的长度大小，求最小值 即可
     * <p>
     * todo em ---》看了下 官方的，思路基本一致，就是实现不同，我是封装了一个bean，更容易理解（当然了，我对于一个数字 记录了 它出现的所有 index，其实我自己 也只是用到了 first 和 last，稍嫌 浪费空间）
     * <p>
     * 时间：O(n) 遍历了一遍 原数组
     * 空间：O(n) 用了一个 hashMap
     */
    public int findShortestSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1、记录bean信息
        HashMap<Integer, Bean> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Bean bean = map.get(num);
            // 不存在的话，创建一个
            if (bean == null) {
                bean = new Bean();
                bean.val = num;
            }
            bean.count += 1;
            bean.indexes.add(i);
            // 更新进去
            map.put(num, bean);
        }

        // 2、求出 count 最大的 bean数组，或者说 todo 求出 bean数组中的 最大count值
        // TODO: 2020-08-06 11:27:23 我曹，leetcode 编译器 竟然 不认识  Collection 工具类 ！

        Collection<Bean> values = map.values();
        Iterator<Bean> iterator = values.iterator();
        int maxCount = 0;
        while (iterator.hasNext()) {
            Bean bean = iterator.next();
            maxCount = Math.max(maxCount, bean.count);
        }

        // 3、遍历bean数组，对于 是 maxCount的bean数组，求 最小长度
        // TODO: 2020-08-06 11:05:51 注意，这里的初始值，得是 最大的！
        int minLength = nums.length;

        Iterator<Bean> iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Bean bean = iterator1.next();
            if (bean.count != maxCount) {
                continue;
            }

            // 是 最大 count 的 对应数值，比较 最小长度
            List<Integer> indexes = bean.indexes;
            int length = indexes.get(indexes.size() - 1) - indexes.get(0) + 1;
            minLength = Math.min(minLength, length);
        }

        return minLength;
    }

    static class Bean {
        // 对应数字
        int val;
        // 出现次数
        int count;
        // 出现的index数组 --》todo 其实这里 拆成 firstIndex = -1 和 lastIndex 两个变量 就够了 ！
        List<Integer> indexes = new ArrayList<>();
    }

    /**
     * 方法2：
     * 官方的：其实就是 代码实现的 稍稍不同
     * 我上面那样写，好像有点 浪费空间 --》todo 草，leetcode 不认识 Collection 工具类。。却认识 Collections ！
     * --》
     * 官方这里，就是用 3个 hashmap 分别保存了： 一个数值num 出现的次数 count，第一次出现的index，最后一次出现的index
     * --》而 我上面封装成了bean，indexes 用了一个list，其实 也可以简化成 俩遍历 firstIndex （初始值 -1，判断为-1，才更新一次），lastIndex（一直更新） 就和 官方 完全一样了！
     * <p>
     * 和我上面的 操作步骤 完全一样，只是 数据结构 存储上 有少许区别
     * 时间：O(n) 遍历一遍 原数组
     * 空间：O(n) 3个hashMap
     */
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap<Integer, Integer> firstIndex = new HashMap<>();
        HashMap<Integer, Integer> lastIndex = new HashMap<>();

        // 1、遍历一遍数组，填充 上面信息
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            count.put(num, count.getOrDefault(num, 0) + 1);
            lastIndex.put(num, i);

            // 只记录 第一次出现的index
            if (firstIndex.get(num) == null) {
                firstIndex.put(num, i);
            }
        }

        // 2、求 count 最大值 --》用 官方 api
        int maxCount = Collections.max(count.values());

        // 3、求 遍历 所有符合条件的 nums 求最小长度
        int minLength = nums.length;
        for (int num : nums) {
            if (count.get(num) != maxCount) {
                continue;
            }

            int length = lastIndex.get(num) - firstIndex.get(num) + 1;
            minLength = Math.min(minLength, length);
        }

        return minLength;
    }


}
