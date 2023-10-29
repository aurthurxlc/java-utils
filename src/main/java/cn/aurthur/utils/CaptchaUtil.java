package cn.aurthur.utils;

import java.util.Random;

/**
 * CaptchaUtil 验证码相关函数
 *
 * @author aurthur
 * @since 1.0.0
 */
public class CaptchaUtil {

    /**
     * @return 6 位数字验证码
     */
    public static String simple() {
        int max = 999999;
        int min = 100000;
        Random rand = new Random();
        return Integer.toString(rand.nextInt((max - min) + 1) + min);
    }
}