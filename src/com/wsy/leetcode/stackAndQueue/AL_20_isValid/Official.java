package com.wsy.leetcode.stackAndQueue.AL_20_isValid;

import java.util.HashMap;
import java.util.Stack;

public class Official {


    /**
     * 判断 一个 字符串，是否是 有效、顺序的 闭合串
     * 只有 '(' '[' '{' 3 种；
     * <p>
     * 方法1：
     * 遍历 所有的字符：
     * 用 栈 来存 左括号，遇到 右括号，就 弹出栈顶的 是不是 需要的 --》维护一个映射关闭 map 即可；
     * 不是的话；false；否则 继续
     * 遍历结束后，看下 栈是否为空，空 则是 有效；否则 false
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        // 映射关系 用
        HashMap<Character, Character> closeMap = new HashMap<>();
        closeMap.put(')', '(');
        closeMap.put(']', '[');
        closeMap.put('}', '{');

        // 遍历 所有字符
        char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            // 是 右括号，看下 stack 最顶部的是不是 匹配的那个，不是的话，就是 false
            if (closeMap.containsKey(c)) {
                // 如果 栈 为空的话，随便 取一个 无效字符 #
                char top = stack.isEmpty() ? '#' : stack.pop();

                if (top != closeMap.get(c)) {
                    return false;
                }

            } else {
                // 是 左括号，直接 入栈
                stack.push(c);
            }

        }

        // 如果是有效的，前面 应该全部 弹出了，这里应该是 null！
        return stack.isEmpty();
    }


}
