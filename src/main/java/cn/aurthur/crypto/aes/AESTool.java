package cn.aurthur.crypto.aes;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESTool {

    private static final String algo = "AES";

    /**
     * 生成 AES 密钥，n 可选：128、192、256
     */
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algo);
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    /**
     * 生成 AES 初始向量 IV
     */
    public static IvParameterSpec generateIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
