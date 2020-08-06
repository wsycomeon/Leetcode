package com.wsy.leetcode.math.AL_69_mySqrt;

public class Official {


    /**
     * 求x的正平方根的整数部分
     * <p>
     * 方法1：
     * 袖珍计算机
     * 其实就是 根号x ，转换成 x^(1/2)  =  e^(ln x^(1/2)) = e^ (1/2 lnx)
     * todo 不让用 平方根函数。。。却让用 指数函数 + 对数函数。。。。
     * 我也是醉了。。
     * todo 但是，因为 指数函数 和 对数函数 的 参数和返回值 都是浮点数，所以 计算结果 会有一个 小于1的误差
     * 所以，最后要把 最后的结果 ans，和 ans+1 比较，哪个平方 是更接近于 x的。
     */
    public int mySqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x should >= 0 !");
        }

        if (x == 0) {
            return 0;
        }

        // x > 0
        int ans = (int) Math.exp(0.5 * Math.log(x));

        // 因为 有误差，要 优先计算 +1

        // TODO: 2020/6/28 我曹，注意 这里的平方，很可能 超过 int范围了，要保留他的精度，需要 转成 long；
        // 如果精度丢失，就会导致 其平方值 变小，本身 ans+1 的平方 能超 x 的
        // 现在 也超不了了，只能取错误的 ans+1 为结果了。。
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }


    /**
     * 方法2：
     * <p>
     * 从（0，x）内 二分定位，快速查找，y^2< = x的最大值
     */
    public int mySqrt2(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x should >= 0 !");
        }

        if (x == 0) {
            return 0;
        }

        int left = 0;
        int right = x;
        int ans = -1;


        while (left <= right) {
            int mid = left + (right - left) / 2;
            // TODO: 2020/6/28 这里 也是，要注意 大数值的精度 不能丢失，否则，这里 就出错了。。
            // 因为 此时的 mid值的平方 实际上已经更大了，应该 往左找了，但是 因为 错判为小，往右找，永远也找不到的。。
            /**
             * 例子
             * {@link AL_69#test1()}
             */
            if ((long) mid * mid <= x) {
//            if (mid * mid <= x) { // 丢失精度的写法！！！
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }


    /**
     * 方法3：利用 牛顿斜率 快速 逼近
     * 假设 待求 平方很的数字是 C；
     * 其实就是 求 y = f(x) = x^2 - C 与 x轴的焦点
     * 这是一个 凸函数；
     * 所以我们一开始 选择 x = C，在对应的y函数上，找到 点 (x,x^2-c)
     * 以这个点 求导，做切线，与x轴的焦点 就是我们的下一个 选取的点，或者说 更逼近于 正确答案的点
     * 即 由 前一个点 xi 求其曲线上的切线 于 x轴的焦点 x(i+1)：
     * 切线为 Z = 2xi * (x-xi) + xi^2-C
     * x(i+1) = 0.5(xi + C/xi)
     * --> 即  从大到小，无限逼近于 正确答案；
     * 啥时候终止呢？
     * 前后两次的差值 小于某个很小的阈值，比如 10^-6 或者 10^-7
     */
    public int mySqrt3(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x should >= 0 !");
        }

        if (x == 0) {
            return 0;
        }

        double C = x;
        double x0 = C;

        while (true) {
            // 注意是 double。精度很高
            double xi = 0.5 * (x0 + C / x0);

            // 计算 两次差值
            if (Math.abs(xi - x0) < Math.pow(10, -7)) {
                break;
            }

            // 否则  继续 往左逼近
            x0 = xi;
        }

        return (int) x0;
    }

}
