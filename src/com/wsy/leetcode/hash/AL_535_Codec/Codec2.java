package com.wsy.leetcode.hash.AL_535_Codec;

import java.util.HashMap;
import java.util.Random;

public class Codec2 {


    /**
     * 方法3：
     * url --》短连接 加密
     */

    // 62位 长度 字母 （10 + 26 + 26）
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    String prefix = "http://tinyurl.com/";

    HashMap<String, String> map = new HashMap<>();

    Random rand = new Random();

    // todo 一直 在变的 key：String类型，容量范围 远超int（62 ^ 6）；长度 固定为 6位了，算是 短链接了！
    String key = getRand();

    // todo 主要难点 就是 key 的 生成 ---》生成 不重复、不好预测的、位数较短 且 固定的 key --》此方法 和 url 出现次序 无关了 ！
    public String getRand() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            // 拼6个字母：每次都从 62个字符中 随机取一个
            sb.append(alphabet.charAt(rand.nextInt(62)));
        }

        return sb.toString();
    }

    public String encode(String longUrl) {

        while (map.containsKey(key)) {
            key = getRand();
        }

        map.put(key, longUrl);

        return prefix + key;
    }


    public String decode(String shortUrl) {

        return map.get(shortUrl.replace(prefix, ""));
    }


}
