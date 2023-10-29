package cn.aurthur.utils;

import java.util.UUID;

/**
 * UUID 相关函数
 *
 * @author aurthur
 * @since 1.0.0
 */
public class UUIDUtil {

    /**
     * @return 随机 UUID
     */
    public static String getGUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}