package com.wsy.leetcode.string.AL_43_multiply;

public class Official {


    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        // todo 有空串 不处理
        if (m == 0 || n == 0) {
            return "";
        }

        // 结果 最多为 m + n 位数，所以申请一个这么大的数组
        int[] numbers = new int[m + n];

        // num1 从个位数 取每个字符 ，开始逐位和 num2的相乘
        for (int i = m - 1; i >= 0; i--) {
            // 和 num2的 每一位 相乘
            for (int j = n - 1; j >= 0; j--) {
                // 字符 和 '0' 的差值，就是 该字符的大小
                // 两个 个位数，相乘 最多是 两位数
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                // 乘积在 numbers数组 对应的 索引位置，是有规律的
                int high = i + j; // 高位
                int low = i + j + 1; // 低位

                // todo 将结果 叠加到 res 上，先加到 原来的低位上，可能会进位的！
                int sum = numbers[low] + mul;

                // 低位 直接 赋值
                numbers[low] = sum % 10;

                // todo 高位 刷新（低位 可能进位，所以这里 要加起来们 ---》即使 这里发生进位 也不用怕，下一轮 他又是低位，在进位也不迟的）
                numbers[high] += sum / 10;
            }
        }

        // 因为结果的前缀 可能有 0（未使用的位），所以 找到真正的起始位
        int start = 0;
        while (start < numbers.length && numbers[start] == 0)
            start++;

        // 将计算结果 转化成 字符串
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < numbers.length; i++) {
            sb.append(numbers[i]);
        }


        // TODO: 2020/6/20 边界条件！如果 最后结果就是 0 , sb此时是空了

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
