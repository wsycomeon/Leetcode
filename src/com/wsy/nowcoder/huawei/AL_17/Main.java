package com.wsy.nowcoder.huawei.AL_17;


import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
     *
     * 输入：
     *
     * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
     *
     * 坐标之间以;分隔。
     *
     * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
     *
     * 下面是一个简单的例子 如：
     *
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     *
     * 处理过程：
     *
     * 起点（0,0）
     *
     * +   A10   =  （-10,0）
     *
     * +   S20   =  (-10,-20)
     *
     * +   W10  =  (-10,-10)
     *
     * +   D30  =  (20,-10)
     *
     * +   x    =  无效
     *
     * +   A1A   =  无效
     *
     * +   B10A11   =  无效
     *
     * +  一个空 不影响
     *
     * +   A10  =  (10,-10)
     *
     * 结果 （10， -10）
     *
     * 注意请处理多组输入输出
     *
     * 输入描述:
     * 一行字符串
     *
     * 输出描述:
     * 最终坐标，以,分隔
     *
     * 示例1
     * 输入
     * 复制
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     * 输出
     * 复制
     * 10,-10
     */


    /**
     * 初始坐标值 (0,0)
     * 然后 按照 一堆 移动指令 移动，输出最后结果，指令之间 以 ; 分割
     * 移动指令 必然是 W/A/S/D + 数值，无效的直接跳过
     * <p>
     * 输入 是一行字符串
     * 输出 俩坐标值 x , y
     * <p>
     * todo 曹尼玛。。。这垃圾的网页的输入 有问题，都说了 只有一行，
     * 你只要 不 while 循环 读取 所有行 --》print 就是 打印不出来 数据，曹尼玛 ！
     * <p>
     * <p>
     * ----
     * 或者
     * 1、正则表达式 匹配 是否是 合法的指令  [ADWS]\\d{1}\\d?
     * 2、将各指令对应的数值各自相加，存到一个容量为4的数组里面
     * 3、最后 将 x，y 分别 计算（加、减） 数组中的四个数值 即可
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String[] a = scanner.nextLine().trim().split(";");
            dealOneString(a);
        }

    }

    private static void dealOneString(String[] a) {
        // 初始坐标值
        int x = 0;
        int y = 0;

        for (int i = 0; i < a.length; i++) {

            if (!isValid(a[i])) {
                continue;
            }

//            // 正则匹配
//            String res = "[ADWS]\\d{1}\\d?";
//            if (!a[i].matches(res)) {
//                continue;
//            }

            char c = a[i].charAt(0);
            int num = Integer.parseInt(a[i].substring(1));

            switch (c) {
                case 'W':
                    y += num;
                    break;
                case 'A':
                    x -= num;
                    break;
                case 'S':
                    y -= num;
                    break;
                case 'D':
                    x += num;
                    break;
            }

        }

        System.out.println(x + "," + y);
    }

    private static boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        char c = s.charAt(0);
        if (c != 'W' && c != 'A' && c != 'S' && c != 'D') {
            return false;
        }

        String num = s.substring(1);
        if (num.contains("W") || num.contains("A") || num.contains("S") || num.contains("D")) {
            return false;
        }

        return true;
    }

}
