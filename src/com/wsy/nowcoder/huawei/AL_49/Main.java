package com.wsy.nowcoder.huawei.AL_49;

import java.util.Scanner;

public class Main {

    // TODO: 2020-08-24 15:16:27 4个线程 依次 向 数组输入 A B C D，  输定 指定次数 个


    /**
     * 控制 线程顺序，不是 join 这个完成顺序
     * 那就得用 锁了
     * <p>
     * --》ABCD  4个线程，分别 以自身为锁，但是 被前一个线程 持有，
     * 一般被 notify后，先打印，然后 唤醒 自己 持有的线程
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int maxCount = scanner.nextInt();
            print(maxCount);
        }

    }

    private static void print(int maxCount) {
        MyThread runnable1 = new MyThread();
        runnable1.setChars("A");
        runnable1.setMaxCount(maxCount - 1);

        MyThread runnable2 = new MyThread();
        runnable1.setChars("B");
        runnable1.setMaxCount(maxCount);

        MyThread runnable3 = new MyThread();
        runnable1.setChars("C");
        runnable1.setMaxCount(maxCount);

        MyThread runnable4 = new MyThread();
        runnable1.setChars("D");
        runnable1.setMaxCount(maxCount);

        runnable1.setNext(runnable2);
        runnable2.setNext(runnable3);
        runnable3.setNext(runnable4);
        runnable4.setNext(runnable1);

        runnable1.start();
        runnable2.start();
        runnable3.start();
        runnable4.start();

        runnable1.printAndNotify();
    }

    static class MyThread extends Thread {

        String chars;

        Object next;

        int maxCount;


        public void setChars(String chars) {
            this.chars = chars;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public void setNext(Object next) {
            this.next = next;
        }

        @Override
        public void run() {
            count = 0;
            print();
        }

        int count;

        private void print() {

            while (true) {

                if (count >= maxCount) {
                    break;
                }

                // 先阻塞
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                printAndNotify();
            }

        }

        public void printAndNotify() {
            // 被唤醒后，打印 自己的 字符
            count++;
            System.out.print(chars);

            // 然后 唤醒 下一个线程
            next.notify();
        }

    }


}
