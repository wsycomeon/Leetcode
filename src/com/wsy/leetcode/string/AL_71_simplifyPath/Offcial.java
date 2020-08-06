package com.wsy.leetcode.string.AL_71_simplifyPath;

import java.util.Stack;

public class Offcial {


    /**
     * 1.此题主要考察的是 栈 ( 如果遇到回退，要 后进 先出) , 所以定义一个辅助栈;
     * 2.先把字符串以"/"为分隔符分割成数组,此时数组有
     * "路径"、""、"."、".." 这四种情况;
     * 3.遍历数组,当 s[i].equals("..")并且 栈不空时 pop 删除一个,
     * 当!s[i].equals("") && !s[i].equals(".") && !s[i].equals("..") --》即 s[i]是路径 则入栈;
     * 4.遍历之后，
     * 栈空,返回"/"
     * 栈非空,用 StringBuilder 做一个拼接 返回即可;
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();


        String[] s = path.split("/");


        for (int i = 0; i < s.length; i++) {

            if (!stack.isEmpty() && s[i].equals(".."))
                // stack 不空，回退一层，即 删除 一个路径
                stack.pop();
            else if (!s[i].equals("") && !s[i].equals(".") && !s[i].equals(".."))
                // 有效的 路径
                stack.push(s[i]);
        }


        if (stack.isEmpty())
            return "/";

        // 顺序读出来，这里用到了 vector，所以，其实一开始 也可以不用栈的，用 deque 之类的
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append("/").append(stack.get(i));
        }

        return sb.toString();
    }

}
