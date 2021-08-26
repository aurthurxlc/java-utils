package cn.aurthur.crypto.aes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BytesUtil {

    public static byte[] stringToBytes(String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }

    public static String bytesToString(byte[] data) {
        return new String(data);
    }

    public static String bytesToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] base64ToBytes(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static String bytesToHex(byte[] data) {
        return new BigInteger(1, data).toString(16);
    }

    public static byte[] hexToBytes(String data) {
        byte[] byteArray = new BigInteger(data, 16).toByteArray();
        if (byteArray[0] == 0) {
            byte[] output = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, output,
                    0, output.length);
            return output;
        }
        return byteArray;
    }
}
