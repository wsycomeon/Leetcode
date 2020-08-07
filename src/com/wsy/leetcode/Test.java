package com.wsy.leetcode;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {


//      test1();

//        test2();

//        test3();

        test4();



    }

    private static void test4() {

        // TODO: 优先级队列 不仅 可以实现 小顶堆
        //  todo 还可以 通过 自定义 Comparator 修改 比较规则（优先 覆盖掉 实现了 Comparable 的类的规则 --> 源码 可见），改成 大顶堆 ！
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o2 - o1;
            }
        });

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }

    }

    private static void test3() {

        SingleObject instance = SingleObject.getInstance();

        System.out.println("SingleObject = " + instance);

//        String path = "../../test.txt";
        String path = "test.txt";
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(instance);
            System.out.println("============== write done");


            ois = new ObjectInputStream(new FileInputStream(path));
            Object o = ois.readObject();

            System.out.println("read object equals = " + (instance == o));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        System.out.println("end");
    }

    private static void test2() {

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (count >= 100) {
                        break;
                    }
                    print();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (count >= 100) {
                        break;
                    }
                    print();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (count >= 100) {
                        break;
                    }
                    print();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (count >= 100) {
                        break;
                    }
                    print();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (count >= 100) {
                        break;
                    }
                    print();
                }
            }
        }.start();


    }

    static int count = 0;
    static Object lock = new Object();

    private static void print() {

        // 0、代码块 加锁
        synchronized (lock) {
            // 1、拿到锁后，就唤醒 等待者
            lock.notify();

            // 2、执行操作
            count++;
            System.out.println(Thread.currentThread().getName() + " -- value = " + count);

            // 3、wait 释放锁，等待唤醒
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void test1() {
        String a1 = "a" + "b" + "c";
        String a2 = "abc";
        String a3 = new String("abc");
        String a4 = new String(a2);

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);

        System.out.println(a1 == a2);
        System.out.println(a2 == a3);
        System.out.println(a2 == a4);


        System.out.println("---------");

        a2 = a2.substring(0, a2.length() - 1);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);

        System.out.println(a1 == a2);
        System.out.println(a2 == a3);
        System.out.println(a2 == a4);
    }
}
