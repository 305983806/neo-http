package com.neo.http.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-29 17:47
 */
public class MD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    public static byte[] digest(String content) {
        try {
            return digest(content.getBytes("utf8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] digest(byte[] content) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(content);
    }

}
