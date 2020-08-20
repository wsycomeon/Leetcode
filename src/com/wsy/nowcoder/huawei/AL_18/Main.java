package com.wsy.nowcoder.huawei.AL_18;

import java.util.Scanner;

public class Main {

    /**
     * 题目描述
     * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
     *
     * 所有的IP地址划分为 A,B,C,D,E五类
     *
     * A类地址1.0.0.0~126.255.255.255;
     *
     * B类地址128.0.0.0~191.255.255.255;
     *
     * C类地址192.0.0.0~223.255.255.255;
     *
     * D类地址224.0.0.0~239.255.255.255；
     *
     * E类地址240.0.0.0~255.255.255.255
     *
     *
     * 私网IP范围是：
     *
     * 10.0.0.0～10.255.255.255
     *
     * 172.16.0.0～172.31.255.255
     *
     * 192.168.0.0～192.168.255.255
     *
     *
     * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
     * 注意二进制下全是1或者全是0均为非法
     *
     * 注意：
     * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
     * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
     *
     * 输入描述:
     * 多行字符串。每行一个IP地址和掩码，用~隔开。
     *
     * 输出描述:
     * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
     *
     * 示例1
     * 输入
     * 复制
     * 10.70.44.68~255.254.255.0
     * 1.0.0.1~255.0.0.0
     * 192.168.0.2~255.255.255.0
     * 19..0.~255.255.255.0
     * 输出
     * 复制
     * 1 0 1 0 0 2 1
     */

    /**
     * 根据要求，统计 7个数值（A B C D E 错误ip或者掩码 私有ip）个数
     * 输入是：多行
     * 每行，~ 分割 的 ip地址 和 掩码
     * 判断 范围 和 对错
     * <p>
     * todo 坑爹题目！
     * 不难，但是 好几个点 需要 注意 ！
     */
    public static void main(String[] args) {
        // 各类个数
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int error = 0;
        int privates = 0;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // TODO: 2020-08-20 14:13:00 这里，不控制 主动 跳出，循环 会一直 等待，出不去！
            if (input.equals("")) {
                break;
            }

            // 取出一行
            String[] arr = input.split("~");
            String ip = arr[0];
            String maskCode = arr[1];

            // 判断逻辑 都 写这里吧，防止 多次 split

            // 1、长度 就不合法
            if (ip == null || ip.length() < 7 || maskCode == null || maskCode.length() < 7) {
                error++;
                continue;
            }

            // 2、先判断 掩码，掩码 不合法，就是 error
            // TODO: 2020-08-20 14:15:25 草！注意 split中 . 的使用 --》 要 转译！
            String[] codeSplit = maskCode.split("\\.");

            if (codeSplit.length != 4) {
                error++;
                continue;
            }

            boolean hasNull = false;
            for (int i = 0; i < codeSplit.length; i++) {
                if (codeSplit[i].equals("")) {
                    hasNull = true;
                    break;
                }
            }
            if (hasNull) {
                error++;
                continue;
            }


            int code1 = Integer.parseInt(codeSplit[0]);
            int code2 = Integer.parseInt(codeSplit[1]);
            int code3 = Integer.parseInt(codeSplit[2]);
            int code4 = Integer.parseInt(codeSplit[3]);
            if (code1 < 0 || code1 > 255
                    || code2 < 0 || code2 > 255
                    || code3 < 0 || code3 > 255
                    || code4 < 0 || code4 > 255) {
                error++;
                continue;
            }

            // TODO: 2020-08-20 14:00:08 我擦！每个数字 都要 满8位的 --》否则 判断会出错的！
            String s = new StringBuilder(getBinaryString(code1))
                    .append(getBinaryString(code2))
                    .append(getBinaryString(code3))
                    .append(getBinaryString(code4))
                    .toString();

            // TODO: 2020-08-20 14:16:09 注意，正则 匹配！
            if (!s.matches("(1)+(0)+")) {
                error++;
                continue;
            }

            // 3、判断 各ip地址

            String[] ipSplit = ip.split("\\.");
            if (ipSplit.length != 4) {
                error++;
                continue;
            }


            for (int i = 0; i < ipSplit.length; i++) {
                if (ipSplit[i].equals("")) {
                    hasNull = true;
                    break;
                }
            }
            if (hasNull) {
                error++;
                continue;
            }


            int ip1 = Integer.parseInt(ipSplit[0]);
            int ip2 = Integer.parseInt(ipSplit[1]);
            int ip3 = Integer.parseInt(ipSplit[2]);
            int ip4 = Integer.parseInt(ipSplit[3]);

            // 错误ip
            if (ip1 < 0 || ip1 > 255
                    || ip2 < 0 || ip2 > 255
                    || ip3 < 0 || ip3 > 255
                    || ip4 < 0 || ip4 > 255) {
                error++;
                continue;
            }

            // 特殊 ip
            if (ip1 == 0 || ip1 == 127) {
                continue;
            }

            // 合法 统计
            if (ip1 <= 126) {
                a++;

                if (ip1 == 10) {
                    privates++;
                }

            } else if (ip1 <= 191) {
                b++;

                if (ip1 == 172 && ip2 >= 16 && ip2 <= 31) {
                    privates++;
                }


            } else if (ip1 <= 223) {
                c++;

                if (ip1 == 192 && ip2 == 168) {
                    privates++;
                }

            } else if (ip1 <= 239) {
                d++;
            } else {
                e++;
            }


        }

        scanner.close();

        String s = new StringBuilder()
                .append(a).append(" ")
                .append(b).append(" ")
                .append(c).append(" ")
                .append(d).append(" ")
                .append(e).append(" ")
                .append(error).append(" ")
                .append(privates).toString();

        System.out.println(s);
    }

    private static String getBinaryString(int code) {
        String s1 = Integer.toBinaryString(code);
        int count = 8 - s1.length();

        for (int i = 0; i < count; i++) {
            s1 = "0" + s1;
        }

        return s1;
    }


}
