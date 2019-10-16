package com.neo.http.sample.hello.server.common;

import com.neo.http.common.bean.Error;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-16 14:38
 */
public enum HelloError implements Error {
    TEST_ERROR  ("Hello.1000", "测试错误"),
    ;

    private String code;
    private String message;

    HelloError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
