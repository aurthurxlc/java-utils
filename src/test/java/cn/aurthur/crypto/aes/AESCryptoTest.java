package cn.aurthur.crypto.aes;

import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESCryptoTest {
    IvParameterSpec iv;
    SecretKey key;
    String pText;

    @Before
    public void init() throws NoSuchAlgorithmException {
        // 准备参数
        iv = AESTool.generateIV();
        key = AESTool.generateKey(128);
        pText = "AES 算法基于 Java 实战演示";

        System.out.println("key: " + BytesUtil.bytesToHex(key.getEncoded()));
        System.out.println("iv: " + BytesUtil.bytesToHex(iv.getIV()));
        System.out.println("pText: " + pText);
    }

    @Test
    public void test() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        // 加密
        AESCrypto aes = new AESCrypto(AESMode.CBC_PKCS5Padding, key);
        byte[] cBytes = aes.encryptWithIV(BytesUtil.stringToBytes(pText), iv);
        System.out.println("加密后 cText: " + BytesUtil.bytesToHex(cBytes));

        // 解密
        byte[] pBytes = aes.decryptWithIV(cBytes, iv);
        System.out.println("解密后 pText: " + BytesUtil.bytesToString(pBytes));

    }
}