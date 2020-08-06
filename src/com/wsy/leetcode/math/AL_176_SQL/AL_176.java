package com.wsy.leetcode.math.AL_176_SQL;

public class AL_176 {

    /**
     * 176. 第二高的薪水
     * SQL架构
     * 编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
     * <p>
     * +----+--------+
     * | Id | Salary |
     * +----+--------+
     * | 1  | 100    |
     * | 2  | 200    |
     * | 3  | 300    |
     * +----+--------+
     * 例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 null。
     * <p>
     * +---------------------+
     * | SecondHighestSalary |
     * +---------------------+
     * | 200                 |
     * +---------------------+
     * <p>
     * https://leetcode-cn.com/problems/second-highest-salary/solution/di-er-gao-de-xin-shui-by-leetcode/
     *
     *
     *
     * 答案 在这里：
     * ---------------------------------------------------------------------------------------------------------
     *         // todo 这里，如果 本身 只有 1条 记录，这里会直接 运行 报错；不会 返回 null
     *         String sql1 = """
     *                 SELECT DISTINCT
     *                 Salary AS SecondHighestSalary
     *                         FROM
     *                 Employee
     *                 ORDER BY Salary DESC
     *                 LIMIT 1 OFFSET 1
     *                         """;
     *
     *         // 而这个 把 里面查询结果 当成一个临时表，外面赋值为 SecondHighestSalary
     *         String sql2 = """
     *                     SELECT
     *                         (SELECT DISTINCT
     *                                 Salary
     *                             FROM
     *                                 Employee
     *                             ORDER BY Salary DESC
     *                             LIMIT 1 OFFSET 1) AS SecondHighestSalary
     *                 """;
     *
     *         // 这个，使用了 IFNULL( 参数1，参数2 ) 函数
     *         String sql3 = """
     *                 SELECT
     *                     IFNULL(
     *                       (SELECT DISTINCT Salary
     *                        FROM Employee
     *                        ORDER BY Salary DESC
     *                         LIMIT 1 OFFSET 1),
     *                     NULL) AS SecondHighestSalary
     *                 """;
     * ---------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) {



    }


}
