package com.wsy.leetcode;

import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {


    public static void main(String[] args) {


//        System.out.println(getResult(" 1 + 3 * 3 + ( 2 - 3 ) = 4 "));
//        System.out.println(getResult(" 1 + 3 * 3 + ( 2 - 3 )  -2 "));
        System.out.println(getResult(" 1 + 3 * 3 + ( 13 - 3 )  -2 * ("));
//        System.out.println(getResult("3 * (2-6 /(3 -7))"));
    }


    /**
     * todo 实现 四则运算器 ！！！
     * <p>
     * 看人家的优秀 处理方式 --》处理 单个字符时，直接 poll 删掉 ，防止 指针 不同步 问题
     * <p>
     * 1、将 字符串 转成 字符队列 queue
     * <p>
     * 2、每次 直接 pop 出一个字符，然后处理：
     * <p>
     * （1）如果是 数字 字符，就 更新 num 值
     * --》注意，可能 多个 数字字符 连一起的，所以 需要处理 数字字符串 的映射，且 注意 int 溢出问题 --》  current  - '0' 加括号 先计算出来
     * 且注意 --》不要 直接 break 或者 return 等，而是 需要 继续 后面的逻辑处理
     * <p>
     * （2）左括号 --》表示 开启了一个新的 子运算
     * --》递归调用 --》其内部的算式 到 右括号 ，其实 就可以当成 一个 数值，更新给 num 就好了
     *
     * <p>
     * （3）如果 不是 数字字符，且 剔除 无用的 且 非最后一位的 空格符，可以当做一个 新的 运算符 或者说 结果更新符
     * todo 此次 poll后 队列空了，也要 把 之前的操作 处理入栈 ！
     * <p>
     * 此时 要把之前的 "运算符 + - * / 和 数值" 做 入栈操作 --》举例： * 10 + --》这里遇到了 + 操作符，先把 之前的 * 10 操作 ，处理掉 --》结果 入栈
     * --》并更新 操作符 和 num 清零
     * <p>
     * 非数字的有， + - * / 空格 = ( ) 这么多种 情况
     * <p>
     * （4）如果是 终结符号，就 break 循环
     * 比如 ) 和 =
     * <p>
     * 3、跳出 循环后
     * 累加 stack 内的值 ，返回 当前 方法 调用的 计算结果 ！
     */
    public static int getResult(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        // 1、生成 字符 队列
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            queue.offer(input.charAt(i));
        }

        return getCharQueueCal(queue);
    }

    private static int getCharQueueCal(Queue<Character> queue) {
        Stack<Integer> stack = new Stack<>();

        // 2、遍历 队列，依次 poll
        int num = 0;
        char op = '+';
        while (!queue.isEmpty()) {
            // 2-1 弹出 最前面的 字符 --> 处理过的、就被 直接 删除了
            Character current = queue.poll();

            // 2-2 先处理 更新 数字的情况：本身是 数字字符，或者是 '(' 所代表的 优先 子运算
            if (Character.isDigit(current)) {
                // todo 注意，这里，后面的数字字符 先 - '0' ，不要直接 加，可能导致 int 越界
                num = num * 10 + (current - '0');
            }

            if (current == '(') {
                // 开启了 子运算 ---> 计算 括号内 子运算 的值
                // todo 操作的是 用一个queue，这样的话，指针 就不会 有 回溯问题了
                num = getCharQueueCal(queue);
            }


            // 2-3 再处理：遇到的是 新的、有效的 操作符（todo 或者叫 结果 更新符） --》要 处理 旧的入栈，并更新为 新的
            if ((!Character.isDigit(current) && current != ' ') || queue.isEmpty()) {

                // 忽略 非最后一位的空格；其他的非数字字符 都是 有效的操作符
                switch (op) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }

                // 更新操作符 num
                op = current;
                num = 0;
            }

            // 2-4 todo 如果 还是 提前的 运算终止符 --》直接 break 循环，表示 本次方法调用，不能 再使用 和 修改 queue 了
            if (current == ')' || current == '=') {
                break;
            }

        }


        // 3、返回 stack 内 元素的 累加结果
        int res = 0;

        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }


    /**
     * todo md 这代码 写的 漏洞百出 --》错误 代码 ============================================
     * 如果只是 原数组，加 index 偏移计算，实际上 边界 不好控制
     * 因为 你用过的字符 还在原数组，实际上 这样 可能导致 指针 已经 运算过了 这些字符，
     * 但是 忘记 更新指针，导致 重复 计算。
     * --> 而且 指针更新 也是不好搞的，你后面 多几个括号，就不好处理了
     * todo 所以，最好还是像 人家那样，直接 运算一个 扔掉一个，防止重复运算 --》需要用到的数据 都存到 stack 中了
     */
    public static int[] getResult2(String input) {
        if (input == null || input.length() == 0) {
            return new int[]{0, 0};
        }

        /**
         *  取出 所有的字符 + - * / 空格 ( ) =
         *  这题的思路是：
         *  用一个stack 存储 上次的 待操作数，这些都是 中间变量：俩用途
         *
         *  1、最后 所有的运算，都转换成一个 待进行 加操作的 数值（有 正、负之分）
         *  2、栈顶的数，在最后 加算之前，可能 需要 被 当做 一次操作的 前面的那个数 使用
         *  一个计算操作，需要 前一个数 + 具体操作 + 当前的数；
         *  所以 用俩 临时变量，记录 当前操作符 和 当前数字
         *
         *  具体如下：
         *  1、默认 遇到 新的 操作符，就计算 上一个操作符 对应的操作；并将结果 压入 栈中，更新 操作符
         *  (1) 如果 字符是 数字的话，就 用 当前字符 - '0' 得到数值，存给 变量 b --》或者说 currentVal
         *  2、上一个 操作符 的 对应操作：上一个数字是 b
         *  将 下述结果 存入栈中；
         *  + ：b
         *  - : -b
         *  * : 弹出栈顶的 * b
         *  / : 弹出栈顶的 / b
         *  空格 : 跳过
         *  ( : 可以 理解成 ，下一位 到 最近的 ")" 之间 是 一个同样的函数调用
         *  ) : 上一个 ")" 计算的终止位：
         *  末尾 或者 = : 整个计算的 终止位，将 stack的结果 全部相加，返回 最终结果
         *
         *  demo：
         *  1 + 3 * 3 + ( 2 - 3 ) = 9
         *  实际上 在 1 之前 默认有一个 " + " 号
         */

        char[] array = input.toCharArray();

        return cal(array, 0, array.length - 1);

    }

    private static int[] cal(char[] array, int startIndex, int endIndex) {
        char op = '+';
        int curVal = 0;
        Stack<Integer> stack = new Stack<>();


        int returnIndex = 0;

        // 遍历 指定区域的字符
        for (int i = startIndex; i <= endIndex; i++) {
            // 当前 字符
            char current = array[i];

            // 先刷新 数字
            if (Character.isDigit(current)) {
                curVal = current - '0';
                // TODO: 2020/7/31 md，这里 continue 也不能用，如果恰好是 最后一位，后面 还要处理位数，压栈的
//                continue;
            }


            // TODO: 2020/7/31  草，这里 空字符 不能 随便跳过， 恰好是 最后一位 就惨了
//            if (current == ' ') {
//                continue;
//            }

            // 当前是 操作符，看下 上一个操作符 和 数字
            if (isCalChar(current)
                    || current == ')'
                    || current == '='
                    || i == endIndex) {

                // 1、更新栈
                dealOp(op, curVal, stack);

                // 2、更新 操作符
                if (isCalChar(current)) {
                    curVal = 0;
                    op = current;
                }

                // 3、TODO: 是某种 计算 终止符，就 break 循环，外面 stack 累加 返回
                if (current == ')' || current == '=' || i == endIndex) {
                    returnIndex = i;
                    break;
                }

                continue;
            }

            if (current == '(') {
                // 开启 子计算
                int[] cal = cal(array, i + 1, endIndex);
                curVal = cal[0];
                // TODO: 2020/7/31 还要更新index 为 ) 的
                i = cal[1];
            }

        }

        // 返回 stack内的 叠加 结果
        return new int[]{getStackResult(stack), returnIndex};
    }

    private static void dealOp(char op, int curVal, Stack<Integer> stack) {
        switch (op) {
            case '+':
                stack.push(curVal);
                break;
            case '-':
                stack.push(curVal * -1);
                break;

            case '*':
                Integer last = stack.pop();
                stack.push(last * curVal);
                break;
            case '/':
                Integer last1 = stack.pop();
                stack.push(last1 / curVal);
                break;
        }
    }

    private static int getStackResult(Stack<Integer> stack) {
        int result = 0;
        while (!stack.isEmpty()) {
            Integer number = stack.pop();
            result += number == null ? 0 : number;
        }
        return result;
    }

    private static boolean isCalChar(char current) {
        return current == '+' || current == '-' || current == '*' || current == '/';
    }


}
