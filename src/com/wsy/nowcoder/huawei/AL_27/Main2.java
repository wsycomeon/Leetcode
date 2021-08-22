package com.wsy.nowcoder.huawei.AL_27;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {

    // TODO: 2020-08-24 11:07:17  --> 这个题目，压根 不要啥 数据结构，就用一遍 拉倒的 ！
    // 直接存起来 --》 后面 遍历比较 是否 符合要求 即可！

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            // 输入字典中单词的个数
            int N = sc.nextInt();

            // TODO: 2020-08-24 11:02:06 先把 字符串 都存起来
            // 输入n个单词作为字典单词
            String[] str = new String[N];
            for (int i = 0; i < N; i++) {
                str[i] = sc.next();
            }

            // 查找兄弟单词
            // 输入一个待查单词
            String target = sc.next();
            // 输入待查单词的 指定序号
            int index = sc.nextInt() - 1;

            // list 存 所有的 兄弟单词
            ArrayList<String> list = new ArrayList<>();
            // todo 遍历 所有的 字符串 --》找 兄弟单词
            for (int i = 0; i < N; i++) {

                // 长度相等 且 字符串 不相等
                if ((str[i].length() == target.length()) && (!str[i].equals(target))) {
                    // 字符 得是 对应的上的
                    if (checkBorther(target, str[i])) {
                        list.add(str[i]);
                    }
                }
            }

            // 输出
            System.out.println(list.size());

            Collections.sort(list);

            if (index < list.size()) {
                System.out.println(list.get(index));
            }

        }

    }

    // 比较 全是 小写字母的 字符串 是不是 异构的
    public static boolean checkBorther(String str1, String str2) {
        int[] arr = new int[26];

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        for (int i = 0; i < ch1.length; i++) {
            // 一个找到index后，count--
            arr[ch1[i] - 'a']++;
            // 一个 count++
            arr[ch2[i] - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            // 不为0 ，说明 不一样！
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

}
