package com.wsy.leetcode.math.AL_393_validUtf8;

public class Official {


    /**
     * https://leetcode-cn.com/problems/utf-8-validation/solution/utf-8-bian-ma-yan-zheng-by-leetcode/
     * <p>
     * 判断一组 int，是否 恰好是 符合utf-8要求的 n 个字符
     * <p>
     * 规则：
     * 1、1个字节的字符，只能是 0开头，后面不管
     * 2、> 1个字节的字符，比如 n 字节，字符的 第一个字节的前n为都是1，第n+1位为0
     * 且之后的几个字节，前两位 都是 10
     * 3、utf-8字符的n 是 1-4，所以 > 4 的 都不符合要求
     * 4、很大的int数（4字节表示），我们也只取 其中的1字节，且是 其 最低8位
     * 5、最后一个 多字节的字符，要完整才行
     * <p>
     * 思路一致：
     * <p>
     * 方法1：字符串数组 方法
     * 1、数组中的 每个整数 都转换成 二进制的字符串
     * 然后，超出8位的，只截取最后8位；不足8位的，前面补0
     * 2、用一个int值，记录 当前字符的 剩余未处理的字节个数 numberOfBytesToProcess
     * 3、其=0，表示是一个新字符的开始；
     * 4、否则，是新字符的后续字节；
     * 5、全部遍历完后，判断 numberOfBytesToProcess = 0；
     * 如果不等于 0 ，说明最后一个完整字符 没处理完，这也就不是一个符合条件的 utf-8字符编码数组
     * <p>
     * <p>
     * 时间：O(n) ；遍历 数组的所有整数；
     * 空间：O(n) ；数组中的 每个整数 都要转换成 一个二进制的字符串。。
     */
    public boolean validUtf8(int[] data) {
        if (data == null) {
            return false;
        }

        // 某字符 还有几个字节 要处理
        int leftBytesNumberInOneChar = 0;

        // 依次 处理 每个整数（当做一个字节）
        for (int i = 0; i < data.length; i++) {

            // 数组的 每个整数，都先转换成 二进制字符串
            String s = Integer.toBinaryString(data[i]);
            // 长度可能过长，我们只取 最后8位;
            // 不足8位，就前面 补0
            s = s.length() >= 8 ?
                    s.substring(s.length() - 8)
                    : "00000000".substring(s.length() % 8) + s;

            if (leftBytesNumberInOneChar == 0) {

                // 当前的int 是 一个新字符开端，看下 第一个 0之前 有几个1
                for (int j = 0; j < s.length(); j++) {
                    // 到 0 就 break
                    if (s.charAt(j) == '0') {
                        break;
                    }

                    // 否则 字符 就是1, 1的个数 +1
                    leftBytesNumberInOneChar++;
                }

                // 如果遍历到最后，一个1都没有，那就是 符合1字节的 字符了，跳过，换下一个字符
                if (leftBytesNumberInOneChar == 0) {
                    continue;
                }

                // 否则，看下 直接返回false的条件
                // 1、字节个数 超过4了
                // 2、说这个字符的字节个数为1，即 10开头，但是实际上 1字节的，只能是 0开头 ！
                if (leftBytesNumberInOneChar > 4 || leftBytesNumberInOneChar == 1) {
                    return false;
                }

                // 否则，才是 规则允许的字符

            } else {

                // 说明 这是 一个字符的 非第一个字节，要满足 前两位是1、0
                // 否则，不符合 要求
                // TODO: 2020/6/28 我曹我曹！这是 字符 '0' ，不是 数字 0！
                if (!(s.charAt(0) == '1' && s.charAt(1) == '0')) {
                    return false;
                }

            }

            // 无论，当前整数 是 某个字符的 第几个字节，这里 都处理完了一个字节
            leftBytesNumberInOneChar--;
        }


        // 判断，是不是 所有 合法字符的 剩余字节 都处理完毕，如果不为0，说明 某个字符不完整，那也是 false！
        return leftBytesNumberInOneChar == 0;
    }


    /**
     * 方法2：
     * 上述的 优化版本：
     * <p>
     * 上面 每个整数，都先转换成 字符串，在截取成 低8位
     * 然后拿每一位的字符 判断 0、1
     * <p>
     * 这里直接 用 掩码 做 位运算，判断 当前位 是 0 还是 1，且只判断 低8位就好；
     * 就省去了 n个字符串的空间，而且不需要 拼接、字符串比较等
     * 且 位运算 比较快
     * <p>
     * 判断 低 8位的 第一位，掩码 就是 1<<7;
     * 判断 低 8位的 第二位，那 就是 1<<6
     * 以此类推。
     * <p>
     * 时间： O(n) ；遍历所有的数字
     * 空间： O(1) ；只有 几个 掩码
     */

    public boolean validUtf82(int[] data) {
        if (data == null) {
            return false;
        }

        // 某字符 还有几个字节 要处理
        int leftBytesNumberInOneChar = 0;

        // 俩掩码，分别用来判断 最后8位的 第一位、第一位
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        // 遍历所有整数，当做一个字节
        for (int i = 0; i < data.length; i++) {

            if (leftBytesNumberInOneChar == 0) {
                // 该整数 是 新字符的第一个字节

                // 从 最后8位的最高位 开始，依次 右移1位
                int tempMask = 1 << 7;

                while ((data[i] & tempMask) != 0) {
                    // 只要和当前掩码的 与运算 不为0，就说明 这一位 是1；
                    leftBytesNumberInOneChar++;

                    // 掩码右移一位，和 下一位 比较
                    tempMask = tempMask >> 1;
                }

                // todo 前面主要是计算 新字符的 第一个字节里面 前面 有几个1，好确定 他有几个字节 组成；
                // 后面 判断处理 和上面的方案 一致；
                if (leftBytesNumberInOneChar == 0) {
                    continue;
                }

                if (leftBytesNumberInOneChar > 4 || leftBytesNumberInOneChar == 1) {
                    return false;
                }

                // 符合要求的新字符

            } else {

                // 否则，就是 新字符的 非第一个字节，判断 1 0
                if (!((data[i] & mask1) != 0 && (data[i] & mask2) == 0)) {
                    return false;
                }
            }

            // 处理完 某字符的一个字节了
            leftBytesNumberInOneChar--;
        }

        return leftBytesNumberInOneChar == 0;

    }


}
