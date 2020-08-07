package com.wsy.leetcode.graph.AL_785_isBipartite;

import com.sun.org.apache.bcel.internal.generic.FADD;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Official {

    /**
     * todo graph 图：
     *
     * 1、核心要素 就是 顶点 和 边
     * Vertex 和 Edge
     *
     * 而 树 和 链表 中的 元素 一般叫 Node --》结点！
     *
     *
     * 2、存储形式 很多种，常用 就两种： --》邻接表 、 邻接矩阵 ！
     * https://www.cnblogs.com/liushang0419/archive/2011/05/06/2039386.html
     * todo 其他的 关联矩阵 、弧、星型 表示法 用的很少　！
     *
     * 其中 纵向 元素 依次为 图中 所有的顶点 --》然后 就是要 表示 边的关系
     *
     * todo（1）邻接表（邻接 链表） ： 相对来说，更节省内存 --》因为 横向元素，就是 与该顶点 相连的 其他 顶点的值 --》（不用 额外判断 是否相连）
     * --》实际上 就是类似于 hash表那种， 数组 + 链表（表示 以当前顶点 为 起点的 多个边） 的 形式
     * --》当然了，如果 没有 权值，这里的链表 还可以 简化成 数组。。。
     *
     * （2）邻接矩阵：横向元素，表示 其他顶点 和 当前顶点的 连接关系，是否存在边 等
     * 需要通过 元素值 来判断 两个顶点 是否相连，相对来说，比 邻接表 更占用 内存 --》但是，功能 也更为 强大
     *
     * 3、图的分类：
     *（1）有权、无权：根据 边 的数值
     *（2）有向、无向：根据 [i][j] 表示 i-->j 和 [j][i] 表示 j-->i，如果 二者数值 不等，就是 有向图；无向图的话，二者 数值 必然 相等！
     * 如果是 有向图，每个顶点 又有 所谓的 度 --》入度（指向 该顶点的 边的个数） 和 出度（从 该顶点 发出的 边的个数）
     *
     * --》一些 特殊的图：
     *（3）连通图：
     *（4）二分图：图中的顶点 可以分为 两个集合，图中的任意一边的两个顶点，分别 来自 这两个集合！
     *
     *
     * 4、图的遍历、搜索：
     * 和 树 一样，有 深度、广度 优先 两种！
     * （1）但是，不像 树 那样 都有 root 结点，可以 固定当做 遍历的 发起点！
     * （2）图的遍历，需要 自己寻找 遍历发起点，一般选择：index = 0，即 第0排的点 或者 入度 = 0 的点 ！
     *
     * 深度 优先的实现：
     * 递归 或者 自己维护 一个待遍历的顶点的 stack；
     *
     * 广度 优先的实现：
     * 自己维护 一个 先入先出的队列 queue
     *
     * --》todo 深度 或者 广度，进行遍历，基本 都有的顶点 和 边，都要访问一次，所以 时间复杂度 基本 相同，就是 O(V + E)
     *
     * 5、经典 解法：
     * （1）一条边的 两个顶点 着色 不同
     *  --》-1（未着色） 0（颜色1） 1（颜色2）
     * （2）int[] visited 标记 某个顶点 是否 被访问过
     *
     *
     * todo --》遇到 非常规的 图的 表示法，不要慌，先转换成 我们熟悉的 邻接表 或者 邻接矩阵 --》再进行 后续 处理！
     */


    // TODO: 2020-08-07 14:00:02 思考了比较久了
    // TODO: 2020-08-07 14:52:06 看完 官方答案，初步 整理了 图 相关的知识点
    // TODO: 2020-08-07 15:07:43 整理完 具体思路，准备 写代码
    // TODO: 2020-08-07 16:05:50 整理完毕。。。我的天 ---》2 小时！


    /**
     * 判断 二分图
     * <p>
     * 原 graph 使用 邻接表 表示，是一个 无向图；
     * 判断 这个 graph 能否 将 顶点 拆分成 俩集合， 要求：任一边的 两个 组成顶点，要分别在 不同的 集合中！
     * <p>
     * 思路：
     * todo 二分图 就是 典型的 着色 判断法 --》不同的 俩顶点集合，用 颜色 区分！
     * 如果 遍历 整个图的所有顶点，能完成  将 任意一条边的 两个顶点 染成 不同的颜色 ，那这就是 二分图，否则 就是 false！
     * <p>
     * 总体方案是：
     * 假设 两种颜色 red、green；
     * （1）从 某顶点 开始 发起遍历（深度、广度 都行）
     * ---》因为 本题 没说 这个图 是 连通图，即 通过 某个顶点 一直往下遍历，可能 不能 访问到 所有顶点，所以 需要将 所有顶点 都作为 发起点 遍历一次
     * <p>
     * （2）这个顶点，没有 被着色过（即 访问过），如果是 发起点，就是 red；
     * <p>
     * （3）然后，开始遍历，由 当前顶点的颜色，计算 相邻顶点的颜色 --》
     * 判断 相邻顶点：
     * 未着色，就着 相反色，并 入栈 或者 入队；
     * 已着色，判断 是否 = 相反色，不是的话，就 return false！
     * <p>
     * （4）遍历完成后，还没有 返回，那就是 true ，是 二分图！
     * <p>
     * <p>
     * --->
     * 本题 深度、广度 都行 --》递归式的深度 反而 不好理解！
     */


    private static final int UN_COLORED = -1;
    // 两个颜色 分别 取 0 1，就是为了 neighbor = 1 - current
    private static final int RED = 0;
    private static final int GREEN = 1;

    /**
     * 方法1：深度优先 实现
     * dfs 或者 栈（这里 使用了 栈）
     * <p>
     * 这里的顶点，就是简单的 int值（或者说 Integer对象 也行）
     * <p>
     * <p>
     * 时间：O(V + E) --》 V 顶点数，E 边数 ，每个顶点，每个边 都要 访问一次
     * 空间：O(V)  --》color数组 V 个，stack（最多 V-1 ）
     */
    public boolean isBipartite(int[][] graph) {
        // 初始化 着色 数组
        int[] color = new int[graph.length];
        // --》因为 初始值为 -1 , 不是 0，需要主动 fill
        Arrays.fill(color, UN_COLORED);

        // stack 替代 递归 完成 深度 优先搜索！
        Stack<Integer> stack = new Stack<>();

        // 因为 图 可能 不连通，所以，这里 要 主动 遍历 所有的顶点
        for (int i = 0; i < graph.length; i++) {
            // i 就是 一个顶点
            // 发起顶点 需要是 未着色 的，才能 开始
            if (color[i] == UN_COLORED) {
                // 先着色，发起点 入栈
                color[i] = RED;
                stack.push(i);
                // 开始 遍历了！
                while (!stack.isEmpty()) {
                    // 弹出 栈顶 顶点 current
                    Integer current = stack.pop();
                    // neighbor 正确的颜色
                    int neighborColor = 1 - color[current];
                    // 给 current 的相邻顶点 graph[current] --》数组 尝试 一 一 着色
                    for (int j = 0; j < graph[current].length; j++) {
                        // 取出 某个 相邻顶点
                        int neighbor = graph[current][j];

                        // 如果 未上色，就上色 入栈
                        if (color[neighbor] == UN_COLORED) {
                            color[neighbor] = neighborColor;
                            stack.push(neighbor);
                        } else if (color[neighbor] != neighborColor) {
                            // 如果 上色了，但是 颜色不对，就是 false
                            return false;
                        }

                    }

                }

            }

        }

        // 前面 着色 成功完毕，就是 二分图
        return true;
    }


    // 是否 在 递归过程 中，发现 其已经 不是 二分图了
    boolean invalid = false;

    /**
     * 深度优先的 递归 实现
     * <p>
     * 时间：O(V + E)  顶点 和 边，都要 恰好 访问一遍
     * 空间：O(V) color数组，递归的栈
     */
    public boolean isBipartite2(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, UN_COLORED);
        // 初始化 为 仍有效
        invalid = false;

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == UN_COLORED) {
                // 以 该顶点 为 起点，开始 进行 递归式 深度遍历
                dfs(graph, i, RED, color);
            }
        }

        return !invalid;
    }

    // 从 node 顶点 开始 递归 处理 着色
    private void dfs(int[][] graph, int node, int nodeColor, int[] color) {
        // 2、 处理当前层
        color[node] = nodeColor;

        // 处理下一层（相邻 顶点）
        int neightborColor = 1 - nodeColor;
        for (int i = 0; i < graph[node].length; i++) {
            int neighbor = graph[node][i];

            // 没着色，递归处理
            if (color[neighbor] == UN_COLORED) {
                // 3、递归
                dfs(graph, neighbor, neightborColor, color);

                // 4、善后
                if (invalid) {
                    return;
                }

            } else if (color[neighbor] != neightborColor) {
                invalid = true;
                return;
            }

        }

    }


    /**
     * 广度优先 实现着色
     * <p>
     * 借助于 queue
     * <p>
     * 时间：O(V + E)  顶点 和 边，都要 恰好 访问一遍
     * 空间：O(V) color数组，queue
     */
    public boolean isBipartite3(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, UN_COLORED);

        // TODO: 2020-08-07 15:41:01 --》和上面的 stack实现的 深度优先的 区别，非常的小 --》操作逻辑 几乎 完全 一致，就是 数据结构 特性的不同 ！
        // 后进先出 和 先进先出！
        Queue<Integer> queue = new LinkedList<>();

        // 因为 可能 非连通，所以 都要 作为 发起点
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == UN_COLORED) {
                color[i] = RED;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    Integer current = queue.poll();
                    int neighborColor = 1 - color[current];
                    for (int j = 0; j < graph[current].length; j++) {
                        int neighbor = graph[current][j];
                        if (color[neighbor] == UN_COLORED) {
                            color[neighbor] = neighborColor;
                            queue.offer(neighbor);
                        } else if (color[neighbor] != neighborColor) {
                            return false;
                        }

                    }

                }

            }

        }


        return true;
    }


}
