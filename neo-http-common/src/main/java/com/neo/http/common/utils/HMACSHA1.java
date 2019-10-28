package com.neo.http.common.utils;

import com.neo.http.common.lang.NeoHttpException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class HMACSHA1 {
    private static final Logger logger = LoggerFactory.getLogger(HMACSHA1.class);

    private static final String MAC_NAME = "HmacSHA1";

    private static final String ENCODING = "UTF-8";

    public static String genKeyStr() throws NoSuchAlgorithmException {
        System.out.println("key: " + Base64.encodeBase64URLSafeString(genKey().getEncoded()));
        return Hex.encodeHexString(genKey().getEncoded());
    }

    public static SecretKey genKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(MAC_NAME);
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public static byte[] hmacSHA1Encrypt(String encryptKey, String encryptText) {
        Mac mac = null;
        byte[] text = null;
        try {
            byte[] data = encryptKey.getBytes(ENCODING);

            // 根据给定的字节数组构造一个密钥
            SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);

            mac = Mac.getInstance(MAC_NAME);

            mac.init(secretKey);

            text = encryptText.getBytes(ENCODING);
        } catch (Exception e) {
            logger.error("An error has occured when hmac-sha1 Encrypt.", e);
            throw new NeoHttpException(e);
        }

        return mac.doFinal(text);
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static byte[] testMD5(String content) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(content.getBytes("utf8"));
        return bytes;
    }
}
