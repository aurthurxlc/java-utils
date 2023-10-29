Java 常用工具类，开箱即用。

AES 算法使用方法如下：

```java
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
```

输出结果如下：

```bash
key: e43ee68382dc550fbd1d329486febdd4
iv: ddffc44a93503156abb36e9bbca876f8
pText: AES 算法基于 Java 实战演示
加密后 cText: e8aa678c21aa028988cd74ee2835344519014a4e9365cb8dda7cf24bfe95dfdf0e047cf979587b02500ccad15415b1c3
解密后 pText: AES 算法基于 Java 实战演示
```