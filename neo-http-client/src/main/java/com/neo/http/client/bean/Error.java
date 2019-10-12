package com.neo.http.client.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-12 10:22
 */
public class Error implements Serializable {
    private String code;
    private String message;

    public Error() {}

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error fromJson(String json) {
        return JSON.parseObject(json, Error.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
