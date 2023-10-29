package cn.aurthur.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomUtil {
    public static String randomString(int n) {
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, StandardCharsets.UTF_8);
        StringBuilder r = new StringBuilder();

        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            boolean flag = ((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0);
            if (flag) {

                r.append(ch);
                n--;
            }
        }
        return r.toString();
    }
}
