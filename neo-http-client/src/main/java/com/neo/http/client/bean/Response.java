package com.neo.http.client.bean;

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

    // 执行结果，OK为成功，FAIL为失败
    private String status;

    // 实际返回结果，如果沒有返回结果，则为null
    private String result;

    // 错误内容
    private Error error;

    public static Response fromJson(String json) {
        return JSON.parseObject(json, Response.class);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
