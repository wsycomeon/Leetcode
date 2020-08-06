package com.wsy.leetcode.ProducerAndCreator;

public class Product2 {

    int i = 0;

    final Object lock;


    public Product2(Object lock) {

        this.lock = lock;

    }


    public void consume() {

        while (true) {

            synchronized (lock) {
                System.out.println("when consume i = " + i);

                if (i != 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    i--;
                    lock.notify();
                }

            }


        }
    }


    public void produce() {

        while (true) {
            synchronized (lock) {
                System.out.println("when produce i = " + i);

                if (i != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {

                    i++;
                    lock.notify();
                }
            }


        }


    }

}
