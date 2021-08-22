package com.wsy.nowcoder.huawei.AL_27;


import java.util.*;

public class Main {

    // TODO: 2020-08-24 10:09:11
    // TODO: 2020-08-24 10:17:22  开始写
    // TODO: 2020-08-24 10:37:52 写完。

    // TODO: 2020-08-24 10:53:25 我曹，答案 怎么 size 多一个

    // TODO: 2020-08-24 11:00:27 md，果然 --》注意：字典中可能有重复单词 --》意思是 a 有一个兄弟单词 b，他出现了 2次，兄弟单词的次数 就得 算 2  --》不能 去重！
    // TODO: 2020-08-24 11:01:14 日，那还是看 别人的做法 把 --》Main2



    /**
     * 设计一个 字典结构，查找 兄弟单词的个数（同字符 顺序不一样的）、
     * 返回 兄弟单词的个数（不包含自身）
     * 返回 指定序号的 兄弟单词 --》字母排序， 短单词 比 长单词 优先 todo --》这里 长度得相同 才行，没有长单词
     * <p>
     * 注意，返回的都是 兄弟单词（不能 包含自身）
     * <p>
     * --》
     * 那么，理论上来说，可以 直接 将 新插入的单词
     * 1、判断 是否相同，相同的跳过；
     * 2、不相同的，都按 字母序列 排序，排序后 相同内容的，就是兄弟单词
     * 3、按排序后的字符串 当key --》存到 hashmap 中 --》value 是 那些单词的 list，按照 字母排序
     * <p>
     * 那么，所求结果：
     * 1、兄弟单词的个数，直接 排序 找 key，返回 list，剔除 和 自身 完全一样的就可以了
     * 2、指定序号的也是 一样，直接 index 定位，如果 和自身 相同，那就 下一个
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // 字典个数
            int count = scanner.nextInt();

            // 读出 所有单词 --> 建立 词典
            HashMap<String, List<String>> map = new HashMap<>();

            for (int i = 0; i < count; i++) {
                String word = scanner.next();
                addInMap(word, map);
            }

            // 读 目标单词
            String target = scanner.next();
            // 读 目标index --》todo 注意 -1 ！
            int index = scanner.nextInt() - 1;

            String key = getKey(target);
            List<String> list = map.get(key);

            // 没有 key
            if (list == null) {
                System.out.println(-1);
                System.out.println("null");
                return;
            }

            // 有 key 的话
            int size = list.size();

            // 包含 这个单词 的话
            if (list.contains(target)) {
                System.out.println(size - 1);

                // 恰好是 该单词，就 下一位
                String s = list.get(index);
                if (s.equals(target)) {
                    System.out.println(list.get(index + 1));
                } else {
                    System.out.println(list.get(index));
                }

            } else {
                System.out.println(size);

                System.out.println(list.get(index));
            }

        }


    }

    private static void addInMap(String word, HashMap<String, List<String>> map) {
        String key = getKey(word);

        List<String> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }

        // 这里，不用set的原因是，set 虽然能去重，但是是 无序的。。每次取出来，都要在排序。
        // 不包含的，才入队
        if (!list.contains(word)) {
            list.add(word);
        }

        // 排序
        Collections.sort(list);
    }

    private static String getKey(String word) {
        char[] a = word.toCharArray();
        Arrays.sort(a);
        StringBuilder sb = new StringBuilder(a.length);
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
        }
        return sb.toString();
    }


}
