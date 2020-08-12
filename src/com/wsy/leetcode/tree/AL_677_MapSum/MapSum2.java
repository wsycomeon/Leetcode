package com.wsy.leetcode.tree.AL_677_MapSum;

import java.util.HashMap;

public class MapSum2 {

    HashMap<String, Integer> map;

    public MapSum2() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int sum = 0;

        for (String s : map.keySet()) {
            if (s.startsWith(prefix)) {
                sum += map.get(s);
            }
        }

        return sum;
    }

}
