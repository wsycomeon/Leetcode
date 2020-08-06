package com.wsy.leetcode.string.AL_40_restoreIpAddresses;

import java.util.ArrayList;
import java.util.List;

public class AL_40 {


    static String chars = "25525511135";

    public static void main(String[] args) {

        List<String> list = new Official().restoreIpAddresses(chars);

        for (String i : list) {
            System.out.println(i);
        }
    }

    /**
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();


        return list;
    }


}
