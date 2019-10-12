package com.neo.http.client.bean;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-12 11:14
 */
public enum Status {
    OK ("0", "OK"),
    Fail ("-1","FAIL"),
    ;

    private String code;
    private String message;

    Status(String code, String message) {
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
