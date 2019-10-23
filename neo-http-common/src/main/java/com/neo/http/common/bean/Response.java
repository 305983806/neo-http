package com.neo.http.common.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-12 11:13
 */
public class Response implements Serializable {

    // 该条查询的记录id，主要用于排查问题使用
    private String requestId;

    // 错误码
    private String code;

    // 错误信息
    private String message;

    // 实际返回结果，如果沒有返回结果，则为null
    private String result;

    public static Response fromJson(String json) {
        return JSON.parseObject(json, Response.class);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
