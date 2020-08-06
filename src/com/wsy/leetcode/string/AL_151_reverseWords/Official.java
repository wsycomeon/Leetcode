package com.wsy.leetcode.string.AL_151_reverseWords;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Official {


    /**
     * 1、最简单的方法。直接 使用 java自带工具api
     * <p>
     * https://blog.csdn.net/high2011/article/details/53467220
     * 正则表达式中 \s 匹配任何 空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
     * 而“\s+”则表示匹配 任意多个 上面的字符。另因为反斜杠在Java里是转义字符，所以在Java里，我们要这么用“\\s+”.
     */
    public String reverseWords(String s) {
        // 除去开头和末尾的 空白字符
        s = s.trim();
        // 正则匹配 连续的空白字符 作为 分隔符 分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));

        // 翻转 单词列表
        Collections.reverse(wordList);

        // 元素之间 添加 空格 拼接起来
        return String.join(" ", wordList);
    }

    /**
     * todo 2、自己 实现 trim、翻转整体、翻转局部（每个单词）
     */
    public String reverseWords2(String s) {
        // 去除空格，单词间 可以允许有空格
        StringBuilder sb = trimSpaces(s);

        // 翻转 sb的字符
        reverse(sb, 0, sb.length() - 1);

        // 翻转 每个单词
        reverseEachWord(sb);

        // 输出结果
        return sb.toString();
    }


    /**
     * 删除 多余空格：
     * 头、尾、以及 中间 最多保持一个空格
     */
    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ')
            ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ')
            --right;

        // 将字符串间 多余的空白字符去除
        StringBuilder sb = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ')
                // 非空字符 直接加
                sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ')
                // 空字符 添加 有条件：前一个不能 也是空
                sb.append(c);

            // 下一个字符 ，向右遍历
            ++left;
        }

        return sb;
    }

    /**
     * 翻转 sb上 某段长度的 字符
     */
    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            // 对调
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);
            // 分别 向内缩进
            left++;
            right--;
        }
    }

    /**
     * 根据 空格 找到单词的index区间，翻转即可
     */
    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 找到 某个单词的末尾 index
            while (end < n && sb.charAt(end) != ' ')
                ++end;


            // 此时end是 空格的index， 翻转 这个单词
            reverse(sb, start, end - 1);

            // 更新start，去找 下一个单词
            start = end + 1;
            ++end;
        }
    }


    /**
     * 3、将单词 读入一个list，然后 逆序 读出来 就可以了
     * 可以用 栈来实现（后进先出，其实 没必要的），就实现了 逆序
     * 每个单词（用空格判断） 依次入栈，
     */
    public String reverseWords3(String s) {
        int left = 0, right = s.length() - 1;

        // 去掉 字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ')
            ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ')
            --right;


        // 存放 临时单词的
        StringBuilder sb = new StringBuilder();

        Stack<String> stack = new Stack();

        while (left <= right) {
            // 取出当前字符，判断处理
            char c = s.charAt(left);

            // 如果 是空字符，sb 之前又没字符，不处理
            if ((sb.length() != 0) && (c == ' ')) {
                // 一个单词结束了， 将单词 push 入栈
                stack.push(sb.toString());
                sb.setLength(0);

            } else if (c != ' ') {

                sb.append(c);
            }

            // 下一个字符
            ++left;
        }

        // 最后一个sb 别忘了。
        stack.push(sb.toString());

        // todo 擦，注意，这里不能直接用这个api，这个api是 tm顺序的。。。stack的底层实现是 vector，
        //  所以，还是从前往后的，不是后进先出的效果
//        return String.join(" ", stack);

        sb.setLength(0);

        for (int i = stack.size() - 1; i >= 0; i--) {
//            System.out.println("第" + i + " 个 = " + stack.get(i));
            // 除了 第一个 单词(逆序，就是最后一个了)，都要 先加一个空格的

            if (i != stack.size() - 1) {
                sb.append(" ");
            }

            sb.append(stack.get(i));
        }

        return sb.toString();
    }


}
