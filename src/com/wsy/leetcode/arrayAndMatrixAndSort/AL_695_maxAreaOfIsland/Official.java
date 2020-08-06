package com.wsy.leetcode.arrayAndMatrixAndSort.AL_695_maxAreaOfIsland;

public class Official {


    /**
     * 靠，这个矩形 可能不是正方形
     * <p>
     * 所以，grid.length 表示多少排，
     * grid[i]表示 第几排，其length 即 多少列
     */
    public int maxAreaOfIsland(int[][] grid) {
        // TODO: 2020/6/21 边界条件！
        if (grid == null || grid[0].length == 0) {
            return 0;
        }


        int res = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                // 找到 数值为1的点，则 就是一个 小岛
                if (grid[i][j] == 1) {
                    // 计算 其左上方的 相连的岛的个数，和 max 作比较
                    res = Math.max(res, dfs(i, j, grid));
                }
            }
        }

        return res;
    }

    /**
     * 每次调用的时候 默认num为1，
     * 进入后 判断如果 不是岛屿，则直接返回0，就可以避免 预防错误的情况。
     * 每次找到岛屿，则直接把 找到的岛屿改成0，这是传说中的 沉岛思想，
     * todo 就是遇到岛屿 就把他和周围的 全部沉默 （就是 一次性 找到周围的全部相连的岛，全部统计完后，置空沉没，后面计算 就可以 大幅优化了）
     * ps：如果能用 沉岛思想，那么自然可以用 朋友圈思想。
     * 有兴趣的朋友可以去尝试。
     */
    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }

        // 先 计数
        int num = 1;

        // 沉岛操作，即 让 当前岛屿 沉没，修改为 0
        grid[i][j] = 0;

        // todo 上下左右 各方向去找，为了防止 重复计数，前面修改 当前岛屿为0
        // 计算本次 相邻岛屿的数量
        num += dfs(i + 1, j, grid);
        num += dfs(i - 1, j, grid);
        num += dfs(i, j + 1, grid);
        num += dfs(i, j - 1, grid);

        return num;

    }


}
