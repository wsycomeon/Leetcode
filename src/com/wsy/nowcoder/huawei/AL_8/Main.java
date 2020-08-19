package com.wsy.nowcoder.huawei.AL_8;

import java.util.*;

public class Main {

    /**
     * 题目描述
     * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
     *
     * 输入描述:
     * 先输入键值对的个数
     * 然后输入成对的index和value值，以空格隔开
     *
     * 输出描述:
     * 输出合并后的键值对（多行）
     *
     * 示例1
     * 输入
     * 复制
     * 4
     * 0 1
     * 0 2
     * 1 2
     * 3 4
     * 输出
     * 复制
     * 0 3
     * 1 2
     * 3 4
     */

    /**
     * 先输入 多少对
     * 再输入 每一对的key-value，空格 隔开
     * <p>
     * key 相同的，value累加
     * 最后，key 从小到大 输出，所有的 key value ，空格隔开
     * <p>
     * --》hashMap 能做
     * todo 但是 TreeMap 的 特性 更适合 --> 天然支持 按照 key 的特性关系 排序！
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            int count = Integer.parseInt(scanner.nextLine());

            TreeMap<Integer, Integer> map = new TreeMap();

            for (int i = 0; i < count; i++) {
                String s = scanner.nextLine().trim();
                // 用空格 拆分成 key-value
                String[] a = s.split(" ");
                int key = Integer.parseInt(a[0]);
                int value = Integer.parseInt(a[1]);

                map.put(key, map.getOrDefault(key, 0) + value);
            }

            // 根据 TreeMap的特性，遍历出来 就是 有序的 --》 打印 即可
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        }

    }

    // todo 方法2： hashmap 的方法，太矬了！
    private static void hashMapFun() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int count = Integer.parseInt(scanner.nextLine());
            HashMap<Integer, Integer> map = new HashMap(count);

            for (int i = 0; i < count; i++) {
                String s = scanner.nextLine().trim();
                // 用空格 拆分成 key-value
                String[] a = s.split(" ");
                int key = Integer.parseInt(a[0]);
                int value = Integer.parseInt(a[1]);

                map.put(key, map.getOrDefault(key, 0) + value);
            }

//            getResult(map);
            getResult2(map);
        }
    }

    private static void getResult2(HashMap<Integer, Integer> hashmap) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : hashmap.keySet()) {
            list.add(i);
        }

        Collections.sort(list);

        // 按照升序的键，进行打印
        for (int i : list) {
            System.out.println(i + " " + hashmap.get(i));
        }

    }

    // 按key从小到大 打印 key-value
    private static void getResult(HashMap<Integer, Integer> map) {

        // TODO: 2020-08-19 13:39:26 我擦， set 的 toArray() 慎用 --》越界？--》应该用 list 读，然后 Collections.sort(result) ！！！
        Integer[] array = (Integer[]) map.keySet().toArray();

        // 排序
        Arrays.sort(array);


        // 依次 读出来
        for (Integer key : array) {
            System.out.println(key + " " + map.get(key));
        }

    }

}
