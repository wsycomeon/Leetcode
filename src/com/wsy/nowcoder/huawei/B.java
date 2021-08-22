package com.wsy.nowcoder.huawei;

public class B extends A {

    public B() {
//        super();
        System.out.println("Hello B");
    }

    {
        System.out.println("I'm B class");
    }

    static {
        System.out.println("static B");
    }


    public static void main(String[] args) {

        new B();
    }

}

class A {

    public A() {
        System.out.println("Hello A");
    }

    {
        System.out.println("I'm A class");
    }

    static {
        System.out.println("static A");
    }

}
