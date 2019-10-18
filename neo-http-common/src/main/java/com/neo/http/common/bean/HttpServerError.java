package com.neo.http.common.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-12 10:22
 */
public class HttpServerError implements Error, Serializable {
    private String code;
    private String message;

    public HttpServerError() {}

    public HttpServerError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpServerError fromJson(String json) {
        return JSON.parseObject(json, HttpServerError.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
