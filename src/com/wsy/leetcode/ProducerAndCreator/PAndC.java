package com.wsy.leetcode.ProducerAndCreator;

public class PAndC {


    /**
     * 生产者 - 消费者 模型
     */
    public static void main(String[] args) {

        test1();


//        test2();
    }

    private static void test2() {
        Product2 product2 = new Product2(new Object());

        new Thread(new Runnable() {
            @Override
            public void run() {
                product2.produce();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                product2.consume();
            }
        }).start();

    }

    private static void test1() {
        Product product = new Product();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    product.produce();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    product.consume();
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    product.produce();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    product.consume();
                }
            }
        }).start();
    }


}
