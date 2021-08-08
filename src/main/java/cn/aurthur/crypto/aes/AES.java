package cn.aurthur.crypto.aes;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {
    private final AESMode mode;
    private final SecretKey key;

    public AES(AESMode mode, SecretKey key) {
        this.mode = mode;
        this.key = key;
    }

    public byte[] encryptWithIV(byte[] plainText, IvParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(mode.getMode());
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(plainText);
    }

    public byte[] decryptWithIV(byte[] cipherText, IvParameterSpec iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(mode.getMode());
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(cipherText);
    }

}
