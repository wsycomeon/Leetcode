package com.wsy.leetcode.hash.AL_535_Codec;


import org.omg.CORBA.MARSHAL;

import java.util.HashMap;

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
public class Codec {


    /**
     * TinyURL的加密与解密
     * <p>
     * 既然是 求 longUrl 和 short 的 加密、映射 关系，那就 肯定 需要 hashMap了
     * 关键是，shortUrl 和 key 的关系，以及 key 可用的范围，
     * 而且 要考虑 无规律，不好 破解，冲突小
     * <p>
     * https://leetcode-cn.com/problems/encode-and-decode-tinyurl/solution/tinyurlde-jia-mi-yu-jie-mi-by-leetcode/
     * <p>
     * 这里方法很多，就说 3个吧；
     *
     * <p>
     * 方法1：
     * key 就是一个自增数值，shortUrl = prefix + key，然后 把 key - longUrl 存入 map 即可
     * <p>
     * 这么做，有几个缺点：
     * 1、key 类型是 int，也就意味着 能存的 url 数量有限；一旦超过了，就会覆盖 之前的url，导致 算法失效！
     * 2、加密后的url 也不一定比 longUrl 还要短，因为 key 是自增的，位数 也会增加，越来越长
     * 3、推测 下一个生成的加密 url 非常方便。。。
     * <p>
     * <p>
     * <p>
     * 方法2：
     * key 直接使用 url的hashCode，这样 本身也是 int 限制了 key的个数，且 有 冲突的概率，比 自增 还要坑爹。。。
     * <p>
     * <p>
     * <p>
     * 方法3：随机数、固定长度 加密。看 下一个 类
     */
    public String encode(String longUrl) {
        map.put(key, longUrl);
        String shortUrl = prefix + key;

        // key 要刷新
        key++;
        return shortUrl;
    }

    HashMap<Integer, String> map = new HashMap<>();

    int key = 0;

    String prefix = "http://tinyurl.com/";


    public String decode(String shortUrl) {
        int key = Integer.parseInt(shortUrl.replace(prefix, ""));
        return map.get(key);
    }


}
