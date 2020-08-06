package com.wsy.leetcode;

import java.io.Serializable;

public class SingleObject implements Serializable {

    /**
     * 静态内部类
     */
    static class Inner {
        private static SingleObject Instance = new SingleObject();
    }

    private SingleObject() {

    }

    public static SingleObject getInstance() {
        return Inner.Instance;
    }


    /**
     * 防止 序列化 生成 其他实例，破坏 单例
     * todo 注意，返回值 是 Object 类型！
     */
    public Object readResolve() {
        System.out.println("method readResolve called !");
        return Inner.Instance;
    }


}
