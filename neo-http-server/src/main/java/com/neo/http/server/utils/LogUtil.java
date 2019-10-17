package com.neo.http.server.utils;

import java.util.UUID;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-15 14:26
 */
public class LogUtil {

    public static String genRequestId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

}
