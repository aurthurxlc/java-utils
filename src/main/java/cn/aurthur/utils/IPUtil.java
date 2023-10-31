package cn.aurthur.utils;

import cn.aurthur.lang.pool.StringPool;

public abstract class IPUtil {
    public IPUtil() {
        throw new AssertionError("工具类不允许实例化");
    }

    /**
     * string类型的ip转换为number类型
     *
     * @param ip xxx.xxx.xxx.xxx
     * @return long 3663452325
     */
    public static long encodeIp(String ip) {
        long ret = 0;
        if (ip == null) {
            return ret;
        }
        String[] segs = ip.split("\\.");

        for (int i = 0; i < segs.length; i++) {
            long seg = Long.parseLong(segs[i]);
            ret += (seg << ((3 - i) * 8));
        }

        return ret;
    }

    /**
     * number类型的ip转换为string类型
     *
     * @param ipLong 3663452325
     * @return String xxx.xxx.xxx.xxx
     */
    public static String decodeIp(long ipLong) {

        return (ipLong >> 24)
                + StringPool.Symbol.DOT
                + ((ipLong & 16711680) >> 16)
                + StringPool.Symbol.DOT
                + ((ipLong & 65280) >> 8)
                + StringPool.Symbol.DOT
                + (ipLong & 255);
    }
}
