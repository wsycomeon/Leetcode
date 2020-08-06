package com.wsy.leetcode.datastructure.AL_432_AllOne;

import java.util.*;

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
public class AllOne {


    HashMap<String, Integer> keyMap;
    HashMap<Integer, HashSet<String>> valueMap;
    int minValue;
    int maxValue;

    /**
     * 我曹。。。md
     * 勉强算是通过了，但是 这效率。。
     * 执行用时：
     * 1127 ms
     * , 在所有 Java 提交中击败了
     * 5.38%
     * 的用户
     * 内存消耗：
     * 49.8 MB
     * , 在所有 Java 提交中击败了
     * 33.33%
     * 的用户
     */
    public AllOne() {
        keyMap = new HashMap<>();
        valueMap = new HashMap<>();

        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
    }

    public void inc(String key) {
        Integer oldValue = keyMap.get(key);

        if (oldValue == null) {
            // 之前未有
            keyMap.put(key, 1);

            // valueMap 也要更新
            HashSet<String> set = valueMap.get(1);
            if (set == null) {
                set = new HashSet<>();
                valueMap.put(1, set);
            }
            set.add(key);

            // 最大、最小value
            minValue = Math.min(minValue, 1);
            maxValue = Math.max(maxValue, 1);
            return;
        }

        // 之前有的话
        int newValue = oldValue + 1;
        keyMap.put(key, newValue);

        valueMap.get(oldValue).remove(key);

        HashSet<String> newSet = valueMap.get(newValue);
        if (newSet == null) {
            newSet = new HashSet<>();
            valueMap.put(newValue, newSet);
        }
        newSet.add(key);

        // 因为值变大，可能导致 突破上限，还有可能导致 下限消失
        maxValue = Math.max(maxValue, newValue);

        if (minValue == oldValue && valueMap.get(minValue).isEmpty()) {
            minValue = newValue;
        }

    }

    public void dec(String key) {
        Integer oldValue = keyMap.get(key);
        // 没有，直接返回
        if (oldValue == null) {
            return;
        }


        // 有的话
        if (oldValue == 1) {
            // 恰好为1的话，要删掉了
            keyMap.remove(key);
            valueMap.get(1).remove(key);

            if (minValue == 1 && valueMap.get(minValue).isEmpty()) {
                // 这里，要换成 稍大的那个啊
                minValue = getMin();
            }

            if (maxValue == 1 && valueMap.get(maxValue).isEmpty()) {
                maxValue = Integer.MIN_VALUE;
            }

            return;
        }

        // 不为1的话，至少是2，要 -1
        int newValue = oldValue - 1;
        keyMap.put(key, newValue);

        valueMap.get(oldValue).remove(key);
        HashSet<String> set = valueMap.get(newValue);
        if (set == null) {
            set = new HashSet<>();
            valueMap.put(newValue, set);
        }
        set.add(key);

        minValue = Math.min(minValue, newValue);

        if (maxValue == oldValue && valueMap.get(maxValue).isEmpty()) {
            maxValue = newValue;
        }

    }

    /**
     * 获取 valueMap value 不为 null的 第一个key
     */
    private int getMin() {
        Iterator<Map.Entry<Integer, HashSet<String>>> iterator = valueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, HashSet<String>> entry = iterator.next();
            if (!entry.getValue().isEmpty()) {
                return entry.getKey();
            }
        }

        return Integer.MAX_VALUE;
    }

    public String getMaxKey() {
        HashSet<String> keys = valueMap.get(maxValue);
        return keys == null ? "" : keys.iterator().next();
    }

    public String getMinKey() {
        HashSet<String> keys = valueMap.get(minValue);
        return keys == null ? "" : keys.iterator().next();
    }


}
