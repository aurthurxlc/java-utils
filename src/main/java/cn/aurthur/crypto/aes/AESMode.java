package cn.aurthur.crypto.aes;

public enum AESMode {
    CBC_PKCS5Padding("AES/CBC/PKCS5Padding"),
    CFB_NoPadding("AES/CFB/NoPadding"),
    OFB_NoPadding("AES/OFB/NoPadding"),
    CTR_NoPadding("AES/CTR/NoPadding"),
    GCM_NoPadding("AES/GCM/NoPadding");

    public final String mode;

    AESMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }
}
