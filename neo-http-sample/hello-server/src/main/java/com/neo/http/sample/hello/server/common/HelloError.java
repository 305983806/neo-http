package com.neo.http.sample.hello.server.common;

import com.neo.http.common.bean.Error;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-16 14:38
 */
public enum HelloError implements Error {
    TEST_ERROR  ("hello.1000", "测试错误"),
    HELLO_NOT_REGISTER  ("hello.1001", "该学生还未注册任何课程。"),
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
