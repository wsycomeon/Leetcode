package com.wsy.leetcode.ProducerAndCreator;

public class Product {

    int i = 0;

    synchronized public void consume() {
//        System.out.println(Thread.currentThread().getName() + " , when consume i = " + i);

        while (i != 1) {
            try {
//                System.out.println(Thread.currentThread().getName() + " , consume wait");
                wait();
            } catch (InterruptedException e) {
//                System.out.println("消费者 被中断");
            }
        }


//        System.out.println(Thread.currentThread().getName() + " , consume --");
        i--;
        notifyAll();
    }


    synchronized public void produce() {
//        System.out.println(Thread.currentThread().getName() + " , when produce i = " + i);

        while (i != 0) {
            try {
//                System.out.println(Thread.currentThread().getName() + " , produce wait");
                wait();
            } catch (InterruptedException e) {
//                System.out.println("生产者 被中断");
            }
        }

//        System.out.println(Thread.currentThread().getName() + " , produce ++");
        i++;
//            notify();
        notifyAll(); // todo 多个生产者-消费者的话，最好 notifyall，否则 有概率 唤醒的还是 生产者，下一个生产者 在wait，没人 notify 消费者 就卡死了

    }

}
